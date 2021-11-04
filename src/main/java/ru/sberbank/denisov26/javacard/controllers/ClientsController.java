package ru.sberbank.denisov26.javacard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.denisov26.javacard.exceptions.ClientNotFoundException;
import ru.sberbank.denisov26.javacard.models.client.Client;
import ru.sberbank.denisov26.javacard.models.client.Phone;
import ru.sberbank.denisov26.javacard.services.ClientsService;

import javax.validation.Valid;

@Controller
@RequestMapping("clients")
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
        try {
            model.addAttribute("client", clientsService.findById(id));
        } catch (ClientNotFoundException e) {
            System.err.println(e);
        }
        return "clients/show";
    }

    @GetMapping("/new")
    public String newClient(@ModelAttribute("client" ) Client client /*Model model*/) {
        for (int i = 0; i < 2 ; i++) {
            client.getPhones().add(new Phone());
        }
        return "clients/new";
    }

    @PostMapping
    public String create(@ModelAttribute("client") @Valid Client client,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "clients/new"; //если форма имеет не валидные значения
        }

        clientsService.save(client);
        return "redirect:/clients";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        try {
            model.addAttribute("client", clientsService.findById(id));
        } catch (ClientNotFoundException e) {
            System.err.println(e);
        }
        return "clients/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("client") @Valid Client client, BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "clients/edit";
        }
        clientsService.update(id, client);
        return "redirect:/clients";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        clientsService.delete(id);
        return "redirect:/clients";
    }
}
