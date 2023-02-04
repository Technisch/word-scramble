package com.github.technisch.worldscramble.game

import com.github.technisch.worldscramble.WorldScramble
import com.github.technisch.worldscramble.utils.Text.colors
import org.bukkit.entity.Ghast
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import java.text.DecimalFormat

class Game(
    private val plugin: WorldScramble,
    private val word: String,
    private val scrambledWord: String,
) : Listener {

    val start: Long = System.currentTimeMillis()
    var active: Boolean = true

    init {
        val server = plugin.server
        server.broadcastMessage("&5&lWORD SCRAMBLE &8- &7Raad het woord: &f$scrambledWord".colors())
    }

    @EventHandler
    fun onChat(event: AsyncPlayerChatEvent) {
        val player = event.player

        if (!active) {
            return
        }

        if (event.message == this.word) {
            plugin.gameManger.endGame()

            val time = DecimalFormat("0.0").format(
                ((System.currentTimeMillis() - start) / 1000).toDouble()
            )

            val server = plugin.server
            server.broadcastMessage("&5&lWORD SCRAMBLE &8- &d${player.name} &7Heeft het woord geraden in &f${time}&7s".colors())
        }
    }

}