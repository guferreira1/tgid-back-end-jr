package br.com.tgid.msbusiness.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Collection;

public class BusinessDtoResponse {

    @JsonProperty
    private Long id;

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
    private Collection<TaxesDtoResponse> taxes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
