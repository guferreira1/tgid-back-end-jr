package br.com.tgid.gatewayapi.business.dtos.response;


import java.util.Collection;

public class BusinessDtoResponse {
    private Long id;

    private String name;

    private String cnpj;

    private String email;

    private String password;

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

    public Collection<TaxesDtoResponse> getTaxes() {
        return taxes;
    }

    public void setTaxes(Collection<TaxesDtoResponse> taxes) {
        this.taxes = taxes;
    }
}
