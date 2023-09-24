package br.com.tgid.msbusiness.dtos.response;

import br.com.tgid.msbusiness.constants.TaxesEnum;
import br.com.tgid.msbusiness.models.Business;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Collection;

public class TaxesDtoResponse {

    @JsonProperty
    private Long id;

    @JsonProperty
    private BigDecimal value;

    @JsonProperty
    private Long business;

    @JsonProperty
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
