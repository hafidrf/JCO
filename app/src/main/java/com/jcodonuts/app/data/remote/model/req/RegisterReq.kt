package com.jcodonuts.app.data.remote.model.req

data class RegisterReq(
    val member_name: String,
    val member_email: String,
    val member_phone: String,
    val member_phone2: String,
    val member_password: String,
    val member_confpassword: String,
    val member_ip: String,
    val member_useragent: String
)