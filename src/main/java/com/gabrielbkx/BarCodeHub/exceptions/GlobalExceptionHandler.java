package com.gabrielbkx.BarCodeHub.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RegistroDuplicadoExeption.class)
    public ResponseEntity<String> handleDuplicatedRecordException(RegistroDuplicadoExeption ex) {
        return new ResponseEntity<>("Esse registro ja existe!",HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RegistroNaoEncontradoException.class)
    public ResponseEntity<String> handleRecordNotFoundException(RegistroNaoEncontradoException ex) {
        return new ResponseEntity<>("Registro não encontrado!", HttpStatus.NOT_FOUND);
    }


}
