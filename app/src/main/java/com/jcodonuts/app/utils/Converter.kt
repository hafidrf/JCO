package com.jcodonuts.app.utils

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
    }
}