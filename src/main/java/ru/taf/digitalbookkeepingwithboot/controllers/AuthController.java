package ru.taf.digitalbookkeepingwithboot.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.taf.digitalbookkeepingwithboot.dto.PersonDTO;
import ru.taf.digitalbookkeepingwithboot.models.Person;
import ru.taf.digitalbookkeepingwithboot.security.JWTUtil;
import ru.taf.digitalbookkeepingwithboot.services.RegistrationService;
import ru.taf.digitalbookkeepingwithboot.util.PersonValidator;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final RegistrationService registrationService;
    private final PersonValidator personValidator;
    private final JWTUtil jwtUtil;
    private final ModelMapper modelMapper;

    public AuthController(RegistrationService registrationService, PersonValidator personValidator, JWTUtil jwtUtil, ModelMapper modelMapper) {
        this.registrationService = registrationService;
        this.personValidator = personValidator;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("personDTO") PersonDTO personDTO){
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("personDTO") @Valid PersonDTO personDTO,
                                      BindingResult bindingResult){

        personValidator.validate(personDTO, bindingResult);
        if(bindingResult.hasErrors()) {
            return "/auth/registration";
        }

        registrationService.register(converToPerson(personDTO));

        return "redirect:/auth/login";
    }

    @GetMapping("/admin")
    public String adminPage(){
        return "/auth/admin";
    }

    @GetMapping("/hello")
    public String userHello(){
        return "/auth/hello";
    }

    public Person converToPerson(PersonDTO personDTO){
        return modelMapper.map(personDTO, Person.class);
    }
}
