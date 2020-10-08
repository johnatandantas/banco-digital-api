package br.com.johnworks.banco.digital.api.controller.input;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import br.com.johnworks.banco.digital.domain.model.enumeration.TipoConta;

public class TransferirInput {

	@NotBlank
	private String agencia;

	@NotBlank
	private String codigoBanco;

	@NotNull
	@Positive(message = "Valor tem que ser positivo")
	private BigDecimal valorTransferido;

	@NotNull
	@PastOrPresent
	private LocalDate dataTransferencia = LocalDate.now();

	@NotBlank
	private String nomeFavorito;

	@NotBlank
	@Column(nullable = false)
	private String cpfOuCnpj;

	@NotNull
	private TipoConta tipoConta;

	@NotBlank
	private String descricaoDaTransferencia;
	
	@NotBlank
	private String contaOrigem;
	
	@NotBlank
	private String contaDestino;

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getCodigoBanco() {
		return codigoBanco;
	}

	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	public BigDecimal getValorTransferido() {
		return valorTransferido;
	}

	public void setValorTransferido(BigDecimal valorTransferido) {
		this.valorTransferido = valorTransferido;
	}

	public LocalDate getDataTransferencia() {
		return dataTransferencia;
	}

	public void setDataTransferencia(LocalDate dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}

	public String getNomeFavorito() {
		return nomeFavorito;
	}

	public void setNomeFavorito(String nomeFavorito) {
		this.nomeFavorito = nomeFavorito;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}

	public String getDescricaoDaTransferencia() {
		return descricaoDaTransferencia;
	}

	public void setDescricaoDaTransferencia(String descricaoDaTransferencia) {
		this.descricaoDaTransferencia = descricaoDaTransferencia;
	}

	public String getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(String contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public String getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(String contaDestino) {
		this.contaDestino = contaDestino;
	}

}
