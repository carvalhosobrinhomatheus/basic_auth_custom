package br.gov.df.se.pdaf.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
* @author Matheus de Carvalho Sobrinho
*/
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestException  extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public BadRequestException() {
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }

    public BadRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
