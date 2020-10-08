package br.com.johnworks.banco.digital.api.controller;

import static br.com.johnworks.banco.digital.api.controller.mapper.ClienteMapper.mapCliente;
import static br.com.johnworks.banco.digital.api.controller.mapper.EnderecoMapper.mapEndereco;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.johnworks.banco.digital.api.controller.input.PropostaAceiteInput;
import br.com.johnworks.banco.digital.api.controller.input.PropostaClienteInput;
import br.com.johnworks.banco.digital.api.controller.input.PropostaEnderecoInput;
import br.com.johnworks.banco.digital.api.controller.mapper.PropostaMapper;
import br.com.johnworks.banco.digital.domain.model.Documento;
import br.com.johnworks.banco.digital.domain.model.Proposta;
import br.com.johnworks.banco.digital.domain.service.CrudPropostaService;

@RestController
@RequestMapping(value = "/api/propostas")
public class PropostaController {

	@Autowired
	private CrudPropostaService cadastraPropostaService;

	@PostMapping("/cliente")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Void> adicionaCliente(@RequestBody @Valid PropostaClienteInput input) throws Exception {
		Proposta proposta = cadastraPropostaService.salvar(mapCliente(input));

		ResourceUriHelper.addUriInResponseHeader(proposta.getId(), "/api/propostas/endereco");
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PostMapping("/endereco")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Void> adicionaEndereco(@RequestBody @Valid PropostaEnderecoInput input) throws Exception {
		Proposta proposta = cadastraPropostaService.atualizarEndereco(input.getPropostaId(), mapEndereco(input));
		ResourceUriHelper.addUriInResponseHeader(proposta.getId(), "/api/propostas/documento");
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PostMapping(path = "/{propostaId}/documento")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Void> adicionaDocumentoFrente(
			@RequestParam(name = "propostaId", required = true) Long propostaId,
			@RequestParam(name = "arquivo-frente", required = true) MultipartFile arquivoFrente,
			@RequestParam(name = "arquivo-Verso", required = true) MultipartFile arquivoVerso) throws Exception {
		Documento documento = new Documento();
		documento.setCpfFrente(arquivoFrente.getOriginalFilename());
		documento.setCpVerso(arquivoVerso.getOriginalFilename());
		Proposta proposta = cadastraPropostaService.atualizarDocumento(propostaId, documento);
		ResourceUriHelper.addUriInResponseHeader(proposta.getId(), "/api/propostas/{id}/aceite");
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PostMapping("/aceite")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Void> aceite(@RequestBody @Valid PropostaAceiteInput input) {
		try {
			Proposta proposta = PropostaMapper.mapProposta(input);
			cadastraPropostaService.aceitaProposta(proposta.getId(), proposta.getAceite());
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
}
