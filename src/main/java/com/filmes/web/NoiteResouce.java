package com.filmes.web;


import com.filmes.service.NoiteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/noite")
public class NoiteResouce {

    private final NoiteService noiteService;

    public NoiteResouce(NoiteService noiteService) {
        this.noiteService = noiteService;
    }


    @GetMapping("/discovery/{genero}/{nota}")
    public ResponseEntity<Object> discovery(@PathVariable("genero") String genero, @PathVariable("nota") String nota) {
        return ResponseEntity.ok(noiteService.gerarNoite(genero, nota));
    }

}
