package com.oura.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class HttpException {
    @ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "No Content")
    public static class HttpNoContentException extends RuntimeException {}
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Unprocessable Entity")
    public static class HttpUnprocessableEntityException extends RuntimeException {}

    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Bad Request")
    public static class HttpBadRequestException extends RuntimeException {}
}
