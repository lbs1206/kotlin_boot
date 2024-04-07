package com.example.balnk.dto.user.request

import org.jetbrains.annotations.NotNull

data class PostSignInRequest(
    @NotNull
    val username: String,
    @NotNull
    val password: String
)
