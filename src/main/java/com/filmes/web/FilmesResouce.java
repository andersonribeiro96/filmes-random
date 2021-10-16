package com.filmes.web;


import com.filmes.service.GerarFilmeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filmes")
public class FilmesResouce {

    private final GerarFilmeService gerarFilmeService;

    public FilmesResouce( GerarFilmeService gerarFilmeService) {
        this.gerarFilmeService = gerarFilmeService;
    }

    @GetMapping("/discovery/year/{genero}/{ano}/{nota}/{votos}")
    public ResponseEntity<Object> discovery(@PathVariable("genero") String genero, @PathVariable("ano") String ano, @PathVariable("nota") String nota, @PathVariable("votos") String votos) throws Exception {
        return ResponseEntity.ok(gerarFilmeService.gerarFilme(genero, ano, nota, votos));
    }

}
