package com.emiyaconsulting.dramapopapi.controller;

import com.emiyaconsulting.dramapopapi.model.Drama;
import com.emiyaconsulting.dramapopapi.service.DramaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DramaController {
    private final DramaService dramaService;

    public DramaController(DramaService dramaService) {
        this.dramaService = dramaService;
    }
    
    // insert a single drama into the database
    @PostMapping("/drama")
    public ResponseEntity<Drama> saveDrama(@Validated @RequestBody Drama drama) {
        drama = new Drama();
        drama.setTitle(drama.getTitle());
        drama.setYear(drama.getYear());
        drama.setDescription(drama.getDescription());
        drama.setEpisodes(drama.getEpisodes());
        drama.setIconUrl(drama.getIconUrl());
        
        Drama savedDrama = dramaService.saveDrama(drama);
        return new ResponseEntity<>(savedDrama, HttpStatus.CREATED);
    }
    
    // get all dramas
    @GetMapping("/dramas")
    public Iterable<Drama> getDramas() {
        return dramaService.getDramas();
    }
}
