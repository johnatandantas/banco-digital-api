package br.com.johnworks.banco.digital.api.controller.mapper;

import br.com.johnworks.banco.digital.api.controller.input.PropostaClienteInput;
import br.com.johnworks.banco.digital.domain.model.Cliente;

public class ClienteMapper {
	
	private ClienteMapper() {
		
	}
	
	public static Cliente mapCliente(PropostaClienteInput input) {
		Cliente cliente = new Cliente();
		cliente.setNome(input.getNome());
		cliente.setSobrenome(input.getSobrenome());
		cliente.setEmail(input.getEmail());
		cliente.setCpf(input.getCpf());
		cliente.setCnh(input.getCnh());
		cliente.setDataNascimento(input.getDataNascimento());
		return cliente;
	}

}
