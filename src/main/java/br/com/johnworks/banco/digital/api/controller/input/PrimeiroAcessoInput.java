package br.com.johnworks.banco.digital.api.controller.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PrimeiroAcessoInput {

	@Email(message = "Email deve ser válido")
	@NotBlank(message = "E-mail é obrigatório")
	private String email;

	@Size(min = 11, max = 11)
	@NotBlank(message = "CPF é obrigatório")
	private String cpf;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
