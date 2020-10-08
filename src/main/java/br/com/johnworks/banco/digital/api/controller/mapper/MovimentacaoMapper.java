package br.com.johnworks.banco.digital.api.controller.mapper;

import br.com.johnworks.banco.digital.api.controller.input.TransferirInput;
import br.com.johnworks.banco.digital.domain.model.Conta;
import br.com.johnworks.banco.digital.domain.model.Movimentacao;

public class MovimentacaoMapper {
	
	private MovimentacaoMapper() {
		
	}
	
	public static Movimentacao mapMovimentacao(TransferirInput input) {
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setAgencia(input.getAgencia());
		movimentacao.setCodigoBanco(input.getCodigoBanco());
		movimentacao.setValorTransferido(input.getValorTransferido());
		movimentacao.setDataTransferencia(input.getDataTransferencia());
		movimentacao.setNomeFavorito(input.getNomeFavorito());
		movimentacao.setCpfOuCnpj(input.getCpfOuCnpj());
		movimentacao.setTipoConta(input.getTipoConta());
		movimentacao.setDescricaoDaTransferencia(input.getDescricaoDaTransferencia());
		movimentacao.setContaOrigem(new Conta(input.getContaOrigem()));
		movimentacao.setContaDestino(new Conta(input.getContaDestino()));
		return movimentacao;
	}

}
