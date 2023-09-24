package br.com.tgid.msbusiness.controllers;

import br.com.tgid.msbusiness.dtos.request.CreateBusinessDtoRequest;
import br.com.tgid.msbusiness.dtos.request.UpdateBusinessDtoRequest;
import br.com.tgid.msbusiness.dtos.response.BusinessDtoResponse;
import br.com.tgid.msbusiness.services.BusinessService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/v1/business")
public class BusinessController {

    private final BusinessService businessService;

    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @ApiOperation("Create business")
    @PostMapping("")
    public ResponseEntity<BusinessDtoResponse> create(
            @RequestBody(required = true) final CreateBusinessDtoRequest dto) {

        return this.businessService.create(dto);
    }

    @ApiOperation("Search all business")
    @GetMapping("")
    public ResponseEntity<Collection<BusinessDtoResponse>> search() {
        return this.businessService.search();
    }

    @ApiOperation("Find business")
    @GetMapping("/{id}")
    public ResponseEntity<BusinessDtoResponse> search(
            @PathVariable(required = true) final Long id) {
        return this.businessService.find(id);
    }

    @ApiOperation("Update business")
    @PatchMapping("/{id}")
    public ResponseEntity<BusinessDtoResponse> update(
            @PathVariable(required = true) final Long id,
            @RequestBody(required = true) final UpdateBusinessDtoRequest dto) {

        return this.businessService.update(id, dto);
    }

    @ApiOperation("Delete business")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable(required = true) final Long id) {

        return this.businessService.delete(id);
    }
}
