package ru.taf.digitalbookkeepingwithboot.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.taf.digitalbookkeepingwithboot.models.Person;
import ru.taf.digitalbookkeepingwithboot.services.BooksService;
import ru.taf.digitalbookkeepingwithboot.services.PeopleService;

@Controller
@RequestMapping("/people")
public class PeopleControllers {

    private final PeopleService peopleService;

    @Autowired
    public PeopleControllers(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people", peopleService.findAll());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person", peopleService.findOne(id));
        model.addAttribute("books", peopleService.findOne(id).getBooks());
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("person", peopleService.findOne(id));
        return "people/edit";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "people/new";
        peopleService.save(person);
        return "redirect:/people";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "people/edit";
        peopleService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        peopleService.delete(id);
        return "redirect:/people";
    }
}
