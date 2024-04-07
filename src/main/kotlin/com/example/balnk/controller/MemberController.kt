package com.example.balnk.controller

import com.example.balnk.dto.user.request.DeleteMemberRequest
import com.example.balnk.dto.user.request.PostMemberRequest
import com.example.balnk.dto.user.request.PostSignInRequest
import com.example.balnk.exception.ErrorCode
import com.example.balnk.exception.ErrorCodeException
import com.example.balnk.service.user.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/member")
class MemberController(
    private val memberService: MemberService,
    private val passwordEncoder: PasswordEncoder,
){

    @GetMapping("/test")
    fun getMemberTest() : String{
        return "hello";
    }

    @PostMapping("")
    fun postMember(@RequestBody request: PostMemberRequest): ResponseEntity<Any> {
        val password: String = passwordEncoder.encode(request.password)
        val member = memberService.saveMember(request.username,password)
        return ResponseEntity.status(HttpStatus.CREATED).body(object {val data = "success"})
    }

    @PostMapping("/sign-in")
    fun postSignIn(@RequestBody request: PostSignInRequest): ResponseEntity<Any> {
        val member = memberService.findMemberByUsername(request.username)

        if(!passwordEncoder.matches(request.password,member.password)){
            throw ErrorCodeException(ErrorCode.InvalidPassword)
        }

        val mem = memberService.findMember(member.username,member.password)

        val token : String = memberService.signIn(mem.id ?: 0, mem.name)

        return ResponseEntity.status(HttpStatus.CREATED).body(object {val token = token})
    }


    @DeleteMapping("")
    fun deleteMember(@RequestBody request: DeleteMemberRequest): ResponseEntity<Any>{
        val member = memberService.findMemberByUsername(request.username)

        if(!passwordEncoder.matches(request.password,member.password)){
            throw ErrorCodeException(ErrorCode.InvalidPassword)
        }

        memberService.deleteMember(request.username,member.password);
        return ResponseEntity.status(HttpStatus.OK).body(object {val data = "success"});
    }
}