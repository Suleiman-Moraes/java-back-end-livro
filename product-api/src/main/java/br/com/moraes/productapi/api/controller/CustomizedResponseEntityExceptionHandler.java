package br.com.moraes.productapi.api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.moraes.handler.CustomizedResponseEntityExceptionHandlerGeneric;

@RestControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends CustomizedResponseEntityExceptionHandlerGeneric {

}
