package br.com.tgid.gatewayapi.business.controllers;

import br.com.tgid.gatewayapi.business.dtos.request.CreateBusinessDtoRequest;
import br.com.tgid.gatewayapi.business.dtos.request.UpdateBusinessDtoRequest;
import br.com.tgid.gatewayapi.business.dtos.response.BusinessDtoResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public interface BusinessController {

    @ApiOperation(value = "Cria nova empresa", produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Sucesso", response = BusinessDtoResponse.class),
            @ApiResponse(code = 404, message = "Empresa não encontrada"),
            @ApiResponse(code = 400, message = "Request inválido")
    })
    public ResponseEntity<BusinessDtoResponse> create(final CreateBusinessDtoRequest dto);

    @ApiOperation(value = "Lista todas empresas", produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso", response = BusinessDtoResponse.class),
            @ApiResponse(code = 404, message = "Empresa não encontrada"),
            @ApiResponse(code = 400, message = "Request inválido")
    })
    public ResponseEntity<Collection<BusinessDtoResponse>> search();

    @ApiOperation(value = "Listar empresa por id", produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso", response = BusinessDtoResponse.class),
            @ApiResponse(code = 404, message = "Empresa não encontrada"),
            @ApiResponse(code = 400, message = "Request inválido")
    })
    public ResponseEntity<BusinessDtoResponse> find(final Long id);

    @ApiOperation(value = "Editar empresa", produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Sucesso", response = BusinessDtoResponse.class),
            @ApiResponse(code = 404, message = "Empresa não encontrada"),
            @ApiResponse(code = 400, message = "Request inválido")
    })
    public ResponseEntity<BusinessDtoResponse> update(final Long id, final UpdateBusinessDtoRequest dto);

    @ApiOperation(value = "Excluir empresa", produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Sucesso", response = BusinessDtoResponse.class),
            @ApiResponse(code = 404, message = "Empresa não encontrada"),
            @ApiResponse(code = 400, message = "Request inválido")
    })
    public ResponseEntity<Void> delete(final Long id);
}
