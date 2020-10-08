package br.com.johnworks.banco.digital.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Documento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	private String cpfFrente;
	
	@NotBlank
	@Column(nullable = false)
	private String cpVerso;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpfFrente() {
		return cpfFrente;
	}

	public void setCpfFrente(String cpfFrente) {
		this.cpfFrente = cpfFrente;
	}

	public String getCpVerso() {
		return cpVerso;
	}

	public void setCpVerso(String cpVerso) {
		this.cpVerso = cpVerso;
	}

}
