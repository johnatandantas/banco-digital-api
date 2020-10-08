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

import br.com.johnworks.banco.digital.api.controller.input.TransferirConta;
import br.com.johnworks.banco.digital.api.controller.input.TransferirInput;
import br.com.johnworks.banco.digital.api.controller.mapper.MovimentacaoMapper;
import br.com.johnworks.banco.digital.domain.model.Movimentacao;
import br.com.johnworks.banco.digital.domain.service.TransferenciaService;

@RestController
@RequestMapping(value = "/api/transferencia")
public class TransferenciaController {
	
	@Autowired
	private TransferenciaService transferenciaService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Void> transferirParaOutraConta(
			@RequestBody @Valid TransferirInput input) {
		try {
			Movimentacao movimentacao = MovimentacaoMapper.mapMovimentacao(input);
			transferenciaService.transferirBanco(movimentacao);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PostMapping(path = "/transferir-para-conta")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Void> transferirConta(
			@RequestBody @Valid TransferirConta input) {
		try {
			transferenciaService.transferirConta(input.getConta(), input.getValorTransferido());
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
