package br.com.tgid.gatewayapi.business.controllers;

import br.com.tgid.gatewayapi.business.dtos.request.CreateBusinessDtoRequest;
import br.com.tgid.gatewayapi.business.dtos.request.UpdateBusinessDtoRequest;
import br.com.tgid.gatewayapi.business.dtos.response.BusinessDtoResponse;
import br.com.tgid.gatewayapi.business.services.BusinessService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/v1/business")
public class BusinessControllerImpl implements BusinessController {

    private final BusinessService businessService;

    public BusinessControllerImpl(BusinessService businessService) {
        this.businessService = businessService;
    }

    @Override
    @PostMapping("")
    public ResponseEntity<BusinessDtoResponse> create(
            @RequestBody(required = true) final CreateBusinessDtoRequest dto) {
        return this.businessService.create(dto);
    }

    @Override
    @GetMapping("")
    public ResponseEntity<Collection<BusinessDtoResponse>> search() {
        return this.businessService.search();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<BusinessDtoResponse> find(
            @PathVariable(required = true) final Long id) {
        return this.businessService.find(id);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<BusinessDtoResponse> update(
            @PathVariable(required = true) final Long id,
            @RequestBody(required = true) final UpdateBusinessDtoRequest dto) {
        return this.businessService.update(id, dto);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable(required = true) final Long id) {
        return this.businessService.delete(id);
    }
}
