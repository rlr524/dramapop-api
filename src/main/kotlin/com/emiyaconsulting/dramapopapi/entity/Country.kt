package com.emiyaconsulting.dramapopapi.entity

import jakarta.persistence.*

@Entity
data class Country (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val countryID: Long,

    @Column(name = "name", nullable = false)
    val name: String,
)