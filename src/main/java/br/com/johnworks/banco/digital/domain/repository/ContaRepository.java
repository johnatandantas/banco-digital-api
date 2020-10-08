package br.com.johnworks.banco.digital.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.johnworks.banco.digital.domain.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta,Long> {

	List<Conta> findByConta(String conta);

}
