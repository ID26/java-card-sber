package ru.sberbank.denisov26.javacard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.denisov26.javacard.models.client.Address;
import ru.sberbank.denisov26.javacard.models.client.Client;
import ru.sberbank.denisov26.javacard.models.client.Phone;
import ru.sberbank.denisov26.javacard.services.AddressService;

import javax.validation.Valid;

@Controller
@RequestMapping("/address")
public class AddressesController {

    private final AddressService addressService;
    @Autowired
    public AddressesController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/new")
    public String newAddress(@ModelAttribute("address" ) Address address) {
        return "addresses/new";
    }

    //    из пост запроса берем данные и добавляем нового клиента в базу данных
    @PostMapping("/new")
    public String create(@ModelAttribute("address") @Valid Address address,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "address/new"; //если форма имеет не валидные значения
        }

//        сохраняем в базу данных
//        addressService.save(client);
//        перенаправляем на страницу следующим способом
        return "redirect:/clients/" + address.getClient().getId();
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
//        model.addAttribute("client", clientDao.show(id));
        return "clients/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("client") @Valid Client client, BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "clients/edit";
        }
//        addressService.update(/*id, */client);
        return "redirect:/clients";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        addressService.delete(id);
        return "redirect:/clients";
    }
}
