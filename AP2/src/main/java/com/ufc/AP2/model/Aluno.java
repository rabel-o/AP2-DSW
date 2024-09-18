package com.ufc.AP2.model;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "Aluno")
@Entity(name = "aluno")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Aluno {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String curso;
    private Integer ira;


    public Aluno(AlunoRequestDTO data) {
        this.nome = data.nome();
        this.curso = data.curso();
        this.ira = data.ira();
    }

}
