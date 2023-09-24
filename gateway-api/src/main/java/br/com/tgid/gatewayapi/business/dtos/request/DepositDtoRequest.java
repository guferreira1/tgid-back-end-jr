package br.com.tgid.gatewayapi.business.dtos.request;

import br.com.tgid.gatewayapi.business.configs.constants.TaxesEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DepositDtoRequest {
    private Long personId;

    private Long businessId;

    private BigDecimal deposit;

    private LocalDateTime depositDate = LocalDateTime.now();

    private TaxesEnum taxesEnum;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public LocalDateTime getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(LocalDateTime depositDate) {
        this.depositDate = depositDate;
    }

    public TaxesEnum getTaxesEnum() {
        return taxesEnum;
    }

    public void setTaxesEnum(TaxesEnum taxesEnum) {
        this.taxesEnum = taxesEnum;
    }
}
