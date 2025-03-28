package com.emiyaconsulting.dramapopapi.entity

import jakarta.persistence.*

@Entity
data class Genre (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val genreID: Long,

    @Column(name = "name", nullable = false)
    val name: String,
)