package com.ufc.AP2.model;

public record AlunoResponseDTO(Long id, String nome, String curso, Integer ira) {
    public AlunoResponseDTO(Aluno aluno) {
        this(aluno.getId(), aluno.getNome(), aluno.getCurso(), aluno.getIra());
    }
}
