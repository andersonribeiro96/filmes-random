package com.filmes.service.impl;

import com.filmes.domain.enums.SobremesaEnum;
import com.filmes.service.SobremesaService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Random;

@Service
public class SobremesaServiceImpl implements SobremesaService {

    @Override
    public SobremesaEnum gerarSobremesa() {
        long count = Arrays.stream(SobremesaEnum.values()).count();
        Random random = new Random();
        return SobremesaEnum.toEnum(random.nextInt((int) count));
    }
}
