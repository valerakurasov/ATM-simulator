package org.example.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

import java.util.Set;

public class DenominationValidator implements ConstraintValidator<Denomination, Integer> {
    @Value("${atm.available-banknotes.put}")
    private Set<Integer> availableDenominations;

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        if (integer == null) {
            return false;
        }
        return availableDenominations.contains(integer);
    }
}
