package com.example.demo.DTO;

import java.util.ArrayList;
import java.util.List;

public class GrupoDto {
    public List<String> alunos;

    public void adicionarAluno(String aluno){
        if(alunos == null){
            alunos = new ArrayList<>();
            alunos.add(aluno);
            return;
        }
        if(!alunos.contains(aluno)){
            alunos.add(aluno);
            return;
        }
        throw new RuntimeException("Aluno já está no grupo");
    }

}
