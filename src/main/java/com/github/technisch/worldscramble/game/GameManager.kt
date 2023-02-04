package com.github.technisch.worldscramble.game

import com.github.technisch.worldscramble.WorldScramble
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask

class GameManager(private val plugin: WorldScramble) {

    var game: Game? = null
    var scheduled: BukkitTask? = null

    fun scheduleNewGame() {
        val runEvery = plugin.config.getInt("run-every", 180)
        this.scheduled = plugin.server.scheduler.runTaskLater(plugin, Runnable { this.createGame() }, runEvery * 20L)
    }

    fun createGame() {
        val word = plugin.wordProvider.provide()
        this.game = Game(plugin, word.first, word.second)
        plugin.server.pluginManager.registerEvents(this.game!!, plugin)
    }

    fun endGame() {
        AsyncPlayerChatEvent.getHandlerList().unregister(game!!)
        this.game = null
        this.scheduleNewGame()
    }

}