package com.jcodonuts.app.ui.payment.topup

import android.util.Log
import android.view.View
import com.airbnb.epoxy.AsyncEpoxyController
import com.jcodonuts.app.*
import com.jcodonuts.app.data.local.*

class TopupController() : AsyncEpoxyController() {
    private val TAG = "TopupController"

    var data: List<BaseCell> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    var idExpand: Int = -1
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        data.forEach { cellData ->
            when(cellData) {
                is TopupItem -> addTopupItem(cellData)
                is TopupSectionHeader -> addTopupSectionHeader(cellData)
            }
        }
    }

    private fun addTopupItem(cellData: TopupItem){
        topupItem {
            id(cellData.id)
            data(cellData)
            isExpand(idExpand == cellData.id)
            onExpand(View.OnClickListener {
                Log.d(TAG, "OnClickListener");
                idExpand = if (cellData.id != idExpand) {
                    cellData.id
                } else {
                    -1
                }
            })
        }
        if(idExpand == cellData.id){
            topupInstruction {
                id(cellData.topupInstuction.hashCode())
                data(cellData.topupInstuction)
            }
            cellData.topupInstructionItems.map {
                topupInstructionItem{
                    id(it.hashCode())
                    data(it)
                }
            }
        }
    }

    private fun addTopupSectionHeader(cellData: TopupSectionHeader){
        topupSectionHeader {
            id(cellData.hashCode())
            data(cellData)
        }
    }
}