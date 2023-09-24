package br.com.tgid.gatewayapi.person.services;

import br.com.tgid.gatewayapi.person.clients.PersonClient;
import br.com.tgid.gatewayapi.person.dtos.request.CreatePersonDtoRequest;
import br.com.tgid.gatewayapi.person.dtos.request.UpdatePersonDtoRequest;
import br.com.tgid.gatewayapi.person.dtos.response.PersonDtoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PersonService {

    private final PersonClient personClient;

    public PersonService(PersonClient personClient) {
        this.personClient = personClient;
    }

    public ResponseEntity<PersonDtoResponse> create(CreatePersonDtoRequest dto) {

       return personClient.create(dto);
    }

    public ResponseEntity<Collection<PersonDtoResponse>> search() {

        return this.personClient.search();
    }

    public ResponseEntity<PersonDtoResponse> find(final Long id) {

        return this.personClient.find(id);
    }

    public ResponseEntity<PersonDtoResponse> updade(Long id, UpdatePersonDtoRequest dto) {

        return this.personClient.update(id, dto);
    }

    public ResponseEntity<Void> delete(Long id) {

        return this.personClient.delete(id);
    }
}
