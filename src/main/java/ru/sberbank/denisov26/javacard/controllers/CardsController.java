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


//    @GetMapping()
//    public String index(Model model) {
//        model.addAttribute("cards", cardsService.findAll());
//        return "/clients/index";
//    }
//
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
            try {
                Card card = cardsService.findById(id);
                model.addAttribute("card", card);
                model.addAttribute("clientId", 23);
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
            model.addAttribute("client", cardsService.findById(id));
        } catch (CardNotFoundException e) {
            System.err.println(e);
        }
        return "cards/edit";
    }
//
//    //    @PatchMapping("/{id}")
//    @PostMapping("/{id}")
//    public String update(@ModelAttribute("client") @Valid Client client, BindingResult bindingResult,
//                         @PathVariable("id") Long id) {
//        if (bindingResult.hasErrors()) {
//            return "clients/edit";
//        }
//        cardsService.update(id, client);
//        return "redirect:/clients";
//    }
//
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id, @RequestParam("clientId") Long clientId) {
        System.out.println(clientId);
        cardsService.delete(id);
        return String.format("redirect:/clients/%d", clientId);
    }

//    @GetMapping("/newCard/{id}")
//    public String newCard(@PathVariable("id") Long id, @ModelAttribute("card") Card card) {
//        Client client = clientDao.show(id);
//        card.setOwner(client.getId());
//        card.setBillingAddress(client.getAddress());
//        card.setNameOnCard(String.format("%s %s", client.getGivenName(), client.getSurName()).toUpperCase());
//        card.setCardNumber("000000000000" + String.valueOf(new Random().nextInt(8999) + 1000));
//        card.setCardVerificationCode(String.valueOf(new Random().nextInt(899) + 100));
//        card.setValidFrom(LocalDate.now());
//        card.setValidFrom(card.getValidFrom().plusYears(3));
//
//        return "cards/newCard";
//    }
}
