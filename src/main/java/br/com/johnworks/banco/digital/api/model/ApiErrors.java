package br.com.johnworks.banco.digital.api.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.validation.BindingResult;

import br.com.johnworks.banco.digital.domain.exception.PropostaNaoEncontradoException;

public class ApiErrors {

	private List<String> erros;

	public ApiErrors(BindingResult bindingResult) {
		this.erros = new ArrayList<>();
		bindingResult.getAllErrors().forEach( error -> this.erros.add(error.getDefaultMessage()));
	}

	public ApiErrors(PropostaNaoEncontradoException ex) {
		this.erros = Arrays.asList(ex.getMessage());
	}

	public List<String> getErros() {
		return erros;
	}

}
