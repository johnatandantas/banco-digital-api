package br.com.johnworks.banco.digital.api.controller.mapper;

import br.com.johnworks.banco.digital.api.controller.input.PropostaEnderecoInput;
import br.com.johnworks.banco.digital.domain.model.Endereco;

public class EnderecoMapper {
	
	private EnderecoMapper() {
		
	}
	
	public static Endereco mapEndereco(PropostaEnderecoInput input) {
		Endereco endereco = new Endereco();
		endereco.setCep(input.getCep());
		endereco.setRua(input.getRua());
		endereco.setBairro(input.getBairro());
		endereco.setComplemento(input.getComplemento());
		endereco.setCidade(input.getCidade());
		endereco.setEstado(input.getEstado());
		return endereco;
	}

}
