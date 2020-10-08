package br.com.johnworks.banco.digital.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.johnworks.banco.digital.domain.model.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao,Long>  {

}
