package com.example.balnk.dto.user.request

import org.jetbrains.annotations.NotNull
import org.springframework.validation.annotation.Validated

@Validated
data class PostMemberRequest(
    @NotNull
    val username: String,
    @NotNull
    val password: String
)
