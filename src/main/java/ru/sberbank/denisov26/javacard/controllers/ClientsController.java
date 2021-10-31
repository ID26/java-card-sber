package ru.sberbank.denisov26.javacard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.denisov26.javacard.models.client.*;
import ru.sberbank.denisov26.javacard.services.ClientsService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/clients")
class ClientsController {

    private final ClientsService clientsService;

    @Autowired
    public ClientsController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("clients", clientsService.findAll());
        return "/clients/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        //        мы получим одного человека из DAO и передадим на отображение в представление
        model.addAttribute("client", clientsService.findById(id));
//        model.addAttribute("client", clientDao.findAllCardByClientId(id));
//        model.addAttribute("cards", cardDao.findById(id));
        return "clients/showClient";
    }

    //    форма для создания нового клиента
    @GetMapping("/new")
    public String newClient(@ModelAttribute("client" ) Client client /*Model model*/) {
//        этот вариант если в параметрах передаем модель
//        model.addAttribute("client", new Client());
        for (int i = 0; i < 2 ; i++) {
            client.getPhones().add(new Phone());
        }
        return "clients/new";
    }

    //    из пост запроса берем данные и добавляем нового клиента в базу данных
    @PostMapping
    public String create(@ModelAttribute("client") @Valid Client client,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "clients/new"; //если форма имеет не валидные значения
        }

//        сохраняем в базу данных
        clientsService.save(client);
//        перенаправляем на страницу следующим способом
        return "redirect:/clients";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("client", clientsService.findById(id));
        return "clients/edit";
    }

//    @PatchMapping("/{id}")
    @PostMapping("/{id}")
    public String update(@ModelAttribute("client") @Valid Client client, BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "clients/edit";
        }
        clientsService.update(id, client);
        return "redirect:/clients";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        clientsService.delete(id);
        return "redirect:/clients";
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
