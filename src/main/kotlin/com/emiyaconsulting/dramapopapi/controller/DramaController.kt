package com.emiyaconsulting.dramapopapi.controller

import com.emiyaconsulting.dramapopapi.entity.Drama
import com.emiyaconsulting.dramapopapi.repository.DramaRepository
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
class DramaController(private val dramaRepository: DramaRepository) {
    @GetMapping
    fun getAllDramas(): List<Drama> = dramaRepository.findAll()
    
    @GetMapping("/{id}")
    fun getDramaById(@PathVariable id: Long): ResponseEntity<Drama> = dramaRepository.findById(id).map { drama ->
        ResponseEntity.ok(drama)
    }.orElse(ResponseEntity.notFound().build())
    
    @PostMapping
    fun createDrama(@RequestBody drama: Drama): Drama = dramaRepository.save(drama)
    
    @PutMapping("/{id}")
    fun updateDrama(@PathVariable id: Long, @RequestBody updatedDrama: Drama): ResponseEntity<Drama> = 
        dramaRepository.findById(id).map { existingDrama -> 
            val dramaToUpdate = existingDrama.copy(
                name = updatedDrama.name,
                year = updatedDrama.year,
                rating = updatedDrama.rating,
                episodes =  updatedDrama.episodes,
                startDate = updatedDrama.startDate,
                endDate = updatedDrama.endDate,
                cast = updatedDrama.cast,
                crew = updatedDrama.crew,
                genre = updatedDrama.genre,
                tags = updatedDrama.tags,
                country = updatedDrama.country,
            )
            ResponseEntity.ok(dramaRepository.save(dramaToUpdate))
        }.orElse(ResponseEntity.notFound().build())
    
    @DeleteMapping("/{id}")
    fun deleteDrama(@PathVariable id: Long, @RequestBody updatedDrama: Drama): ResponseEntity<Drama> =
        dramaRepository.findById(id).map { existingDrama -> 
            val dramaToUpdate = existingDrama.copy(
                deleted = true,
            )
            ResponseEntity.ok(dramaRepository.save(dramaToUpdate))
        }.orElse(ResponseEntity.notFound().build())
}