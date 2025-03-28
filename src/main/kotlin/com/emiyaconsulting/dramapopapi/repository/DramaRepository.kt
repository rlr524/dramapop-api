package com.emiyaconsulting.dramapopapi.repository

import org.springframework.data.jpa.repository.JpaRepository
import com.emiyaconsulting.dramapopapi.entity.Drama

interface DramaRepository : JpaRepository<Drama, Long> {
}