package br.com.johnworks.banco.digital.domain.exception;

public class PropostaNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public PropostaNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public PropostaNaoEncontradoException(Long restauranteId) {
		this(String.format("Não existe um cadastro de proposta com código %d", restauranteId));
	}
	
}
