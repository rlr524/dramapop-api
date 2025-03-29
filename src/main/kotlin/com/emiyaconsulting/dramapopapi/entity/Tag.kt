package com.emiyaconsulting.dramapopapi.entity

import jakarta.persistence.*
import org.hibernate.proxy.HibernateProxy

@Entity
@Table(name = "tags")
data class Tag (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val tagID: Long,
    
    @Column(name = "name", nullable = false)
    val name: String,

    @ManyToMany(mappedBy = "tags")
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
        other as Tag

        return tagID != null && tagID == other.tagID
    }

    final override fun hashCode(): Int =
        if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass.hashCode() else javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(  name = $name )"
    }
}