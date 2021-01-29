package com.jcodonuts.app.data.local

data class ProfileHeader(val img:String, val name:String, val balance:String, val point:String):BaseCell()

data class ProfileMenuHeader(val title:String):BaseCell()

data class ProfileMenu(val icon:Int, val title:String, val type:Int):BaseCell()

data class SocmedMenu(val icon:Int, val title:String, val type:Int):BaseCell()

data class ProfileFooter(var showLoading:Boolean=false):BaseCell()

data class ProfileData(val img:String, val name:String):BaseCell()