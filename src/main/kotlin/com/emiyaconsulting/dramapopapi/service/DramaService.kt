package com.emiyaconsulting.dramapopapi.service

import com.emiyaconsulting.dramapopapi.entity.Drama
import com.emiyaconsulting.dramapopapi.repository.DramaRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class DramaService(private val dramaRepository: DramaRepository) {
    fun getAllDramas(): MutableList<Drama> = dramaRepository.findAll()
    
    fun getDramaById(id: Long): ResponseEntity<Drama> = dramaRepository.findById(id).map { drama ->
        ResponseEntity.ok(drama)
    }.orElse(ResponseEntity.notFound().build())
    
    fun createDrama(drama: Drama): Drama = dramaRepository.save(drama)
    
    fun updateDrama(id: Long, updatedDrama: Drama): ResponseEntity<Drama> = 
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
    
    fun deleteDrama(id: Long, updatedDrama: Drama): ResponseEntity<Drama> = 
        dramaRepository.findById(id).map { existingDrama ->
        val dramaToUpdate = existingDrama.copy(
            deleted = true,
        )
        ResponseEntity.ok(dramaRepository.save(dramaToUpdate))
    }.orElse(ResponseEntity.notFound().build())
}