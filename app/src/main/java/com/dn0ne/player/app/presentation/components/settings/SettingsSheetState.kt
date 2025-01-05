package com.dn0ne.player.app.presentation.components.settings

import com.dn0ne.player.app.data.Settings

data class SettingsSheetState(
    val settings: Settings,
    val isShown: Boolean = false,
)
