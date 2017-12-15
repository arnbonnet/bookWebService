package fr.iocean.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

public class IsbnValidator implements ConstraintValidator<Isbn, String>{

	@Override
	public void initialize(Isbn arg0) {
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return StringUtils.isEmpty(value) || (value.length() >= 10 && value.length() <= 14);
	}
	
}


