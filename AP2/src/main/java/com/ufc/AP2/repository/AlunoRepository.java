package com.ufc.AP2.repository;

import com.ufc.AP2.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Optional<Aluno> findByNome(String nome);

    Optional<Aluno> deleteByNome(String nome);
}
