package br.com.tgid.gatewayapi.business.services;

import br.com.tgid.gatewayapi.business.clients.BusinessClient;
import br.com.tgid.gatewayapi.business.dtos.request.CreateBusinessDtoRequest;
import br.com.tgid.gatewayapi.business.dtos.request.UpdateBusinessDtoRequest;
import br.com.tgid.gatewayapi.business.dtos.response.BusinessDtoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BusinessService {

    private final BusinessClient businessClient;

    public BusinessService(BusinessClient businessClient) {
        this.businessClient = businessClient;
    }

    public ResponseEntity<BusinessDtoResponse> create(CreateBusinessDtoRequest dto) {
        return this.businessClient.create(dto);
    }

    public ResponseEntity<Collection<BusinessDtoResponse>> search() {
        return this.businessClient.search();
    }

    public ResponseEntity<BusinessDtoResponse> find(final Long id) {

        return this.businessClient.find(id);
    }

    public ResponseEntity<BusinessDtoResponse> update(Long id, UpdateBusinessDtoRequest dto) {

        return this.businessClient.update(id, dto);
    }

    public ResponseEntity<Void> delete(Long id) {

        return this.businessClient.delete(id);
    }
}
