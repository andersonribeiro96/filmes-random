package com.filmes.web.exception.handler;

import com.filmes.web.exception.CaracteristicasNaoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HandlerCustomizeException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CaracteristicasNaoEncontradaException.class)
    public final ResponseEntity<String> caracteristaNaoEncontrada(){
        CaracteristicasNaoEncontradaException caracteristicasNaoEncontradaException = new CaracteristicasNaoEncontradaException();
        return new ResponseEntity<>(caracteristicasNaoEncontradaException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProviderWatchNaoEncontrandoException.class)
    public final ResponseEntity<String> providerNaoEncontrado(){
        ProviderWatchNaoEncontrandoException providerWatchNaoEncontrandoException = new ProviderWatchNaoEncontrandoException();
        return new ResponseEntity<>(providerWatchNaoEncontrandoException.getMessage(), HttpStatus.NOT_FOUND);
    }


}
