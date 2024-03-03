package ru.taf.digitalbookkeepingwithboot.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.taf.digitalbookkeepingwithboot.dto.PersonDTO;
import ru.taf.digitalbookkeepingwithboot.models.Person;
import ru.taf.digitalbookkeepingwithboot.services.PeopleService;

@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PersonDTO personDTO = (PersonDTO) target;

        if (peopleService.findByUserName(personDTO.getName()).isPresent())
            errors.rejectValue("username", "", "человек с таким именем уже существует");
    }
}
