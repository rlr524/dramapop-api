package com.emiyaconsulting.dramapopapi.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import org.hibernate.proxy.HibernateProxy

@Entity
@Table(name = "actors")
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
) {
    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        val oEffectiveClass =
            if (other is HibernateProxy) other.hibernateLazyInitializer.persistentClass else other.javaClass
        val thisEffectiveClass =
            if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass else this.javaClass
        if (thisEffectiveClass != oEffectiveClass) return false
        other as Actor

        return actorID != null && actorID == other.actorID
    }

    final override fun hashCode(): Int =
        if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass.hashCode() else javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(  firstName = $firstName   ,   lastName = $lastName )"
    }
}