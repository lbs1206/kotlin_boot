package com.example.balnk.domain.member

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant

@Entity
@Table(name = "member")
open class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_key", nullable = false)
    open var id: Int? = null,

    @Column(name = "username", unique = true, nullable = false, length = 100)
    open var username: String,

    @Column(name = "password", nullable = false, length = 100)
    open var password: String,

    @Column(name = "reg_dttm")
    @CreationTimestamp
    open var regDttm: Instant? = null,

    @Column(name = "upd_dttm")
    @UpdateTimestamp
    open var updDttm: Instant? = null
){
}