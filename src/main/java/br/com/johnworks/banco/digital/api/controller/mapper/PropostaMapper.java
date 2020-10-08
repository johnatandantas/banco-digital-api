package br.com.johnworks.banco.digital.api.controller.mapper;

import br.com.johnworks.banco.digital.api.controller.input.PropostaAceiteInput;
import br.com.johnworks.banco.digital.domain.model.Proposta;

public class PropostaMapper {
	private PropostaMapper() {
		
	}
	
	public static Proposta mapProposta(PropostaAceiteInput input) {
		Proposta proposta = new Proposta();
		proposta.setId(input.getPropostaId());
		proposta.setAceite(input.getAceite());
		return proposta;
	}
}
