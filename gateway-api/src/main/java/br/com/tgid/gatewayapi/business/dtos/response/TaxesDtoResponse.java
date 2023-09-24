package br.com.tgid.gatewayapi.business.dtos.response;

import br.com.tgid.gatewayapi.business.configs.constants.TaxesEnum;


import java.math.BigDecimal;

public class TaxesDtoResponse {
    private Long id;

    private BigDecimal value;

    private Long business;

    private TaxesEnum taxesValue;

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

    public Long getBusiness() {
        return business;
    }

    public void setBusiness(Long business) {
        this.business = business;
    }

    public TaxesEnum getTaxesValue() {
        return taxesValue;
    }

    public void setTaxesValue(TaxesEnum taxesValue) {
        this.taxesValue = taxesValue;
    }
}
