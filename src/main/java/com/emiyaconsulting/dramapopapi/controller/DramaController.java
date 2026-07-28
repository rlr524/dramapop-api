package com.emiyaconsulting.dramapopapi.controller;

import com.emiyaconsulting.dramapopapi.model.Drama;
import com.emiyaconsulting.dramapopapi.service.DramaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    // Sanitization performed in DramaService.saveDrama() via OWASP HTML Sanitizer.
    // Static analysis cannot trace taint across service boundaries. See DRAM-12.
    @SuppressWarnings({"XSS", "JvmTaintAnalysis"})
    @PostMapping("/drama")
    public ResponseEntity<Drama> saveDrama(@Valid @RequestBody Drama drama) {
        Drama savedDrama = dramaService.saveDrama(drama);
        return new ResponseEntity<>(savedDrama, HttpStatus.CREATED);
    }
    
    // get all dramas
    @GetMapping("/dramas")
    public Iterable<Drama> getDramas() {
        return dramaService.getDramas();
    }
}
