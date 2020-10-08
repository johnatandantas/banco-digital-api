package br.com.johnworks.banco.digital.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.johnworks.banco.digital.api.controller.input.GerarSenhaInput;
import br.com.johnworks.banco.digital.api.controller.input.PrimeiroAcessoInput;
import br.com.johnworks.banco.digital.domain.service.CrudClienteService;

@RestController
@RequestMapping(value = "/api/acessos")
public class AcessoController {
	
	@Autowired
	private CrudClienteService cadastraClienteService;

	@PostMapping("/primeiro-acesso")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Void> gerarToken(
			@RequestBody @Valid PrimeiroAcessoInput input) {
		try {
			cadastraClienteService.salvarToken(input.getCpf(), input.getEmail());
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PostMapping("/gerar-senha")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Void> salvarSenha(
			@RequestBody @Valid GerarSenhaInput input) {
		try {
			cadastraClienteService.salvarSenha(input.getToken(), input.getSenha());
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
