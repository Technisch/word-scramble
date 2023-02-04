package com.github.technisch.worldscramble.listeners

import com.github.technisch.worldscramble.game.GameManager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class PlayerListener(private val gameManager: GameManager) : Listener {

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        if (gameManager.scheduled != null || gameManager.game != null) {
            return
        }

        if (event.player.server.onlinePlayers.size >= 2) {
            gameManager.scheduleNewGame()
        }
    }
    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        if (gameManager.scheduled != null) {
            if (event.player.server.onlinePlayers.size < 2) {
                gameManager.scheduled?.cancel()
            }
        }
    }
}