package com.lojadomecanico.desafio.app.exceptions;

import com.lojadomecanico.desafio.domain.exceptions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RequiredArgsConstructor
class CustomExceptionHandler {

    // todo exception notation @Valid in request
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomException> handleNotFoundException(MethodArgumentNotValidException bad){
        CustomException invalidValuesInRequest = new CustomException(
                "Algum campo inválido",
                HttpStatus.BAD_REQUEST.value(),
                new Date().getTime(),
                bad.getStackTrace()[0].getMethodName(),
                bad.getMessage()
                );

        return new ResponseEntity<>(invalidValuesInRequest, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<CustomException> handleNotFoundException(NotFoundException notFound){
        CustomException notFoundDetails = new CustomException(
                "Recurso não encontrado",
                HttpStatus.NOT_FOUND.value(),
                new Date().getTime(),
                notFound.getStackTrace()[0].getMethodName(),
                notFound.getMessage()
        );

        return new ResponseEntity<>(notFoundDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<CustomException> handleBadRequestException(BadRequestException bad){
        CustomException badDetails = new CustomException(
                "Algum campo inválido",
                HttpStatus.BAD_REQUEST.value(),
                new Date().getTime(),
                bad.getStackTrace()[0].getMethodName(),
                bad.getMessage()
        );
        return new ResponseEntity<>(badDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServerErrorException.class)
    public ResponseEntity<CustomException> handleServerErrorException(ServerErrorException server){
        CustomException serverError = new CustomException(
                "Erro interno no servidor",
                HttpStatus.NOT_FOUND.value(),
                new Date().getTime(),
                server.getStackTrace()[0].getMethodName(),
                server.getMessage()
        );
        return new ResponseEntity<>(serverError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<CustomException> handleUnauthorizedException(UnauthorizedException auth){
        CustomException noAuth = new CustomException(
                "Não autorizado",
                HttpStatus.UNAUTHORIZED.value(),
                new Date().getTime(),
                auth.getStackTrace()[0].getMethodName(),
                auth.getMessage()
        );

        return new ResponseEntity<>(noAuth, HttpStatus.UNAUTHORIZED);
    }

}
