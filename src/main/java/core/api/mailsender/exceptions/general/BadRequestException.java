package core.api.mailsender.exceptions.general;

import core.api.mailsender.exceptions.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends GeneralException {
    public BadRequestException() {
        super(HttpStatus.BAD_REQUEST.value());
    }

    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST.value(), message);
    }
}