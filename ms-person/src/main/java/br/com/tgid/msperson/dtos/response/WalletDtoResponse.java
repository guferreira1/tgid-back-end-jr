package br.com.tgid.msperson.dtos.response;

import br.com.tgid.msperson.models.Person;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class WalletDtoResponse {

    @JsonProperty
    private String id;

    @JsonProperty
    private BigDecimal amount;

    @JsonProperty
    private Person person;

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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
