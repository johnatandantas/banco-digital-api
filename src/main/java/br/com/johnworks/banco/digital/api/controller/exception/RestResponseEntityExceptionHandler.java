package br.com.johnworks.banco.digital.api.controller.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.johnworks.banco.digital.api.model.ApiErrors;
import br.com.johnworks.banco.digital.domain.exception.PropostaNaoEncontradoException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@Override
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, 
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult bindingResult = ex.getBindingResult();
		return new ResponseEntity<>(new ApiErrors(bindingResult), status);
	}
	
	@ExceptionHandler(PropostaNaoEncontradoException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> handleNaoEncontrada(PropostaNaoEncontradoException ex, 
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<>(new ApiErrors(ex), status);
	}

}
