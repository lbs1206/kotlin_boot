package com.example.balnk.config.jwt

import org.jetbrains.annotations.NotNull

data class AuthInfoResponse(
    @NotNull
    val grantType: String,
    @NotNull
    val accessToken: String,
    @NotNull
    val refreshToken: String,
)
