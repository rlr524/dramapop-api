package com.emiyaconsulting.dramapopapi.entity

import jakarta.persistence.*

@Entity
data class Staff (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val staffID: Long,

    @Column(name = "first_name", nullable = true)
    val firstName: String,

    @Column(name= "last_name", nullable = false)
    val lastName: String,
    
    @Column(name="role", nullable = true)
    val role: String,

    @ManyToMany(mappedBy = "crew")
    var dramas: MutableSet<Drama> = mutableSetOf()
)