package br.com.tgid.gatewayapi.business.dtos.request;

import br.com.tgid.gatewayapi.business.dtos.response.TaxesDtoResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Collection;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateBusinessDtoRequest {

    private String name;

    private String cnpj;

    private String email;

    private String password;

    private BigDecimal totalValue;

    private Collection<TaxesDtoResponse> taxes;

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

    public Collection<TaxesDtoResponse> getTaxes() {
        return taxes;
    }

    public void setTaxes(Collection<TaxesDtoResponse> taxes) {
        this.taxes = taxes;
    }
}
