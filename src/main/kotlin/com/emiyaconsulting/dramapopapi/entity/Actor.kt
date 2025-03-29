package com.emiyaconsulting.dramapopapi.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany

@Entity
data class Actor (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val actorID: Long,
    
    @Column(name = "first_name", nullable = true)
    val firstName: String?,
    
    @Column(name= "last_name", nullable = false)
    val lastName: String,
    
    @ManyToMany(mappedBy = "cast")
    var dramas: MutableSet<Drama>? = mutableSetOf()
)