package br.com.tgid.gatewayapi.person.controllers;

import br.com.tgid.gatewayapi.person.dtos.request.CreatePersonDtoRequest;
import br.com.tgid.gatewayapi.person.dtos.request.UpdatePersonDtoRequest;
import br.com.tgid.gatewayapi.person.dtos.response.PersonDtoResponse;
import br.com.tgid.gatewayapi.person.services.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/v1/persons")
public class PersonControllerImpl implements PersonController{

    private final PersonService personService;

    public PersonControllerImpl(PersonService personService) {
        this.personService = personService;
    }

    @Override
    @PostMapping("")
    public ResponseEntity<PersonDtoResponse> create(@RequestBody(required = true) CreatePersonDtoRequest dto) {
        return personService.create(dto);
    }

    @Override
    @GetMapping("")
    public ResponseEntity<Collection<PersonDtoResponse>> search() {
        return this.personService.search();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<PersonDtoResponse> find(
            @PathVariable(required = true) final Long id) {
        return this.personService.find(id);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<PersonDtoResponse> update(
            @PathVariable(required = true) final Long id,
            @RequestBody(required = true) final UpdatePersonDtoRequest dto) {
        return this.personService.updade(id, dto);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable(required = true) final Long id) {
        return this.personService.delete(id);
    }
}
