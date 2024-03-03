package ru.taf.digitalbookkeepingwithboot.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.taf.digitalbookkeepingwithboot.models.Book;
import ru.taf.digitalbookkeepingwithboot.models.Person;
import ru.taf.digitalbookkeepingwithboot.services.BooksService;
import ru.taf.digitalbookkeepingwithboot.services.PeopleService;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    private final PeopleService peopleService;
    private final BooksService booksService;

    @Autowired
    public BookController(PeopleService peopleService, BooksService booksService) {
        this.peopleService = peopleService;
        this.booksService = booksService;
    }

    @GetMapping
    public String index(Model model,
                        @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                        @RequestParam(value = "books_per_page", required = false, defaultValue = "10") int booksPerPage,
                        @RequestParam(value = "sort_by_year", required = false) boolean sort_by_year){
        Page<Book> books = booksService.findAll(page, booksPerPage, sort_by_year);
        model.addAttribute("books", books);
        return "book/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id, @ModelAttribute("person") Person person){
        Book book = booksService.findOne(id);
        model.addAttribute("book", book);
        model.addAttribute("people", peopleService.findAll());
        model.addAttribute("owner", book.getOwner());
        return "book/show";
    }

    @GetMapping("/new")
    public String newBook(Model model){
        model.addAttribute("book", new Book());
        return "book/new";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("book", booksService.findOne(id));
        return "book/edit";
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam(value = "title", required = false) String title){
        List<Book> searchBooks = booksService.searchBooks(title);
        model.addAttribute("books", searchBooks);
        return "book/search";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "book/new";
        booksService.save(book);
        return "redirect:/book";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "book/edit";
        booksService.update(id, book);
        return "redirect:/book";
    }

    @PatchMapping("/{id}/set")
    public String setBook(@PathVariable("id") int id, @ModelAttribute("person") Person person){
        booksService.setBook(person.getId(), id);
        return "redirect:/book";
    }

    @PatchMapping("/{id}/liberate")
    public String liberateBook(@PathVariable("id") int id){
        booksService.liberateBook(id);
        return "redirect:/book";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        booksService.delete(id);
        return "redirect:/book";
    }
}
