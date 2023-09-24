package br.com.tgid.gatewayapi.person.dtos.response;


import java.math.BigDecimal;

public class WalletDtoResponse {
    private String id;

    private BigDecimal amount;

    private PersonDtoResponse person;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PersonDtoResponse getPerson() {
        return person;
    }

    public void setPerson(PersonDtoResponse person) {
        this.person = person;
    }
}
