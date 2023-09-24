package br.com.tgid.gatewayapi.person.clients;

import br.com.tgid.gatewayapi.person.dtos.request.CreatePersonDtoRequest;
import br.com.tgid.gatewayapi.person.dtos.request.UpdatePersonDtoRequest;
import br.com.tgid.gatewayapi.person.dtos.response.PersonDtoResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

import static br.com.tgid.gatewayapi.configs.Variables.HOST_PERSON;

@Component
public class PersonClient {

    private final String hostMsPerson;
    private final RestTemplate restTemplate;

    public PersonClient(final RestTemplate restTemplate, @Value(HOST_PERSON) String hostMsPerson) {
        this.hostMsPerson = hostMsPerson;
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<PersonDtoResponse> create(final CreatePersonDtoRequest dto) {
        final URI uri = UriComponentsBuilder.fromHttpUrl(hostMsPerson)
                .path("api/v1/persons")
                .build()
                .toUri();
        final RequestEntity<CreatePersonDtoRequest> request = RequestEntity.post(uri)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(dto);

        return restTemplate.exchange(request,
                new ParameterizedTypeReference<PersonDtoResponse>() {});
    }

    public ResponseEntity<Collection<PersonDtoResponse>> search() {
        final URI uri = UriComponentsBuilder.fromHttpUrl(hostMsPerson)
                .path("api/v1/persons")
                .build()
                .toUri();
        final RequestEntity<Void> request = RequestEntity.get(uri)
                .accept(MediaType.APPLICATION_JSON)
                .build();

        return restTemplate.exchange(request,
                new ParameterizedTypeReference<Collection<PersonDtoResponse>>() {});
    }

    public ResponseEntity<PersonDtoResponse> find(final Long id) {
        final URI uri = UriComponentsBuilder.fromHttpUrl(hostMsPerson)
                .path("api/v1/persons/{id}")
                .build(id);

        final RequestEntity<Void> request = RequestEntity.get(uri)
                .accept(MediaType.APPLICATION_JSON)
                .build();

        return restTemplate.exchange(request,
                new ParameterizedTypeReference<PersonDtoResponse>() {});
    }

    public ResponseEntity<PersonDtoResponse> update(Long id, UpdatePersonDtoRequest dto) {
        final URI uri = UriComponentsBuilder.fromHttpUrl(hostMsPerson)
                .path("api/v1/persons/{id}")
                .build(id);

        final RequestEntity<UpdatePersonDtoRequest> request = RequestEntity.patch(uri)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(dto);

        return restTemplate.exchange(request,
                new ParameterizedTypeReference<PersonDtoResponse>() {});
    }

    public ResponseEntity<Void> delete(final Long id) {
        final URI uri = UriComponentsBuilder.fromHttpUrl(hostMsPerson)
                .path("api/v1/persons/{id}")
                .build(id);

        final RequestEntity<Void> request = RequestEntity.delete(uri)
                .accept(MediaType.APPLICATION_JSON)
                .build();

        return restTemplate.exchange(request,
                new ParameterizedTypeReference<Void>() {});
    }
}
