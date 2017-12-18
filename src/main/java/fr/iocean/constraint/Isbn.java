package fr.iocean.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = IsbnValidator.class)
@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Isbn {
	String message() default "La longeur de l'isbn doit être entre 10 et 14 caractères";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
