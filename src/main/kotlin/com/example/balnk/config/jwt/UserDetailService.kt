package com.example.balnk.config.jwt

import com.example.balnk.domain.member.MemberRepository
import com.example.balnk.exception.CustomException
import com.example.balnk.exception.ErrorCode
import com.example.balnk.exception.ErrorCodeException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailService (private val memberRepository: MemberRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        return memberRepository.findByName(username)?: throw ErrorCodeException(ErrorCode.AUTH_FAIL)
    }
}