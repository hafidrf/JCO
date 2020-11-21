package com.jcodonuts.app.ui.payment.linkaja

import com.jcodonuts.app.data.local.*

interface LinkajaControllerListener {
    fun onTransactionClick(linkajaTransaction: LinkajaTransaction)
    fun onTypeClick(linkajaTopupType: LinkajaTopupType)
}