package br.gov.df.se.pdaf.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
* @author Matheus de Carvalho Sobrinho
*/
@ResponseStatus(reason = "Não Autorizado", code = HttpStatus.UNAUTHORIZED, value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnauthorizedException() {
	}

	public UnauthorizedException(String message) {
		super(message);
	}
	
}
