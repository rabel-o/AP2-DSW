package com.ufc.AP2.controller;

import com.ufc.AP2.model.Aluno;
import com.ufc.AP2.model.AlunoRequestDTO;
import com.ufc.AP2.model.AlunoResponseDTO;
import com.ufc.AP2.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    @CrossOrigin(originPatterns = "*", allowCredentials = "true")
    public List<AlunoResponseDTO> getAll(){
        List<AlunoResponseDTO> alunoList = alunoService.findAll().stream().map(AlunoResponseDTO::new).toList();
        return alunoList;
    }

    @GetMapping("/nome/{nome}")
    @CrossOrigin(originPatterns = "*", allowCredentials = "true")
    public ResponseEntity<?> getAlunoByNome(@PathVariable String nome) {
        Optional<Aluno> aluno = alunoService.findByNome(nome);
        if (aluno.isPresent()) {
            return ResponseEntity.ok(new AlunoResponseDTO(aluno.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno not found");
        }
    }

    @PostMapping
    @CrossOrigin(originPatterns = "*", allowCredentials = "true")
    public void saveAluno(@RequestBody AlunoRequestDTO data) {
        Aluno alunoData = new Aluno(data);
        alunoService.save(alunoData);
    }

    @PutMapping("/{id}")
    @CrossOrigin(originPatterns = "*", allowCredentials = "true")
    public void updateAluno(@PathVariable Long id, @RequestBody AlunoRequestDTO data) {
        Aluno alunoData = alunoService.findById(id).orElseThrow(() -> new RuntimeException("Aluno not found"));
        alunoData.setNome(data.nome());
        alunoData.setCurso(data.curso());
        alunoData.setIra(data.ira());
        alunoService.save(alunoData);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(originPatterns = "*", allowCredentials = "true")
    public void deleteAluno(@PathVariable Long id)  {
        alunoService.deleteById(id);
    }

    @PutMapping("/nome/{nome}")
    @CrossOrigin(originPatterns = "*", allowCredentials = "true")
    public ResponseEntity<?> updateAlunoByNome(@PathVariable String nome, @RequestBody(required = false) AlunoRequestDTO data) {
        if (data == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request body is missing");
        }
        Aluno alunoData = alunoService.findByNome(nome).orElseThrow(() -> new RuntimeException("Aluno not found"));
        alunoData.setNome(data.nome());
        alunoData.setCurso(data.curso());
        alunoData.setIra(data.ira());
        alunoService.save(alunoData);
        return ResponseEntity.ok(new AlunoResponseDTO(alunoData));
    }

    @DeleteMapping("/nome/{nome}")
    @CrossOrigin(originPatterns = "*", allowCredentials = "true")
    public void deleteAlunoByNome(@PathVariable String nome)  {
        alunoService.deleteByNome(nome);
    }
}