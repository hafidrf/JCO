package com.jcodonuts.app.ui.payment.linkaja

import com.airbnb.epoxy.AsyncEpoxyController
import com.jcodonuts.app.*
import com.jcodonuts.app.data.local.*

class LinkajaController(private val listener: LinkajaControllerListener) : AsyncEpoxyController() {
    private val TAG = "HomeController"

    var data: List<BaseCell> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        data.forEach { cellData ->
            when(cellData) {
                is Divider16 -> addDivider16(cellData)
                is LinkajaCard -> addLinkajaCard(cellData)
                is LinkajaTopupType -> addLinkajaPaymentType(cellData, listener)
                is LinkajaSectionHeader -> addLinkajaSectionHeader(cellData)
                is LinkajaTransaction -> addLinkajaTransaction(cellData)
                is LinkajaTransactionDate -> addLinkajaTransactionDate(cellData)
            }
        }
    }

    private fun addDivider16(cellData: Divider16){
        divider16 {
            id(cellData.hashCode())
        }
    }

    private fun addLinkajaCard(cellData: LinkajaCard){
        linkajaCard {
            id(cellData.hashCode())
            data(cellData)
        }
    }

    private fun addLinkajaPaymentType(cellData: LinkajaTopupType, listener: LinkajaControllerListener){
        linkajaTopupType {
            id(cellData.hashCode())
            data(cellData)
            onItemClick(listener)
        }
    }

    private fun addLinkajaSectionHeader(cellData: LinkajaSectionHeader){
        linkajaSectionHeader {
            id(cellData.hashCode())
            data(cellData)
        }
    }

    private fun addLinkajaTransaction(cellData: LinkajaTransaction){
        linkajaTransaction {
            id(cellData.hashCode())
            data(cellData)
        }
    }

    private fun addLinkajaTransactionDate(cellData: LinkajaTransactionDate){
        linkajaTransactionDate {
            id(cellData.hashCode())
            data(cellData)
        }
    }
}