package br.com.johnworks.banco.digital.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import br.com.johnworks.banco.digital.domain.model.enumeration.TipoConta;

@Entity
public class Movimentacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String agencia;

	@NotBlank
	@Column(nullable = false)
	private String codigoBanco;

	@NotNull
	@Column(nullable = false)
	private BigDecimal valorTransferido;

	@NotNull
	@PastOrPresent
	private LocalDate dataTransferencia = LocalDate.now();

	@NotBlank
	@Column(nullable = false)
	private String nomeFavorito;

	@NotBlank
	@Column(nullable = false)
	private String cpfOuCnpj;

	@NotNull
	private TipoConta tipoConta;

	@NotBlank
	@Column(nullable = false)
	private String descricaoDaTransferencia;

	@JoinColumn(name = "conta_origem_id")
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Conta contaOrigem;

	@JoinColumn(name = "conta_destino_id")
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Conta contaDestino;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Conta getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(Conta contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public Conta getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(Conta contaDestino) {
		this.contaDestino = contaDestino;
	}

}
