package br.com.johnworks.banco.digital.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.johnworks.banco.digital.domain.model.Proposta;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta,Long> {


}
