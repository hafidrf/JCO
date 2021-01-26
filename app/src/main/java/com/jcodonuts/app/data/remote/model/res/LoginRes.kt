package com.jcodonuts.app.data.remote.model.res

data class LoginRes(
    val accessToken: String,
    val refreshToken: String,
    val status_code: Int,
    val error: String,
)