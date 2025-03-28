package com.emiyaconsulting.dramapopapi.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import java.time.LocalDate

@Entity
data class Drama (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val dramaID : Long,
    
    @Column(name= "name", nullable = false)
    val name: String,
    
    @Column(name="year", nullable = true)
    val year: Int,
    
    @Column(name="rating", nullable = true)
    val rating: Int,
    
    @Column(name="episodes", nullable = true)
    val episodes: Int,
    
    @Column(name="start_date", nullable = true)
    val startDate: LocalDate,
    
    @Column(name="end_date", nullable = true)
    val endDate: LocalDate,
    
    @Column(name="deleted", nullable = false)
    val deleted: Boolean = false,

    @ManyToMany
    @JoinTable(
        name = "dramas_actors",
        joinColumns = [JoinColumn(name="drama_id")],
        inverseJoinColumns = [JoinColumn(name="actor_id")]
    )
    var cast: MutableSet<Actor> = mutableSetOf(),

    @ManyToMany
    @JoinTable(
        name = "dramas_staff",
        joinColumns = [JoinColumn(name="drama_id")],
        inverseJoinColumns = [JoinColumn(name="staff_id")]
    )
    var crew: MutableSet<Actor> = mutableSetOf(),

    @ManyToMany
    @JoinTable(
    name = "dramas_tags",
    joinColumns = [JoinColumn(name="drama_id")],
    inverseJoinColumns = [JoinColumn(name="tag_id")]
)
    var tags: MutableSet<Tag> = mutableSetOf(),
    
    @ManyToOne
    @JoinColumn(name = "country_id", nullable = true)
    val country: Country,
    
    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = true)
    val genre: Genre,
)

