package com.ufc.AP2.service;

import com.ufc.AP2.model.Aluno;
import com.ufc.AP2.repository.AlunoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    public Optional<Aluno> findByNome(String nome) {
        return repository.findByNome(nome);
    }

    public List<Aluno> findAll() {
        return repository.findAll();
    }

    public void save(Aluno aluno) {
        repository.save(aluno);
    }

    public Optional<Aluno> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void deleteByNome(String nome) {
        repository.deleteByNome(nome);
    }
}