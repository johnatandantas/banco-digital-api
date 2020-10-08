package br.com.johnworks.banco.digital.api.controller.input;

import javax.validation.constraints.NotNull;

public class PropostaAceiteInput {

	@NotNull
	private Long propostaId;

	@NotNull
	private Boolean aceite;

	public Long getPropostaId() {
		return propostaId;
	}

	public void setPropostaId(Long propostaId) {
		this.propostaId = propostaId;
	}

	public Boolean getAceite() {
		return aceite;
	}

	public void setAceite(Boolean aceite) {
		this.aceite = aceite;
	}

}
