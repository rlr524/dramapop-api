package com.emiyaconsulting.dramapopapi.entity

import jakarta.persistence.*
import org.hibernate.proxy.HibernateProxy

@Entity
@Table(name = "people")
data class Person (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val personID: Long,

    @Column(name = "first_name", nullable = true)
    val firstName: String?,

    @Column(name= "last_name", nullable = false)
    val lastName: String,

    @Column(name="role", nullable = true)
    val role: String?,

    @ManyToMany(mappedBy = "crew")
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
        other as Person

        return personID != null && personID == other.personID
    }

    final override fun hashCode(): Int =
        if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass.hashCode() else javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(  firstName = $firstName   ,   lastName = $lastName   ,   role = $role )"
    }
}