package com.filmes.service.impl;

import com.filmes.domain.enums.BebidaEnum;
import com.filmes.service.BebidaService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Random;

@Service
public class BebidaServiceImpl implements BebidaService {

    @Override
    public BebidaEnum gerarBebida() {
        long count = Arrays.stream(BebidaEnum.values()).count();
        Random random = new Random();
        return BebidaEnum.toEnum(random.nextInt((int) count));
    }
}
