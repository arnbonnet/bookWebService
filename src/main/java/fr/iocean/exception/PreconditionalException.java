package fr.iocean.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
public class PreconditionalException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1515398236442853241L;

}
