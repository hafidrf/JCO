package com.jcodonuts.app.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.text.NumberFormat
import java.util.*

class Converter {
    companion object {
        fun rupiah(number: Double): String{
            val localeID =  Locale("in", "ID")
            val numberFormat = NumberFormat.getCurrencyInstance(localeID)
            return numberFormat.format(number).toString().replace(",00","")
        }

        fun rupiah(number:String):String{
            return try {
                val data = number.toDouble()
                rupiah(data)
            }catch (e:Exception){
                ""
            }
        }

        fun md5(input:String): String {
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }
    }
}