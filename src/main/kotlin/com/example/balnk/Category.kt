package com.example.balnk

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "category")
open class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @Column(name = "type", nullable = false, length = 50)
    open var type: String? = null

    @Column(name = "title", length = 100)
    open var title: String? = null

    @Column(name = "created_at", nullable = false)
    open var createdAt: Instant? = null

    @Column(name = "created_by", nullable = false, length = 20)
    open var createdBy: String? = null

    @Column(name = "updated_at")
    open var updatedAt: Instant? = null

    @Column(name = "updated_by", length = 20)
    open var updatedBy: String? = null
}