package com.jcodonuts.app.ui.payment.linkaja

import com.jcodonuts.app.data.local.LinkajaTopupType
import com.jcodonuts.app.data.local.LinkajaTransaction

interface LinkajaControllerListener {
    fun onTransactionClick(linkajaTransaction: LinkajaTransaction)
    fun onTypeClick(linkajaTopupType: LinkajaTopupType)
}