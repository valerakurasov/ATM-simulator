package org.example.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Данная аннотация предназначена для валидации банкнот
 *
 * @author Valera
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE_USE})
@Constraint(validatedBy = DenominationValidator.class)
public @interface Denomination {
    String message() default "Invalid denomination";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
