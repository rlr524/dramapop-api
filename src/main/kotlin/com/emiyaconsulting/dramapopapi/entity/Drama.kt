package com.emiyaconsulting.dramapopapi.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany

@Entity
data class Drama (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val dramaID : Long,
    
    @Column(name= "drama_name", nullable = false)
    val dramaName: String,
    
    @Column(name="drama_year", nullable = true)
    val dramaYear: Int,

    @ManyToMany
    @JoinTable(
        name = "drama_actors",
        joinColumns = [JoinColumn(name="drama_id")],
        inverseJoinColumns = [JoinColumn(name="actor_id")]
    )
    var cast: MutableSet<Actor> = mutableSetOf()
)

