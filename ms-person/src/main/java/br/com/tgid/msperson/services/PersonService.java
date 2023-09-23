package br.com.tgid.msperson.services;

import static org.springframework.http.HttpStatus.CREATED;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.tgid.msperson.configs.BusinessException;
import br.com.tgid.msperson.dtos.request.UpdatePersonDtoRequest;
import br.com.tgid.msperson.dtos.response.WalletDtoResponse;
import br.com.tgid.msperson.models.Person;
import br.com.tgid.msperson.models.Wallet;
import br.com.tgid.msperson.repositories.PersonRepository;
import br.com.tgid.msperson.repositories.WalletRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.tgid.msperson.dtos.request.CreatePersonDtoRequest;
import br.com.tgid.msperson.dtos.response.PersonDtoResponse;

import java.util.*;

@Service
public class PersonService {

	private static final Logger LOGGER = LogManager.getLogger(PersonService.class.getName());
	private final PersonRepository personRepository;
	private final WalletRepository walletRepository;

	public PersonService(PersonRepository personRepository, WalletRepository walletRepository) {
		this.personRepository = personRepository;
		this.walletRepository = walletRepository;
	}
	public ResponseEntity<PersonDtoResponse> create(@Valid @NotNull final CreatePersonDtoRequest dto) {

		String cpfFormatted = dto.getCpf().replaceAll("[^0-9]", "");

		Person person = new Person();
		person.setCpf(cpfFormatted);
		person.setEmail(dto.getEmail());
		person.setPassword(dto.getPassword());
		person.setFirstName(dto.getFirstName());
		person.setLastName(dto.getLastName());

		try {
			LOGGER.info("Saving person in database...");
			personRepository.save(person);
			LOGGER.info("Saved.");


			this.createWallet(person);
		} catch (RuntimeException ex) {
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		}

		PersonDtoResponse personResponse = new PersonDtoResponse();
		personResponse.setId(person.getId());
		personResponse.setCpf(person.getCpf());
		personResponse.setEmail(person.getEmail());
		personResponse.setFirstName(person.getFirstName());
		personResponse.setLastName(person.getFirstName());
		personResponse.setPassword(person.getPassword());
		
		return ResponseEntity.status(CREATED).body(personResponse);
	}

	public ResponseEntity<Collection<PersonDtoResponse>> search() {

		Collection<PersonDtoResponse> personsResponse = new ArrayList<>();

		LOGGER.info("Search all persons in database...");
		Collection<Person> persons = this.personRepository.findAll();
		LOGGER.info("Found.");

		for (Person person : persons) {
			PersonDtoResponse p = new PersonDtoResponse();
			p.setId(person.getId());
			p.setFirstName(person.getFirstName());
			p.setLastName(person.getLastName());
			p.setCpf(person.getCpf());
			p.setEmail(person.getEmail());
			p.setPassword(person.getPassword());

			personsResponse.add(p);
		}

		return ResponseEntity.status(HttpStatus.OK).body(personsResponse);
	}

	public ResponseEntity<PersonDtoResponse> find(@Valid @NotNull @Min(1) final Long id) {
		try {
			LOGGER.info("Find person...");
			Optional<Person> person = this.personRepository.findById(id);
			LOGGER.info("Found.");

			if (person.isPresent()) {
				PersonDtoResponse personResponse = new PersonDtoResponse();
				personResponse.setPassword(person.get().getPassword());
				personResponse.setEmail(person.get().getEmail());
				personResponse.setCpf(person.get().getCpf());
				personResponse.setId(person.get().getId());
				personResponse.setFirstName(person.get().getFirstName());
				personResponse.setLastName(person.get().getLastName());

				Wallet wallet = person.get().getWallet();
				if (wallet != null) {
					WalletDtoResponse walletResponse = new WalletDtoResponse();
					walletResponse.setAmount(wallet.getAmount());
					personResponse.setWallet(walletResponse);
				}

				return ResponseEntity.status(HttpStatus.OK).body(personResponse);
			} else {
				throw new BusinessException(HttpStatus.NOT_FOUND.value(), "Usuário de id: " + id + " não encontrado");
			}
		} catch (RuntimeException ex) {
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ocorreu um erro ao buscar o usuário");
		}
	}

	public ResponseEntity<PersonDtoResponse> update(@Valid @NotNull @Min(1) final Long id, @Valid @NotNull final UpdatePersonDtoRequest dto) {

		LOGGER.info("Find person by id...");
		Person foundPerson = personRepository.findById(id)
				.orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND.value(), "Usuário não encontrado"));
		LOGGER.info("Found.");


		if (Objects.nonNull(dto.getEmail())) {
			foundPerson.setEmail(dto.getEmail());
		}
		if (Objects.nonNull(dto.getPassword())) {
			foundPerson.setPassword(dto.getPassword());
		}
		if (Objects.nonNull(dto.getFirstName())) {
			foundPerson.setFirstName(dto.getFirstName());
		}
		if (Objects.nonNull(dto.getLastName())) {
			foundPerson.setLastName(dto.getLastName());
		}
		if (Objects.nonNull(dto.getCpf())) {
			throw new BusinessException(HttpStatus.BAD_REQUEST.value(), "Não é possível alterar o CPF.");
		}

		LOGGER.info("Updating a person...");
		personRepository.save(foundPerson);
		LOGGER.info("Updated.");

		PersonDtoResponse personResponse = new PersonDtoResponse();
		personResponse.setFirstName(foundPerson.getFirstName());
		personResponse.setLastName(foundPerson.getLastName());
		personResponse.setCpf(foundPerson.getCpf());
		personResponse.setEmail(foundPerson.getEmail());
		personResponse.setId(foundPerson.getId());

		return ResponseEntity.status(CREATED.value()).body(personResponse);
	}

	public ResponseEntity<Void> delete(final Long id) {

		LOGGER.info("Find person by id...");
		Person foundPerson = personRepository.findById(id)
				.orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND.value(), "Usuário não encontrado"));
		LOGGER.info("Found.");

		LOGGER.info("Deleting a person...");
		personRepository.delete(foundPerson);
		LOGGER.info("Deleted.");

		return ResponseEntity.noContent().build();
	}
	private WalletDtoResponse createWallet(final Person personWallet) {
		Wallet createWallet = new Wallet();
		createWallet.setPerson(personWallet);

		LOGGER.info("Saving the wallet on the person...");
		walletRepository.save(createWallet);
		LOGGER.info("Saved.");

		WalletDtoResponse walletResponse = new WalletDtoResponse();
		walletResponse.setId(createWallet.getId().toString());
		walletResponse.setAmount(createWallet.getAmount());
		walletResponse.setPerson(createWallet.getPerson());

		return walletResponse;
	}
}
