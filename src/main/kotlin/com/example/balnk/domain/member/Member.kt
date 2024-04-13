package com.example.balnk.domain.member

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.Instant

@Entity
@Table(name = "member")
open class Member (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_key", nullable = false)
    open var id: Int? = null,

    @Column(name = "name", unique = true, nullable = false, length = 100)
    open var name: String,

    @Column(name = "secretWord", nullable = false, length = 100)
    open var secretWord: String,

    @Column(name = "reg_dttm")
    @CreationTimestamp
    open var regDttm: Instant? = null,

    @Column(name = "upd_dttm")
    @UpdateTimestamp
    open var updDttm: Instant? = null
): UserDetails{
    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
        return null
    }

    override fun getUsername(): String {
        return name
    }
    override fun getPassword(): String {
        return secretWord
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}