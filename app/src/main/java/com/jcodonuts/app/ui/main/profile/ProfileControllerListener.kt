package com.jcodonuts.app.ui.main.profile

interface ProfileControllerListener {
    fun onMenuClick(index: Int)
    fun onSignOut()
    fun onEditProfile()
    fun onLinkAjaClick()
}