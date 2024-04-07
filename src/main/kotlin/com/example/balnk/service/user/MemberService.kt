package com.example.balnk.service.user

import com.example.balnk.config.jwt.JwtTokenProvider
import com.example.balnk.domain.member.Member
import com.example.balnk.domain.member.MemberRepository
import com.example.balnk.exception.ErrorCode
import com.example.balnk.exception.ErrorCodeException
import jakarta.transaction.Transactional
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepo: MemberRepository,
    private val jwtTokenProvider: JwtTokenProvider
){
    @Transactional
    fun findMember(username: String, password: String): Member {
        return memberRepo.findMemberByNameAndSecretWord(username, password)
            ?: throw ErrorCodeException(ErrorCode.NOT_FOUND);
    }

    @Transactional
    fun findMemberByUsername(username: String): UserDetails {
        return memberRepo.findByName(username)?: throw ErrorCodeException(ErrorCode.NOT_FOUND)
    }

    @Transactional
    fun saveMember(username: String, password: String): Member {
        val member : Member = Member(name = username,secretWord = password);
        return memberRepo.save(member);
    }

    @Transactional
    fun deleteMember(username: String, password: String) {
        val member : Member = findMember(username,password)
        return memberRepo.delete(member)
    }

    @Transactional
    fun signIn(memberId: Int,username: String): String {

        val token: String = jwtTokenProvider.createToken(username,memberId);

        return token
    }

}