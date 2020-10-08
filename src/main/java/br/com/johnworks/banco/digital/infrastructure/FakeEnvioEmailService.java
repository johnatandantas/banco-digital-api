package br.com.johnworks.banco.digital.infrastructure;

import org.springframework.stereotype.Service;

import br.com.johnworks.banco.digital.domain.service.EnvioEmailService;

@Service
public class FakeEnvioEmailService {

	public void enviar(EnvioEmailService.Mensagem mensagem) {
		System.out.println("Enviando e-mail fake");
	}

}
