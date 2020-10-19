package br.com.fiap.fiapcreditcard.repository;

import br.com.fiap.fiapcreditcard.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    List<Aluno> findAllByNomeContainingAndAtivoIsTrue(String nome);
}
