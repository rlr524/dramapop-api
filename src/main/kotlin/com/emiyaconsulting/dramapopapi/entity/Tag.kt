package com.emiyaconsulting.dramapopapi.entity

import jakarta.persistence.*

@Entity
data class Tag (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val tagID: Long,
    
    @Column(name = "name", nullable = false)
    val name: String,

    @ManyToMany(mappedBy = "tags")
    var dramas: MutableSet<Drama>? = mutableSetOf()
)