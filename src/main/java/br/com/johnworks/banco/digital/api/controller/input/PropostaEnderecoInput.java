package br.com.johnworks.banco.digital.api.controller.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PropostaEnderecoInput {
	
	@NotNull(message = "Proposta não pode ser nula")
	private Long propostaId;
	
	@NotBlank(message = "cep é obrigatório")
	private String cep;
	
	@NotBlank(message = "rua é obrigatório")
	private String rua;
	
	@NotBlank(message = "bairro é obrigatório")
	private String bairro;
	
	@NotBlank(message = "complemento é obrigatório")
	private String complemento;
	
	@NotBlank(message = "cidade é obrigatório")
	private String cidade;
	
	@NotBlank(message = "estado é obrigatório")
	private String estado;

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Long getPropostaId() {
		return propostaId;
	}

	public void setPropostaId(Long propostaId) {
		this.propostaId = propostaId;
	}

}
