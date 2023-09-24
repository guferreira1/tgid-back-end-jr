package br.com.tgid.gatewayapi.business.services;

import br.com.tgid.gatewayapi.business.clients.BusinessClient;
import br.com.tgid.gatewayapi.business.configs.constants.TaxesEnum;
import br.com.tgid.gatewayapi.business.dtos.request.CreateBusinessDtoRequest;
import br.com.tgid.gatewayapi.business.dtos.request.DepositDtoRequest;
import br.com.tgid.gatewayapi.business.dtos.request.UpdateBusinessDtoRequest;
import br.com.tgid.gatewayapi.business.dtos.response.BusinessDtoResponse;
import br.com.tgid.gatewayapi.business.dtos.response.TaxesDtoResponse;
import br.com.tgid.gatewayapi.person.dtos.request.UpdateWalletDtoRequest;
import br.com.tgid.gatewayapi.person.dtos.response.PersonDtoResponse;
import br.com.tgid.gatewayapi.person.dtos.response.WalletDtoResponse;
import br.com.tgid.gatewayapi.person.services.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.jni.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class BusinessService {

    private static final Logger LOGGER = LogManager.getLogger(BusinessService.class.getName());
    private final BusinessClient businessClient;
    private final PersonService personService;

    public BusinessService(BusinessClient businessClient, PersonService personService) {
        this.businessClient = businessClient;
        this.personService = personService;
    }

    public ResponseEntity<BusinessDtoResponse> create(CreateBusinessDtoRequest dto) {
        return this.businessClient.create(dto);
    }

    public ResponseEntity<Collection<BusinessDtoResponse>> search() {
        return this.businessClient.search();
    }

    public ResponseEntity<BusinessDtoResponse> find(final Long id) {

        return this.businessClient.find(id);
    }

    public ResponseEntity<BusinessDtoResponse> update(Long id, UpdateBusinessDtoRequest dto) {

        return this.businessClient.update(id, dto);
    }

    public ResponseEntity<Void> delete(Long id) {

        return this.businessClient.delete(id);
    }

    public ResponseEntity<DepositDtoRequest> deposit(DepositDtoRequest dto) {
        LOGGER.info("Search person...");
        ResponseEntity<PersonDtoResponse> person = this.personService.find(dto.getPersonId());
        LOGGER.info("Found.");

        LOGGER.info("Search business...");
        ResponseEntity<BusinessDtoResponse> business = this.businessClient.find(dto.getBusinessId());
        LOGGER.info("Found.");

        if (person != null &&
                person.getBody() != null &&
                person.getBody().getWallet() != null &&
                person.getBody().getWallet().getAmount().compareTo(BigDecimal.ONE) > 0) {

            UpdateWalletDtoRequest wallet = new UpdateWalletDtoRequest();
            wallet.setAmount(wallet.getAmount().subtract(dto.getDeposit()));

            this.personService.updateWallet(person.getBody().getId(), wallet);
        }

        if (business != null && business.getBody() != null) {
            BigDecimal depositTax = BigDecimal.ZERO;
            BigDecimal withdrawTax = BigDecimal.ZERO;
            BigDecimal percentage = BigDecimal.ZERO;

            for (TaxesDtoResponse taxe : business.getBody().getTaxes()) {
                if (taxe.getTaxesValue().equals(TaxesEnum.DEPOSIT)) {
                    depositTax = taxe.getValue();
                    percentage = depositTax;
                }
                if (taxe.getTaxesValue().equals(TaxesEnum.WITHDRAN)) {
                    withdrawTax = taxe.getValue();
                    percentage = withdrawTax;
                }
            }

            BigDecimal totalValue = business.getBody().getTotalValue();
            BigDecimal updatedTotalValue = totalValue.subtract(withdrawTax).add(depositTax);

            BigDecimal finalTotalValue = updatedTotalValue.multiply(BigDecimal.ONE.add(percentage));

            UpdateBusinessDtoRequest updateBusiness = new UpdateBusinessDtoRequest();
            updateBusiness.setTotalValue(finalTotalValue);

            this.businessClient.update(business.getBody().getId(), updateBusiness);
        }

        DepositDtoRequest deposit = new DepositDtoRequest();
        deposit.setDepositDate(LocalDateTime.now());
        deposit.setDeposit(dto.getDeposit());
        deposit.setBusinessId(business.getBody().getId());
        deposit.setPersonId(person.getBody().getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(deposit);
    }

    public ResponseEntity<DepositDtoRequest> withdraw(DepositDtoRequest dto) {
        LOGGER.info("Search person...");
        ResponseEntity<PersonDtoResponse> person = this.personService.find(dto.getPersonId());
        LOGGER.info("Found.");

        LOGGER.info("Search business...");
        ResponseEntity<BusinessDtoResponse> business = this.businessClient.find(dto.getBusinessId());
        LOGGER.info("Found.");

        if (business != null && business.getBody() != null) {
            BigDecimal withdrawTax = BigDecimal.ZERO;
            BigDecimal percentage = BigDecimal.ZERO;

            if (dto.getTaxesEnum().equals(TaxesEnum.WITHDRAN)) {
                withdrawTax = BigDecimal.ZERO;
                for (TaxesDtoResponse tax : business.getBody().getTaxes()) {
                    if (tax.getTaxesValue().equals(TaxesEnum.WITHDRAN)) {
                        withdrawTax = tax.getValue();
                        break;
                    }
                }
                percentage = withdrawTax;
            }

            if (person != null &&
                    person.getBody() != null &&
                    person.getBody().getWallet() != null) {

                UpdateWalletDtoRequest wallet = new UpdateWalletDtoRequest();
                BigDecimal currentAmount = person.getBody().getWallet().getAmount();
                BigDecimal newAmount = currentAmount.add(dto.getDeposit()).subtract(percentage);
                wallet.setAmount(newAmount);

                this.personService.updateWallet(person.getBody().getId(), wallet);
            }

            BigDecimal totalValue = business.getBody().getTotalValue();

            BigDecimal finalTotalValue = totalValue.subtract(BigDecimal.ONE.add(dto.getDeposit()));

            UpdateBusinessDtoRequest updateBusiness = new UpdateBusinessDtoRequest();
            updateBusiness.setTotalValue(finalTotalValue);

            this.businessClient.update(business.getBody().getId(), updateBusiness);
        }

        DepositDtoRequest deposit = new DepositDtoRequest();
        deposit.setDepositDate(LocalDateTime.now());
        deposit.setDeposit(dto.getDeposit());
        deposit.setBusinessId(business.getBody().getId());
        deposit.setPersonId(person.getBody().getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(deposit);
    }
}
