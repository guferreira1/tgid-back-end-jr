package br.com.tgid.gatewayapi.business.clients;

import br.com.tgid.gatewayapi.business.dtos.request.CreateBusinessDtoRequest;
import br.com.tgid.gatewayapi.business.dtos.request.UpdateBusinessDtoRequest;
import br.com.tgid.gatewayapi.business.dtos.response.BusinessDtoResponse;
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

import static br.com.tgid.gatewayapi.configs.Variables.HOST_BUSINESS;

@Component
public class BusinessClient {

    private final String hostMsBusiness;
    private final RestTemplate restTemplate;

    public BusinessClient(final RestTemplate restTemplate, @Value(HOST_BUSINESS) String hostMsBusiness) {
        this.hostMsBusiness = hostMsBusiness;
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<BusinessDtoResponse> create(final CreateBusinessDtoRequest dto) {
        final URI uri = UriComponentsBuilder.fromHttpUrl(hostMsBusiness)
                .path("api/v1/business")
                .build()
                .toUri();
        final RequestEntity<CreateBusinessDtoRequest> request = RequestEntity.post(uri)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(dto);

        return restTemplate.exchange(request,
                new ParameterizedTypeReference<BusinessDtoResponse>() {});
    }

    public ResponseEntity<Collection<BusinessDtoResponse>> search() {
        final URI uri = UriComponentsBuilder.fromHttpUrl(hostMsBusiness)
                .path("api/v1/business")
                .build()
                .toUri();
        final RequestEntity<Void> request = RequestEntity.get(uri)
                .accept(MediaType.APPLICATION_JSON)
                .build();

        return restTemplate.exchange(request,
                new ParameterizedTypeReference<Collection<BusinessDtoResponse>>() {});
    }

    public ResponseEntity<BusinessDtoResponse> find(final Long id) {
        final URI uri = UriComponentsBuilder.fromHttpUrl(hostMsBusiness)
                .path("api/v1/business/{id}")
                .build(id);

        final RequestEntity<Void> request = RequestEntity.get(uri)
                .accept(MediaType.APPLICATION_JSON)
                .build();

        return restTemplate.exchange(request,
                new ParameterizedTypeReference<BusinessDtoResponse>() {});
    }

    public ResponseEntity<BusinessDtoResponse> update(Long id, UpdateBusinessDtoRequest dto) {
        final URI uri = UriComponentsBuilder.fromHttpUrl(hostMsBusiness)
                .path("api/v1/business/{id}")
                .build(id);

        final RequestEntity<UpdateBusinessDtoRequest> request = RequestEntity.patch(uri)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(dto);

        return restTemplate.exchange(request,
                new ParameterizedTypeReference<BusinessDtoResponse>() {});
    }

    public ResponseEntity<Void> delete(final Long id) {
        final URI uri = UriComponentsBuilder.fromHttpUrl(hostMsBusiness)
                .path("api/v1/business/{id}")
                .build(id);

        final RequestEntity<Void> request = RequestEntity.delete(uri)
                .accept(MediaType.APPLICATION_JSON)
                .build();

        return restTemplate.exchange(request,
                new ParameterizedTypeReference<Void>() {});
    }
}
