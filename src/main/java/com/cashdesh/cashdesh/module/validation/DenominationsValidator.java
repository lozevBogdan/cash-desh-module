package com.cashdesh.cashdesh.module.validation;

import java.util.Map;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DenominationsValidator  implements ConstraintValidator<ValidDenominations, Map<Integer, Integer>> {


    @Override
    public void initialize(ValidDenominations constraintAnnotation) {
    }

    @Override
    public boolean isValid(Map<Integer, Integer> denominations, ConstraintValidatorContext context) {
        // Implement your validation logic here
        // For example, check if denominations map is not null and contains valid values
        return denominations != null && !denominations.isEmpty();
    }

}
