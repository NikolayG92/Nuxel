package com.example.nuxel.adsservice.validators.ad;

import com.example.nuxel.adsservice.annotations.Validator;
import com.example.nuxel.adsservice.model.bindingModels.AdAddBindingModel;
import com.example.nuxel.adsservice.repository.AdRepository;
import org.springframework.validation.Errors;

import java.math.BigDecimal;

import static com.example.nuxel.adsservice.constants.validators.AdConstants.*;

@Validator
public class AdAddValidator implements org.springframework.validation.Validator  {
    private final AdRepository adRepository;

    public AdAddValidator(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return AdAddBindingModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AdAddBindingModel adAddBindingModel = (AdAddBindingModel) o;

        if (adRepository.findByName(adAddBindingModel.getName()) != null) {
            errors.rejectValue("name", AD_NAME_ALREADY_EXIST,
                    AD_NAME_ALREADY_EXIST);
        }

        if (errors.hasErrors()) {
            return;
        }

        if (adAddBindingModel.getName().isEmpty()) {
            errors.rejectValue("name", NAME_CANNOT_BE_NULL,
                    NAME_CANNOT_BE_NULL);
        }

        if (adAddBindingModel.getDescription().length() < 30 ||
        adAddBindingModel.getDescription().length() > 6000){
            errors.rejectValue("description", AD_DESCRIPTION_LENGTH,
                    AD_DESCRIPTION_LENGTH);
        }

        if (adAddBindingModel.getPrice() == null) {
            errors.rejectValue("price", PRICE_CANNOT_BE_NULL,
                    PRICE_CANNOT_BE_NULL);
        } else {
            if (adAddBindingModel.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
                errors.rejectValue("price", PRICE_CANNOT_BE_NEGATIVE_OR_ZERO,
                        PRICE_CANNOT_BE_NEGATIVE_OR_ZERO);
            }
        }

//        if (adAddBindingModel.getCategory() == null) {
//            errors.rejectValue("category", CATEGORY_CANNOT_BE_NULL,
//                    CATEGORY_CANNOT_BE_NULL);
//        }

        if (adAddBindingModel.getCity().isEmpty()) {
            errors.rejectValue("city", CITY_CANNOT_BE_NULL,
                    CITY_CANNOT_BE_NULL);
        }

        if(adAddBindingModel.getCity().contains(" ")){
            errors.rejectValue("city", WHITESPACE_IS_NOT_VALID,
                    WHITESPACE_IS_NOT_VALID);
        }

        if (adAddBindingModel.getRegion().isEmpty()) {
            errors.rejectValue("region", REGION_CANNOT_BE_NULL,
                    REGION_CANNOT_BE_NULL);
        }

        if (adAddBindingModel.getRegion().contains(" ")) {
            errors.rejectValue("region", WHITESPACE_IS_NOT_VALID,
                    WHITESPACE_IS_NOT_VALID);
        }


        if (adAddBindingModel.getPostCode() < 1000 || adAddBindingModel.getPostCode() > 9999) {
            errors.rejectValue("postCode", AD_POSTCODE_LENGTH,
                    AD_POSTCODE_LENGTH);
        }

        if (!adAddBindingModel.getPhoneNumber().matches("0[0-9]{9}")){
            errors.rejectValue("phoneNumber", INVALID_PHONE_NUMBER,
                    INVALID_PHONE_NUMBER);
        }
    }
}
