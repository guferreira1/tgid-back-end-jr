package br.com.tgid.msbusiness.dtos.request;

import br.com.tgid.msbusiness.models.Taxes;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Collection;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateBusinessDtoRequest {
    @JsonProperty
    private String name;

    @JsonProperty
    private String cnpj;

    @JsonProperty
    private String email;

    @JsonProperty
    private String password;

    @JsonProperty
    private BigDecimal totalValue;

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

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public Collection<Taxes> getTaxes() {
        return taxes;
    }

    public void setTaxes(Collection<Taxes> taxes) {
        this.taxes = taxes;
    }
}
