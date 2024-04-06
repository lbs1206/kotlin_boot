package com.example.balnk.exception

import org.springframework.http.HttpStatus

class CustomException(
    val status : HttpStatus,
    override val message : String,
    val code : Int,
) : RuntimeException()