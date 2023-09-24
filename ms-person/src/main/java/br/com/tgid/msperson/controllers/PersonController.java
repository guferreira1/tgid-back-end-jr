package br.com.tgid.msperson.controllers;


import br.com.tgid.msperson.dtos.request.UpdatePersonDtoRequest;
import br.com.tgid.msperson.dtos.request.UpdateWalletDtoRequest;
import br.com.tgid.msperson.dtos.response.WalletDtoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.tgid.msperson.dtos.request.CreatePersonDtoRequest;
import br.com.tgid.msperson.dtos.response.PersonDtoResponse;
import br.com.tgid.msperson.services.PersonService;
import io.swagger.annotations.ApiOperation;

import java.util.Collection;

@RestController
@RequestMapping("api/v1/persons")
public class PersonController {
	
	private final PersonService personService;
	
	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@ApiOperation("Create person")
	@PostMapping("")
	public ResponseEntity<PersonDtoResponse> create(
			@RequestBody(required = true) final CreatePersonDtoRequest dto) {
		return personService.create(dto);
	}

	@ApiOperation("Search all persons")
	@GetMapping("")
	public ResponseEntity<Collection<PersonDtoResponse>> search() {
		return personService.search();
	}

	@ApiOperation("Find one person by id")
	@GetMapping("/{id}")
	public ResponseEntity<PersonDtoResponse> find(
			@PathVariable(required = true) final Long id) {
				return personService.find(id);
	}

	@ApiOperation("Edit person")
	@PatchMapping("/{id}")
	public ResponseEntity<PersonDtoResponse> update(
			@PathVariable(required = true) final Long id,
			@RequestBody(required = true) final UpdatePersonDtoRequest dto) {
		return personService.update(id, dto);
	}

	@ApiOperation("Delete person")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(
			@PathVariable(required = true) final Long id) {
		return personService.delete(id);
	}

	@ApiOperation("Update wallet")
	@PatchMapping("wallet/{id}")
	public ResponseEntity<WalletDtoResponse> update(
			@PathVariable(required = true) final Long id,
			@RequestBody(required = true) final UpdateWalletDtoRequest dto) {
		return this.personService.update(id, dto);
	}
}
