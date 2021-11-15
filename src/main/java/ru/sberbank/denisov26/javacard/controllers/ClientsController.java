package ru.sberbank.denisov26.javacard.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.denisov26.javacard.exceptions.ClientNotFoundException;
import ru.sberbank.denisov26.javacard.exceptions.PassportError;
import ru.sberbank.denisov26.javacard.models.Client;
import ru.sberbank.denisov26.javacard.services.ClientsService;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Slf4j
@Controller
@RequestMapping("clients")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class ClientsController {

    private final ClientsService clientsService;

    private static final String CLIENTS_EDIT = "clients/edit";

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
            log.error("Client {} not found!!! Exception: {}, Date: {}", id, e, LocalDateTime.now());
        }
        return "clients/show";
    }

    @GetMapping("/new")
    public String newClient(@ModelAttribute("client" ) Client client) {

        return "clients/new";
    }

    @PostMapping
    public String create(@ModelAttribute("client") @Valid Client client, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/clients/new"; //если форма имеет не валидные значения
        }

            Client result = clientsService.save(client);
            return String.format("redirect:/clients/%d", result.getId());
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        try {
            model.addAttribute("client", clientsService.findById(id));
        } catch (ClientNotFoundException e) {
            log.error("Client {} not found!!! Exception: {}, Date: {}", id, e, LocalDateTime.now());
        }
        return CLIENTS_EDIT;
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("client") @Valid Client client, BindingResult bindingResult,
                         @PathVariable("id") Long id, Model model) {
        if (bindingResult.hasErrors()) {
            return CLIENTS_EDIT;
        }
        try {
            clientsService.update(id, client);
        } catch (PassportError passportError) {
            model.addAttribute("passportError", passportError.toString());
            return CLIENTS_EDIT;
        }
        return String.format("redirect:/clients/%d", id);
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        clientsService.delete(id);
        return "redirect:/clients";
    }
}
