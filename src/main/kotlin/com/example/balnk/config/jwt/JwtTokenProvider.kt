package com.example.balnk.config.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import jakarta.annotation.PostConstruct
import jakarta.servlet.http.HttpServletRequest
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import io.jsonwebtoken.security.Keys
import java.util.*

@Slf4j
@Component
class JwtTokenProvider(private val userDetailService: UserDetailService){
    @Value("\${jwt.secret}")
    lateinit var secretKey: String

    @Value("\${jwt.access-expire}")
    lateinit var accessExpire: String

    private val key by lazy { Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)) }



    // JWT 토큰 생성
    fun createToken(userPk: String,userId: Int): String {
        val claims: Claims = Jwts.claims().setSubject(userPk) // JWT payload 에 저장되는 정보단위
        claims["userPk"] = userPk // 정보는 key / value 쌍으로 저장된다.
        claims["userId"] = userId
        val now = Date()
        return Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .setClaims(claims) // 정보 저장
            .setIssuedAt(now) // 토큰 발행 시간 정보
            .setExpiration(Date(now.time + (1000 * 60 * accessExpire.toLong()))) // set Expire Time
            .signWith(key,SignatureAlgorithm.HS256 ) // 사용할 암호화 알고리즘과
            // signature 에 들어갈 secret값 세팅
            .compact()
    }

    // JWT 토큰에서 인증 정보 조회
    fun getAuthentication(token: String): Authentication {
        val userDetails = userDetailService.loadUserByUsername(getUserPk(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    // 토큰에서 회원 정보 추출
    fun getUserPk(token: String): String {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).body.subject
    }

    // Request의 Header에서 token 값을 가져옵니다. "X-AUTH-TOKEN" : "TOKEN값'
    fun resolveToken(request: HttpServletRequest): String? {
        return request.getHeader("Authorization")
    }

    // 토큰의 유효성 + 만료일자 확인
    fun validateToken(jwtToken: String): Boolean {
        return try {
            val claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken)
            !claims.body.expiration.before(Date())
        } catch (e: Exception) {
            false
        }
    }

}