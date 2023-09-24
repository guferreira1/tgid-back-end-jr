package br.com.tgid.msbusiness.services;

import br.com.tgid.msbusiness.configs.BusinessException;
import br.com.tgid.msbusiness.dtos.request.CreateBusinessDtoRequest;
import br.com.tgid.msbusiness.dtos.request.UpdateBusinessDtoRequest;
import br.com.tgid.msbusiness.dtos.response.BusinessDtoResponse;
import br.com.tgid.msbusiness.dtos.response.TaxesDtoResponse;
import br.com.tgid.msbusiness.models.Business;
import br.com.tgid.msbusiness.models.Taxes;
import br.com.tgid.msbusiness.repositories.BusinessRepository;
import br.com.tgid.msbusiness.repositories.TaxRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
public class BusinessService {

    private static final Logger LOGGER = LogManager.getLogger(BusinessService.class.getName());

    private final BusinessRepository businessRepository;
    private final TaxRepository taxRepository;

    public BusinessService(BusinessRepository businessRepository, TaxRepository taxRepository) {
        this.businessRepository = businessRepository;
        this.taxRepository = taxRepository;
    }

    public ResponseEntity<BusinessDtoResponse> create(@Valid final CreateBusinessDtoRequest dto) {
        Collection<TaxesDtoResponse> taxes = null;

        String cnpjFormatted = dto.getCnpj().replaceAll("[^0-9]", "");

        if (cnpjFormatted.length() > 14) {
            throw new BusinessException(HttpStatus.BAD_REQUEST.value(), "Quantidade de caracteres inválidas para CNPJ.");
        }

        Business business = new Business();
        business.setCnpj(cnpjFormatted);
        business.setEmail(dto.getEmail());
        business.setPassword(dto.getPassword());
        business.setTaxes(dto.getTaxes());
        business.setName(dto.getName());

        try {
            LOGGER.info("Saving business in database...");
            businessRepository.save(business);
            LOGGER.info("Saved.");

            taxes = this.createTaxes(dto.getTaxes(), business); // Passando a empresa para a criação das taxas
        } catch (RuntimeException ex) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        }

        BusinessDtoResponse businessResponse = new BusinessDtoResponse();
        businessResponse.setCnpj(cnpjFormatted);
        businessResponse.setId(business.getId());
        businessResponse.setName(business.getName());
        businessResponse.setTaxes(taxes);
        businessResponse.setPassword(business.getPassword());
        businessResponse.setEmail(business.getEmail());
        businessResponse.setTotalValue(business.getTotalValue());

        return ResponseEntity.status(HttpStatus.CREATED).body(businessResponse);
    }

    public ResponseEntity<Collection<BusinessDtoResponse>> search() {

        Collection<BusinessDtoResponse> businessResponse = new ArrayList<>();

        LOGGER.info("Search all business...");
        Collection<Business> companies = this.businessRepository.searchAll();
        LOGGER.info("Found.");

        for (Business business : companies) {
            BusinessDtoResponse b = new BusinessDtoResponse();
            Collection<TaxesDtoResponse> taxResponse = new ArrayList<>();

            for (Taxes tax : business.getTaxes()) {
                TaxesDtoResponse t = new TaxesDtoResponse();
                t.setTaxesValue(tax.getTaxesValue());
                t.setValue(tax.getValue());
                t.setId(tax.getId());
                t.setBusiness(tax.getBusiness().getId());

                taxResponse.add(t);
            }
            b.setTaxes(taxResponse);
            b.setEmail(business.getEmail());
            b.setCnpj(business.getCnpj());
            b.setId(business.getId());
            b.setName(business.getName());
            b.setPassword(business.getPassword());
            b.setTotalValue(business.getTotalValue());

            businessResponse.add(b);
        }

        return ResponseEntity.status(HttpStatus.OK).body(businessResponse);
    }

    public ResponseEntity<BusinessDtoResponse> find(final Long id) {
        LOGGER.info("Searching a business...");
        Business business = this.businessRepository.findById(id)
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND.value(), "Empresa não encontrada"));
        LOGGER.info("Found.");

        BusinessDtoResponse businessResponse = new BusinessDtoResponse();

        Collection<TaxesDtoResponse> taxesResponse = new ArrayList<>();
        for (Taxes tax : business.getTaxes()) {
            TaxesDtoResponse t = new TaxesDtoResponse();
            t.setBusiness(tax.getBusiness().getId());
            t.setId(tax.getId());
            t.setValue(tax.getValue());
            t.setTaxesValue(tax.getTaxesValue());

            taxesResponse.add(t);
        }

        businessResponse.setTaxes(taxesResponse);
        businessResponse.setPassword(business.getPassword());
        businessResponse.setName(business.getName());
        businessResponse.setId(business.getId());
        businessResponse.setCnpj(business.getCnpj());
        businessResponse.setEmail(business.getEmail());
        businessResponse.setTotalValue(business.getTotalValue());

        return ResponseEntity.status(HttpStatus.OK).body(businessResponse);
    }

    public ResponseEntity<BusinessDtoResponse> update(Long id, UpdateBusinessDtoRequest dto) {
        LOGGER.info("Searching a business...");
        Business foundBusiness = this.businessRepository.findById(id)
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND.value(), "Empresa não encontrada"));
        LOGGER.info("Found.");

        if (Objects.nonNull(dto.getEmail())) {
            foundBusiness.setEmail(dto.getEmail());
        }
        if (Objects.nonNull(dto.getPassword())) {
            foundBusiness.setPassword(dto.getPassword());
        }
        if (Objects.nonNull(dto.getName())) {
            foundBusiness.setName(dto.getName());
        }
        if (Objects.nonNull(dto.getTaxes())) {
            foundBusiness.setTaxes(dto.getTaxes());
        }
        if(Objects.nonNull(dto.getTotalValue())){
            foundBusiness.setTotalValue(dto.getTotalValue());
        }
        if (Objects.nonNull(dto.getCnpj())) {
            throw new BusinessException(HttpStatus.BAD_REQUEST.value(), "Não é possível alterar o CNPJ.");
        }

        LOGGER.info("Updating a business...");
        businessRepository.save(foundBusiness);
        LOGGER.info("Updated.");

        Collection<TaxesDtoResponse> taxesResponse = new ArrayList<>();
        for (Taxes tax : foundBusiness.getTaxes()) {
            TaxesDtoResponse t = new TaxesDtoResponse();
            t.setBusiness(tax.getBusiness().getId());
            t.setId(tax.getId());
            t.setValue(tax.getValue());
            t.setTaxesValue(tax.getTaxesValue());

            taxesResponse.add(t);
        }

        BusinessDtoResponse businessResponse = new BusinessDtoResponse();
        businessResponse.setEmail(foundBusiness.getEmail());
        businessResponse.setCnpj(foundBusiness.getCnpj());
        businessResponse.setId(foundBusiness.getId());
        businessResponse.setName(foundBusiness.getName());
        businessResponse.setPassword(foundBusiness.getPassword());
        businessResponse.setTaxes(taxesResponse);

        return ResponseEntity.status(HttpStatus.CREATED.value()).body(businessResponse);
    }

    public ResponseEntity<Void> delete(final Long id) {
        LOGGER.info("Searching a business...");
        Business business = this.businessRepository.findById(id)
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND.value(), "Empresa não encontrada"));
        LOGGER.info("Found.");

        LOGGER.info("Deleting a business...");
        this.businessRepository.delete(business);
        LOGGER.info("Deleted.");

        return ResponseEntity.noContent().build();
    }

    private Collection<TaxesDtoResponse> createTaxes(Collection<Taxes> taxes, Business business) {
        Collection<TaxesDtoResponse> taxesDtoResponse = new ArrayList<>();

        for (Taxes t : taxes) {
            Taxes tax = new Taxes();
            tax.setTaxesValue(t.getTaxesValue());
            tax.setValue(t.getValue());
            tax.setBusiness(business);

            LOGGER.info("Saving tax in database...");
            taxRepository.save(tax);
            LOGGER.info("Saved.");

            TaxesDtoResponse taxesResponse = new TaxesDtoResponse();
            taxesResponse.setValue(tax.getValue());
            taxesResponse.setTaxesValue(tax.getTaxesValue());
            taxesResponse.setBusiness(business.getId());
            taxesResponse.setId(tax.getId());

            taxesDtoResponse.add(taxesResponse);
        }

        return taxesDtoResponse;
    }
}
