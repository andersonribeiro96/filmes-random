package com.filmes.service.impl;

import com.filmes.domain.enums.ComidaEnum;
import com.filmes.service.ComidaService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Random;

@Service
public class ComidaServiceImpl implements ComidaService {

    @Override
    public ComidaEnum gerarComida() {
        long count = Arrays.stream(ComidaEnum.values()).count();
        Random random = new Random();
        return ComidaEnum.toEnum(random.nextInt(Integer.parseInt(String.valueOf(count))));
    }
}
