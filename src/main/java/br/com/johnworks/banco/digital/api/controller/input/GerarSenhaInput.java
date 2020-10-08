package br.com.johnworks.banco.digital.api.controller.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class GerarSenhaInput {

	@NotBlank(message = "Token é obrigatório")
	private String token;

	@Size(min = 8, max = 20)
	@NotBlank(message = "Senha é obrigatório")
	private String senha;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


}
