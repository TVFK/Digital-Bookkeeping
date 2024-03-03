package ru.taf.digitalbookkeepingwithboot.services;

import ru.taf.digitalbookkeepingwithboot.models.Person;
import ru.taf.digitalbookkeepingwithboot.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    public Optional<Person> findByUserName(String name){
        return peopleRepository.findByName(name);
    }

    public Person findOne(int id){
        Person person = peopleRepository.findById(id).orElse(null);
        Hibernate.initialize(person != null ? person.getBooks() : null);
        return person;
    }

    @Transactional
    public void save(Person person){
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson){
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }

}
