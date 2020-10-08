package br.com.johnworks.banco.digital.api.controller.input;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

public class PropostaClienteInput {

	@NotBlank(message = "Nome é obrigatório")
	private String nome;

	@NotBlank(message = "Sobrenome é obrigatório")
	private String sobrenome;

	@Email(message = "Email deve ser válido")
	@NotBlank(message = "E-mail é obrigatório")
	private String email;

	@Size(min = 11, max = 11)
	@NotBlank(message = "CPF é obrigatório")
	private String cpf;

	@Size(min = 11, max = 11)
	@NotBlank(message = "CNH é obrigatório")
	private String cnh;

	@PastOrPresent
	@NotNull(message = "Data nascimento é obrigatório")
	private LocalDate dataNascimento;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

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

	public String getCnh() {
		return cnh;
	}

	public void setCnh(String cnh) {
		this.cnh = cnh;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
