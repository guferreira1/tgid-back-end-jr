package br.com.tgid.msbusiness.dtos.request;

import br.com.tgid.msbusiness.models.Taxes;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public class CreateBusinessDtoRequest {

    @JsonProperty
    private String name;

    @JsonProperty
    private String cnpj;

    @JsonProperty
    private String email;

    @JsonProperty
    private String password;

    @JsonProperty
    private Collection<Taxes> taxes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Taxes> getTaxes() {
        return taxes;
    }

    public void setTaxes(Collection<Taxes> taxes) {
        this.taxes = taxes;
    }
}
