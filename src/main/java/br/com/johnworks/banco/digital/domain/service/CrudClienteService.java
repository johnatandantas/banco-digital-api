package br.com.johnworks.banco.digital.domain.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.johnworks.banco.digital.domain.model.Cliente;
import br.com.johnworks.banco.digital.domain.model.Documento;
import br.com.johnworks.banco.digital.domain.model.Endereco;
import br.com.johnworks.banco.digital.domain.repository.ClienteRepository;
import br.com.johnworks.banco.digital.domain.service.EnvioEmailService.Mensagem;
import br.com.johnworks.banco.digital.infrastructure.FakeEnvioEmailService;

@Service
public class CrudClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private FakeEnvioEmailService fakeEnvioEmailService;

	public Cliente buscarPorId(Long id) {
		Cliente cliente = clienteRepository.getOne(id);
		return cliente;
	}

	@Transactional
	public void atualizarEndereco(Long id, Endereco endereco) throws Exception {
		Cliente cliente = buscarPorId(id);
		cliente.setEndereco(endereco);
		clienteRepository.save(cliente);
	}

	@Transactional
	public void atualizarDocumento(Long id, Documento documento) throws Exception {
		Cliente cliente = buscarPorId(id);
		cliente.setDocumento(documento);
		clienteRepository.save(cliente);
	}

	@Transactional
	public Cliente salvar(Cliente cliente) throws Exception {

		if (Period.between(cliente.getDataNascimento(), LocalDate.now()).getYears() < 19) {
			throw new Exception("Precisa ser maior de idade para fazer uma proposta");
		}

		List<Cliente> clienteEmails = clienteRepository.findByEmail(cliente.getEmail());

		if (!clienteEmails.isEmpty()) {
			throw new Exception("E-mail já existe");
		}

		List<Cliente> clienteCpf = clienteRepository.findByEmail(cliente.getCpf());

		if (!clienteCpf.isEmpty()) {
			throw new Exception("CPF já existe");
		}

		return clienteRepository.save(cliente);
	}

	public void salvarToken(String cpf, String email) throws Exception {
		List<Cliente> clientes = clienteRepository.findByEmailAndCpf(email, cpf);

		if (!clientes.isEmpty()) {
			Cliente cliente = clientes.get(0);
			cliente.setTokenUsado(false);
			cliente.setToken(gerarToken(cpf, email));
			cliente.setExpiredToken(LocalDate.now().plusDays(1));
			clienteRepository.save(cliente);
			fakeEnvioEmailService.enviar(new Mensagem());
		}
	}

	public void salvarSenha(String token, String senha) throws Exception {
		List<Cliente> clientes = clienteRepository.findByToken(token);

		if (clientes.isEmpty()) {
			throw new Exception("Token Inválido");
		}
		
		Cliente cliente = clientes.get(0);
		
		if (cliente.getTokenUsado()) {
			throw new Exception("Token já usado renove o token");
		}


		if (cliente.getExpiredToken().isBefore(LocalDate.now())) {
			throw new Exception("Token expired");
		}
		
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.update(senha.getBytes(), 0, senha.length());
		cliente.setSenha(new BigInteger(1, m.digest()).toString(16));
		cliente.setTokenUsado(true);
		clienteRepository.save(cliente);
		fakeEnvioEmailService.enviar(new Mensagem());
	}

	private String gerarToken(String cpf, String email) throws Exception {
		String texto = cpf + email + LocalDateTime.now().toString();
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(texto.getBytes(), 0, texto.length());
			return new BigInteger(1, m.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("Token Inválido");
		}
	}

}
