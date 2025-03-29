package com.emiyaconsulting.dramapopapi.controller

import com.emiyaconsulting.dramapopapi.entity.Drama
import com.emiyaconsulting.dramapopapi.service.DramaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/dramas")
class DramaController(private val dramaService: DramaService) {
    @GetMapping
    fun getAllDramas(): List<Drama> = dramaService.getAllDramas()
    
    @GetMapping("/{id}")
    fun getDramaById(@PathVariable id: Long): ResponseEntity<Drama> = dramaService.getDramaById(id)
    
    @PostMapping
    fun createDrama(@RequestBody drama: Drama): Drama = dramaService.createDrama(drama)
    
    @PutMapping("/{id}")
    fun updateDrama(@PathVariable id: Long, @RequestBody updatedDrama: Drama): ResponseEntity<Drama> = 
        dramaService.updateDrama(id, updatedDrama)
    
    @DeleteMapping("/{id}")
    fun deleteDrama(@PathVariable id: Long, @RequestBody updatedDrama: Drama): ResponseEntity<Drama> = 
        dramaService.deleteDrama(id, updatedDrama)
}