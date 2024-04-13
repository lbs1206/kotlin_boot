package com.example.balnk.domain.member

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository : JpaRepository<Member, Int> {
    fun findMemberByNameAndSecretWord(name: String,secretWord: String): Member?

    fun findByName(name: String): UserDetails?
}