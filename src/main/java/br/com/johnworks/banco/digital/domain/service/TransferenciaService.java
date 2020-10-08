package br.com.johnworks.banco.digital.domain.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.johnworks.banco.digital.domain.model.Conta;
import br.com.johnworks.banco.digital.domain.model.Movimentacao;
import br.com.johnworks.banco.digital.domain.repository.ContaRepository;
import br.com.johnworks.banco.digital.domain.repository.MovimentacaoRepository;

@Service
public class TransferenciaService {
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	
	public void transferirBanco(Movimentacao movimentacao) throws Exception {
		List<Conta> contasOrigem = contaRepository.findByConta(movimentacao.getContaOrigem().getConta());
		
		if(contasOrigem.isEmpty()) {
			throw new Exception("Conta origem não existe");
		}
		
		List<Conta> contasDestino = contaRepository.findByConta(movimentacao.getContaDestino().getConta());
		
		if(contasDestino.isEmpty()) {
			throw new Exception("Conta destino não existe");
		}
		
		BigDecimal novoValorOrigem = contasOrigem.get(0).getSaldo().subtract(movimentacao.getValorTransferido());
		
		if(novoValorOrigem.compareTo(BigDecimal.ZERO) < 0) {
			throw new Exception("Conta origem não pode ficar com um valor negativo");
		}
		
		Conta contaOrigem = contasOrigem.get(0);
		contaOrigem.setSaldo(novoValorOrigem);
		contaRepository.save(contaOrigem);
		
		Conta contaDestino = contasDestino.get(0);
		contaDestino.setSaldo(contaDestino.getSaldo().add(movimentacao.getValorTransferido()));
		contaRepository.save(contaDestino);
		
		movimentacao.setContaOrigem(contaOrigem);
		movimentacao.setContaDestino(contaDestino);
		movimentacaoRepository.save(movimentacao);
	}

	public void transferirConta(String conta, BigDecimal valorTransferido) throws Exception {
		List<Conta> contas = contaRepository.findByConta(conta);
		
		if(contas.isEmpty()) {
			throw new Exception("Conta não existe");
		}
		
		if(valorTransferido.compareTo(BigDecimal.ZERO) < 0) {
			throw new Exception("Conta  não pode ficar com um valor negativo");
		}
		
		Conta contaOrigem = contas.get(0);
		
		contaOrigem.setSaldo(contaOrigem.getSaldo().add(valorTransferido));
		contaRepository.save(contaOrigem);
		
	}

}
