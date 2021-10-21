package ru.sberbank.denisov26.javacard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.denisov26.javacard.models.client.Client;
import ru.sberbank.denisov26.javacard.repository.CardRepository;
import ru.sberbank.denisov26.javacard.repository.ClientRepository;

import javax.validation.Valid;

//@RestController
//@RequestMapping("/clients")
class ClientsController {
    private final ClientRepository clientDao;
    private final CardRepository cardDao;

//    clientDaoJdbcTemplate cardDaoJdbcTemplate
//    @Autowired
//    public ClientsController(@Qualifier("clientDaoJdbcTemplate") ClientDao clientDao,
//                             @Qualifier("cardDaoJdbcTemplate") CardDao cardDao) {
//        this.clientDao = clientDao;
//        this.cardDao = cardDao;
//    }

    @Autowired
    public ClientsController(ClientRepository clientDao, CardRepository cardDao) {
        this.clientDao = clientDao;
        this.cardDao = cardDao;
    }

    @GetMapping()
    public String index(Model model) {
        //        получим всех людей из DAO и передадим на отображение в представление
        model.addAttribute("clients", clientDao.findAll());
        return "clients/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        //        мы получим одного человека из DAO и передадим на отображение в представление
        model.addAttribute("client", clientDao.findById(id));
//        model.addAttribute("client", clientDao.findAllCardByClientId(id));
//        model.addAttribute("cards", cardDao.findById(id));
        return "clients/showClient";
    }

//    форма для создания нового клиента
    @GetMapping("/new")
    public String newClient(@ModelAttribute("client" ) Client client/*Model model*/) {
//        этот вариант если в параметрах передаем модель
//        model.addAttribute("client", new Client());
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
        clientDao.save(client);
//        перенаправляем на страницу следующим способом
        return "redirect:/clients";
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
//        clientDao.update(id, client);
        return "redirect:/clients";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
//        clientDao.delete(id);
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
