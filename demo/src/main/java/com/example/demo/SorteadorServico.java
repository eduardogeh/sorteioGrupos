package com.example.demo;

import com.example.demo.DTO.GrupoDto;
import com.example.demo.DTO.SorteioDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SorteadorServico {

    private final ObjectMapper mapper = new ObjectMapper();
    private List<GrupoDto> listaGrupos = null;

    public String sortear(String json) {
        try {
            listaGrupos = new ArrayList<>();
            SorteioDto sorteioDto = mapper.readValue(json, SorteioDto.class);
            distribuirEmGrupos(sorteioDto);
            return converterEmJson(listaGrupos);
        }catch (Exception e){
            throw new RuntimeException("Erro ao sortear os alunos: " + e.getMessage());
        }
    }

    private String converterEmJson(List<GrupoDto> listaGrupos) {
        try {
            return mapper.writeValueAsString(listaGrupos);
        } catch (JsonProcessingException e) {
            return "Erro ao converter json";
        }
    }

    private void distribuirEmGrupos(SorteioDto sorteioDto) {
        List<String> alunos = sorteioDto.alunos;
        Integer qtdPorGrupo = sorteioDto.qtdPorGrupo;
        Integer qtdGrupos = (int) Math.ceil((double) alunos.size() / qtdPorGrupo);
        sortearGrupos(alunos, qtdPorGrupo, qtdGrupos);
    }

    private void sortearGrupos(List<String> alunos, Integer qtdPorGrupo, Integer qtdGrupos) {
        for (int i = 0; i < qtdGrupos; i++) {
            GrupoDto grupoDto = new GrupoDto();
            adicionarAlunosAoGrupo(alunos, qtdPorGrupo, grupoDto);
            listaGrupos.add(grupoDto);
        }
    }

    private void adicionarAlunosAoGrupo(List<String> alunos, Integer qtdPorGrupo, GrupoDto grupoDto) {
        for (int j = 0; j < qtdPorGrupo && alunos.size()>0; j++) {
            Integer posicao = gerarAleatorio(alunos.size());
            grupoDto.adicionarAluno(alunos.get(posicao));
            alunos.remove(alunos.get(posicao));
        }
    }

    private Integer gerarAleatorio(int size) {
        return (int) (Math.random() * size);
    }
}
