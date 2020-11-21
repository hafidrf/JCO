package com.jcodonuts.app.data.local

data class TopupItem(
        val id:Int,
        val title:String,
        val iconURL:String,
        var isExpand:Boolean,
        val topupInstructionItems:List<TopupInstructionItem>,
        val topupInstuction:TopupInstuction
):BaseCell()

data class TopupInstructionItem(val title:String, var position:String):BaseCell()

data class TopupInstuction(val title:String):BaseCell()