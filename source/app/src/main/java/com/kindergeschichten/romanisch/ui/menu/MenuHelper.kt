package com.kindergeschichten.romanisch.ui.menu


import com.kindergeschichten.romanisch.R


object MenuHelper {
    val menuItems = listOf(
        MenuItem(MenuAction.NAV_VEREIN, R.string.verein_title,R.drawable.ic_nav_ueber),
        MenuItem(MenuAction.NAV_IMPRESSUM, R.string.impressum_title,R.drawable.privacy_policy),
        MenuItem(MenuAction.NAV_SPONSOR, R.string.sponsor_title,R.drawable.ic_sponsor),
        MenuItem(MenuAction.SETTINGS, R.string.settings,R.drawable.ic_settings),
        MenuItem(MenuAction.SHARE, R.string.share_title,R.drawable.ic_share),
        MenuItem(MenuAction.RATE, R.string.rate_title,R.drawable.ic_rate))
}