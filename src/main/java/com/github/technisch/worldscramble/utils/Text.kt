package com.github.technisch.worldscramble.utils

import org.bukkit.ChatColor

object Text {

    fun String.colors(): String {
        return ChatColor.translateAlternateColorCodes('&', this)
    }

}