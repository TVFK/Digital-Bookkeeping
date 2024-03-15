package ru.taf.digitalbookkeepingwithboot.services;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.taf.digitalbookkeepingwithboot.models.Person;
import ru.taf.digitalbookkeepingwithboot.repositories.PeopleRepository;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final PasswordEncoder passwordEncoder;
    private final PeopleRepository peopleRepository;


    @Transactional
    public void register(Person person){
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_USER");
        peopleRepository.save(person);
    }
}
