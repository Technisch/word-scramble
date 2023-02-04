package com.github.technisch.worldscramble

import com.github.technisch.worldscramble.game.GameManager
import com.github.technisch.worldscramble.listeners.PlayerListener
import com.github.technisch.worldscramble.providers.WordProvider
import org.bukkit.plugin.java.JavaPlugin

class WorldScramble : JavaPlugin() {

    lateinit var gameManger: GameManager
    lateinit var wordProvider: WordProvider

    override fun onEnable() {
        this.config.options().copyDefaults(true)
        this.saveConfig()

        this.wordProvider = WordProvider(this)
        this.gameManger = GameManager(this)

        this.server.pluginManager.registerEvents(PlayerListener(this.gameManger),this)
    }
}