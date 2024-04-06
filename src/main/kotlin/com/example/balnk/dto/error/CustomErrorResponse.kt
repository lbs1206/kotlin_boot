package com.example.balnk.dto.error

import org.jetbrains.annotations.NotNull
import org.springframework.validation.annotation.Validated

@Validated
data class CustomErrorResponse(
    @NotNull
    val message: String,
    @NotNull
    val code: Int
)