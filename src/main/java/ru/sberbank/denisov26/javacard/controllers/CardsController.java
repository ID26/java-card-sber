package ru.sberbank.denisov26.javacard.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.denisov26.javacard.exceptions.CardNotFoundException;
import ru.sberbank.denisov26.javacard.exceptions.ClientNotFoundException;
import ru.sberbank.denisov26.javacard.models.Card;
import ru.sberbank.denisov26.javacard.models.Client;
import ru.sberbank.denisov26.javacard.services.CardsService;
import ru.sberbank.denisov26.javacard.services.ClientsService;
import ru.sberbank.denisov26.javacard.utils.CardGenerator;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Slf4j
@Controller
@RequestMapping("cards")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CardsController {
    private static final String CLIENT_ID = "clientId";
    private static final String CARD_EXCEPTION = "Card {} not found!!! Exception: {}, Date: {}";
    private final CardsService cardsService;
    private final ClientsService clientsService;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("cards", cardsService.findAll());
        return "/cards/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
            try {
                Card card = cardsService.findById(id);
                model.addAttribute("card", card);
                model.addAttribute(CLIENT_ID, card.getClient().getId());
            } catch (CardNotFoundException e) {
                log.error(CARD_EXCEPTION, id, e, LocalDateTime.now());
            }
        return "cards/show";
    }

    @GetMapping("/new/{id}")
    public String newCard(@PathVariable("id") Long id, Model model) {
            try {
                Card tempCard = CardGenerator.generateCard(clientsService.findById(id));
                model.addAttribute("card", tempCard);
            } catch (ClientNotFoundException e) {
                log.error(CARD_EXCEPTION, id, e, LocalDateTime.now());
            }
            model.addAttribute(CLIENT_ID, id);
        return "cards/new";
    }

    @PostMapping("/new/{id}")
    public String create(@PathVariable("id") Long id, @ModelAttribute("card") @Valid Card card,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(CLIENT_ID, id);
            return "cards/new";
        }
        try {
            card.setClient(clientsService.findById(id));
        } catch (ClientNotFoundException e) {
            log.error("Client {} not found!!! Exception: {}, Date: {}", id, e, LocalDateTime.now());
        }
        cardsService.save(card);
        return String.format("redirect:/clients/%d", id);
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        try {
            Card card = cardsService.findById(id);
            model.addAttribute("card", card);
            model.addAttribute("cardId", card.getId());
        } catch (CardNotFoundException e) {
            log.error(CARD_EXCEPTION, id, e, LocalDateTime.now());
        }
        return "cards/edit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("card") @Valid Card card,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("card", card);
            model.addAttribute("cardId", id);

            return "cards/edit";
        }
        try {
            Client client = cardsService.findById(id).getClient();
            card.setClient(client);
        } catch (CardNotFoundException e) {
            log.error(CARD_EXCEPTION, id, e, LocalDateTime.now());
        }
        cardsService.update(id, card);
        return String.format("redirect:/cards/%d", id);
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id, @RequestParam(CLIENT_ID) Long clientId) {
        cardsService.delete(id);
        return String.format("redirect:/clients/%d", clientId);
    }
}
