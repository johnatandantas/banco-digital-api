package br.com.johnworks.banco.digital.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.johnworks.banco.digital.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
	
	List<Cliente> findByEmailAndCpf(String email, String cpf);
	List<Cliente> findByCpf(String cpf);
	List<Cliente> findByEmail(String email);
	List<Cliente> findByToken(String token);

}
