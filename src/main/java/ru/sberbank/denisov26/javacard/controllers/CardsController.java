package ru.sberbank.denisov26.javacard.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.denisov26.javacard.exceptions.CardNotFoundException;
import ru.sberbank.denisov26.javacard.exceptions.ClientNotFoundException;
import ru.sberbank.denisov26.javacard.models.client.Card;
import ru.sberbank.denisov26.javacard.services.CardsService;
import ru.sberbank.denisov26.javacard.services.ClientsService;
import ru.sberbank.denisov26.javacard.utils.CardGenerator;

import javax.validation.Valid;

@Controller
@RequestMapping("cards")
public class CardsController {
    private final CardsService cardsService;
    private final ClientsService clientsService;

    @Autowired
    public CardsController(CardsService cardsService, ClientsService clientsService) {
        this.cardsService = cardsService;
        this.clientsService = clientsService;
    }


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
                model.addAttribute("clientId", card.getClient().getId());
            } catch (CardNotFoundException e) {
                System.err.println(e);
            }
        return "cards/show";
    }

    @GetMapping("/new/{id}")
    public String newCard(@PathVariable("id") Long id, Model model/*@ModelAttribute("card" ) Card card*/) {
        try {
           Card card = CardGenerator.generateCard(clientsService.findById(id));;
           model.addAttribute("card", card);
           model.addAttribute("clientId", id);// put here for back step
        } catch (ClientNotFoundException e) {
            System.err.println(e);
        }
        return "cards/new";
    }

    @PostMapping("/new/{id}")
    public String create(@PathVariable("id") Long id, @ModelAttribute("card") @Valid Card card,
                         BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "cards/new/" + id; //если форма имеет не валидные значения
//        }
        try {
            card.setClient(clientsService.findById(id));
        } catch (ClientNotFoundException e) {
            e.printStackTrace();
        }
        cardsService.save(card);
        return String.format("redirect:/clients/%d", id);
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        try {
            Card card = cardsService.findById(id);
            model.addAttribute("card", card);
        } catch (CardNotFoundException e) {
            System.err.println(e);
        }
        return "cards/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("card") @Valid Card card, BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "clients/edit";
        }
        cardsService.update(id, card);
        return String.format("redirect:/cards/%d", id);
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id, @RequestParam("clientId") Long clientId) {
        System.out.println(clientId);
        cardsService.delete(id);
        return String.format("redirect:/clients/%d", clientId);
    }
}
