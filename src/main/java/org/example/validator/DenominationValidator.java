package org.example.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

import java.util.Set;

/**
 * Данный валидатор непосредственно валидирует банкноты
 * Банкноты, номинал которых отсутствуюет в списке возможных, не пройдут валидацию
 *
 * @author Valera
 * @version 1.0
 */
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
