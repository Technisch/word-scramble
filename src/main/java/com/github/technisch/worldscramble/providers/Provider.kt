package com.github.technisch.worldscramble.providers

sealed interface Provider<P> {

    fun provide(): P


}