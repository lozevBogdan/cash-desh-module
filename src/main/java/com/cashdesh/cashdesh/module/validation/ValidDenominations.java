/**
 * 
 */
package com.cashdesh.cashdesh.module.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = DenominationsValidator.class)
public @interface ValidDenominations {
	
    String message() default "Invalid denominations";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
