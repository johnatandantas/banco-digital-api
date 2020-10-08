package br.com.johnworks.banco.digital.domain.service;

import static br.com.johnworks.banco.digital.domain.model.enumeration.StatusProposta.LIBERADA;
import static br.com.johnworks.banco.digital.domain.model.enumeration.StatusProposta.NAO_LIBERADA;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.johnworks.banco.digital.domain.exception.PropostaNaoEncontradoException;
import br.com.johnworks.banco.digital.domain.model.Cliente;
import br.com.johnworks.banco.digital.domain.model.Conta;
import br.com.johnworks.banco.digital.domain.model.Documento;
import br.com.johnworks.banco.digital.domain.model.Endereco;
import br.com.johnworks.banco.digital.domain.model.Proposta;
import br.com.johnworks.banco.digital.domain.repository.ContaRepository;
import br.com.johnworks.banco.digital.domain.repository.PropostaRepository;
import br.com.johnworks.banco.digital.domain.service.EnvioEmailService.Mensagem;
import br.com.johnworks.banco.digital.infrastructure.FakeEnvioEmailService;

@Service
public class CrudPropostaService {
	
	@Autowired
	private CrudClienteService cadastraPropostaService;
	
	@Autowired
	private PropostaRepository propostaRepository;
	
	@Autowired
	private FakeEnvioEmailService fakeEnvioEmailService;
	
	@Autowired
	private ContaRepository contaRepository;

	@Transactional
	public Proposta salvar(Cliente cliente) throws Exception {
		Cliente novoCLiente = cadastraPropostaService.salvar(cliente);
		Proposta proposta = new Proposta();
		proposta.setCliente(novoCLiente);
		return propostaRepository.save(proposta);
	}
	
	@Transactional
	public Proposta atualizarEndereco(Long id, Endereco endereco) throws Exception {
		Optional<Proposta> proposta = propostaRepository.findById(id);
		
		if(proposta.isPresent()) {
			cadastraPropostaService.atualizarEndereco(proposta.get().getCliente().getId(), endereco);
			return proposta.get();
		}
		
		return null;
	}
	
	@Transactional
	public Proposta atualizarDocumento(Long id, Documento documento) throws Exception {
		Optional<Proposta> proposta = propostaRepository.findById(id);
		
		if(proposta.isPresent()) {
			cadastraPropostaService.atualizarDocumento(proposta.get().getCliente().getId(), documento);
			return proposta.get();
		}
		
		return null;
	}
	
	@Transactional
	public void aceitaProposta(Long id, boolean aceite) throws Exception {
		try {
			Proposta proposta = propostaRepository.findById(id).orElseThrow(() -> new PropostaNaoEncontradoException(id));
			if(aceite) {
				Conta conta = novaConta();
				conta.setProposta(proposta);
				contaRepository.save(conta);
				proposta.setStatusProposta(LIBERADA);
				fakeEnvioEmailService.enviar(new Mensagem());
			}else {
				proposta.setStatusProposta(NAO_LIBERADA);
				fakeEnvioEmailService.enviar(new Mensagem());
			}
			
			proposta.setAceite(aceite);
			propostaRepository.save(proposta);
		} catch (Exception e) {
			throw new Exception();
		}
		
		
	}

	private static Conta novaConta() {
		Random random = new Random();
		Conta conta = new Conta();
		conta.setConta(String.valueOf(random.nextInt(1000)) + "1234");
		conta.setAgencia(String.valueOf(random.nextInt(1000)));
		conta.setCodigoBanco("123");
		conta.setSaldo(BigDecimal.ZERO);
		return conta;
	}
}
