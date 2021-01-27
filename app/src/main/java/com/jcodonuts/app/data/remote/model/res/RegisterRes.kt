package com.jcodonuts.app.data.remote.model.res

data class RegisterRes(
    val message: String,
    val status: Int,
    val token: Token
)

data class Token(
    val accessToken: String,
    val refreshToken: String,
    val status_code: Int
)