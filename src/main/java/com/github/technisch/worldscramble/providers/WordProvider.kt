package com.github.technisch.worldscramble.providers

import com.github.technisch.worldscramble.WorldScramble

class WordProvider(plugin: WorldScramble) : Provider<Pair<String, String>> {

    private val contents: List<String>

    init {
        contents = plugin.config.getStringList("words")
    }

    override fun provide(): Pair<String, String> {
        val word = contents.random()
        val shuffled = word.split("").shuffled().joinToString("")
        return word to shuffled
    }

}