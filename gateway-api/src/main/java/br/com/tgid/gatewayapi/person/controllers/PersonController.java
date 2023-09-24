package br.com.tgid.gatewayapi.person.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import br.com.tgid.gatewayapi.person.dtos.request.CreatePersonDtoRequest;
import br.com.tgid.gatewayapi.person.dtos.request.UpdatePersonDtoRequest;
import br.com.tgid.gatewayapi.person.dtos.response.PersonDtoResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

public interface PersonController {
    @ApiOperation(value = "Cria novo cliente", produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Sucesso", response = PersonDtoResponse.class),
            @ApiResponse(code = 404, message = "Pessoa não encontrada"),
            @ApiResponse(code = 400, message = "Request inválido")
    })
    public ResponseEntity<PersonDtoResponse> create(final CreatePersonDtoRequest dto);

    @ApiOperation(value = "Lista todos os clientes", produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso", response = PersonDtoResponse.class),
            @ApiResponse(code = 404, message = "Pessoa não encontrada"),
            @ApiResponse(code = 400, message = "Request inválido")
    })
    public ResponseEntity<Collection<PersonDtoResponse>> search();

    @ApiOperation(value = "Lista um cliente", produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso", response = PersonDtoResponse.class),
            @ApiResponse(code = 404, message = "Pessoa não encontrada"),
            @ApiResponse(code = 400, message = "Request inválido")
    })
    public ResponseEntity<PersonDtoResponse> find(final Long id);

    @ApiOperation(value = "Editar um cliente", produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Sucesso", response = PersonDtoResponse.class),
            @ApiResponse(code = 404, message = "Pessoa não encontrada"),
            @ApiResponse(code = 400, message = "Request inválido")
    })
    public ResponseEntity<PersonDtoResponse> update(final Long id, final UpdatePersonDtoRequest dto);

    @ApiOperation(value = "Deletar um cliente", produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Sucesso", response = PersonDtoResponse.class),
            @ApiResponse(code = 404, message = "Pessoa não encontrada"),
            @ApiResponse(code = 400, message = "Request inválido")
    })
    public ResponseEntity<Void> delete(final Long id);
}
