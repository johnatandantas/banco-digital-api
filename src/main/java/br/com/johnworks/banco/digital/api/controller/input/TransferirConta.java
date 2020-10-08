package br.com.johnworks.banco.digital.api.controller.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class TransferirConta {

	@NotBlank(message = "COnta não pode ser nula")
	private String conta;

	@NotNull
	@Positive(message = "Valor tem que ser positivo")
	private BigDecimal valorTransferido;

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public BigDecimal getValorTransferido() {
		return valorTransferido;
	}

	public void setValorTransferido(BigDecimal valorTransferido) {
		this.valorTransferido = valorTransferido;
	}

}
