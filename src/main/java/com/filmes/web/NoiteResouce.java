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


    @GetMapping("/discovery/{genero}/{ano}")
    public ResponseEntity<Object> discovery(@PathVariable("genero") String genero, @PathVariable("ano") String ano, @PathVariable("nota") String nota, @PathVariable("votos") String votos) throws Exception {
        return ResponseEntity.ok(noiteService.gerarNoite(genero, ano, nota, votos));
    }

}
