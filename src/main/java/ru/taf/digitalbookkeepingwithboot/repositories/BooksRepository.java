package ru.taf.digitalbookkeepingwithboot.repositories;

import ru.taf.digitalbookkeepingwithboot.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    List<Book> findByNameStartingWith(String startingWith);


}
