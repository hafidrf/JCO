package com.jcodonuts.app.data.local

data class HomeDialogQrcode(
    val qrcodes: List<Qrcode>
):BaseCell()

data class Qrcode(
    val credit: String,
    val imgURL: String,
    val phone: String
):BaseCell()