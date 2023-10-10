package com.khan.pma.validators;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;//type manually
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)//it means this validation will only apply on fields
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueValidator.class)
public @interface UniqueValue {
	
	String message() default "Unique Constraint violated";
	
	Class<?>[] groups() default{};
	Class<? extends Payload>[] payload() default{};

}
