package com.example.balnk.controller

import com.example.balnk.domain.member.Member
import com.example.balnk.dto.user.request.DeleteMemberRequest
import com.example.balnk.dto.user.request.PostMemberRequest
import com.example.balnk.service.user.MemberService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/member")
class MemberController (
    private val memberService: MemberService,
    private val passwordEncoder: PasswordEncoder
){

    @GetMapping("/test")
    fun getMemberTest() : String{
        return "hello";
    }

    @PostMapping("")
    fun postMember(@RequestBody request: PostMemberRequest): ResponseEntity<Any> {
        val password: String = passwordEncoder.encode(request.password)
        val member = memberService.saveMember(request.username,password)
        return ResponseEntity.status(HttpStatus.CREATED).body(object {val data = "success"});
    }

    @DeleteMapping("")
    fun deleteMember(@RequestBody request: DeleteMemberRequest): ResponseEntity<Any>{
        memberService.deleteMember(request.username,request.password);
        return ResponseEntity.status(HttpStatus.OK).body(object {val data = "success"});
    }
}