package com.example.balnk.service.user

import com.example.balnk.domain.member.Member
import com.example.balnk.domain.member.MemberRepository
import com.example.balnk.exception.ErrorCode
import com.example.balnk.exception.ErrorCodeException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class MemberService (
    private val memberRepo: MemberRepository
){
    @Transactional
    fun findMember(username: String, password: String): Member {
        return memberRepo.findMemberByUsernameAndPassword(username, password)
            ?: throw ErrorCodeException(ErrorCode.NOT_FOUND);
    }

    @Transactional
    fun saveMember(username: String, password: String): Member {
        val member : Member = Member(username = username,password = password);
        return memberRepo.save(member);
    }

    @Transactional
    fun deleteMember(username: String, password: String) {
        val member : Member = findMember(username,password)
        return memberRepo.delete(member)
    }
}