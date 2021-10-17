package com.filmes.web;


import com.filmes.service.FilmeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filmes")
public class FilmesResouce {

    private final FilmeService filmeService;

    public FilmesResouce(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @GetMapping("/discovery/year/{genero}/{nota}")
    public ResponseEntity<Object> discovery(@PathVariable("genero") String genero, @PathVariable("nota") String nota) {
        return ResponseEntity.ok(filmeService.criaFilme(genero, nota));
    }

}
