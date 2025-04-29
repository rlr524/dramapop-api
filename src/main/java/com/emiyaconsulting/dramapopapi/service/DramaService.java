package com.emiyaconsulting.dramapopapi.service;

import com.emiyaconsulting.dramapopapi.model.Drama;
import com.emiyaconsulting.dramapopapi.repository.DramaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DramaService {
    private final DramaRepository dramaRepository;

    public DramaService(DramaRepository dramaRepository) {
        this.dramaRepository = dramaRepository;
    }
    
    public Drama saveDrama(Drama drama) {
        return dramaRepository.save(drama);
    }
    
    public Iterable<Drama> getDramas() {
        return dramaRepository.findAll();
    }
}
