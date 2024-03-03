package ru.taf.digitalbookkeepingwithboot.services;

import ru.taf.digitalbookkeepingwithboot.models.Book;
import ru.taf.digitalbookkeepingwithboot.models.Person;
import ru.taf.digitalbookkeepingwithboot.repositories.BooksRepository;
import ru.taf.digitalbookkeepingwithboot.repositories.PeopleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final PeopleRepository peopleRepository;
    private final BooksRepository booksRepository;

    public BooksService(PeopleRepository peopleRepository, BooksRepository booksRepository) {
        this.peopleRepository = peopleRepository;
        this.booksRepository = booksRepository;
    }

    public Page<Book> findAll(int pageNumber, int booksPerPage, boolean sort_by_year){
        Pageable pageable;
        if(sort_by_year) {
            pageable = PageRequest.of(pageNumber, booksPerPage, Sort.by("creationYear"));
        }
        else {
            pageable = PageRequest.of(pageNumber, booksPerPage);
        }
        return booksRepository.findAll(pageable);
    }

    public Book findOne(int id){
        return booksRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Book book){
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBooks){
        updatedBooks.setId(id);
        booksRepository.save(updatedBooks);
    }

    @Transactional
    public void delete(int id){
        booksRepository.deleteById(id);
    }

    @Transactional
    public void setBook(int personId, int bookId){
        Person person = peopleRepository.findById(personId).orElse(null);
        Book book = booksRepository.findById(bookId).orElse(null);

        assert person != null;
        book.setOwner(person);
        person.getBooks().add(book);
        book.setTimeTakeBook(LocalDateTime.now());
    }

    @Transactional
    public void liberateBook(int id){
        Book book = booksRepository.findById(id).orElse(null);
        book.setOwner(null);
    }

    public List<Book> searchBooks(String title){
        return booksRepository.findByNameStartingWith(title);
    }
}
