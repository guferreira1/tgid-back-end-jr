package br.com.tgid.msbusiness.models;

import br.com.tgid.msbusiness.constants.TaxesEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Entity
public class Taxes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    private TaxesEnum taxesValue;

    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public TaxesEnum getTaxesValue() {
        return taxesValue;
    }

    public void setTaxesValue(TaxesEnum taxesValue) {
        this.taxesValue = taxesValue;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }
}
