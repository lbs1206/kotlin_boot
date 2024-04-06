package com.example.balnk.domain.member

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository : JpaRepository<Member, Int> {
    fun findMemberByUsernameAndPassword(username: String,password: String): Member?
}