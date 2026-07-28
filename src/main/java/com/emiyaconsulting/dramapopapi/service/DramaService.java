package com.emiyaconsulting.dramapopapi.service;

import com.emiyaconsulting.dramapopapi.model.Drama;
import com.emiyaconsulting.dramapopapi.repository.DramaRepository;
import org.springframework.stereotype.Service;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

@Service
public class DramaService {
    private static final PolicyFactory SANITIZER = Sanitizers.FORMATTING.and(Sanitizers.LINKS);
    
    private final DramaRepository dramaRepository;

    public DramaService(DramaRepository dramaRepository) {
        this.dramaRepository = dramaRepository;
    }
    
    public Drama saveDrama(Drama drama) {
        if (drama.getTitle() != null) {
            drama.setTitle(SANITIZER.sanitize(drama.getTitle()));
        }
        if (drama.getDescription() != null) {
            drama.setDescription(SANITIZER.sanitize(drama.getDescription()));
        }
        return dramaRepository.save(drama);
    }
    
    public Iterable<Drama> getDramas() {
        return dramaRepository.findAll();
    }
}
