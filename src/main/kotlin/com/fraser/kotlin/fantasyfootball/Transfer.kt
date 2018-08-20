package com.fraser.kotlin.fantasyfootball

data class Transfer(val outgoing: Player, val incoming: Player) {
    val profit = outgoing.price - incoming.price

    override fun toString(): String {
        return String.format("%11s => %11s, Profit: £%.1fM", outgoing.name, incoming.name, profit)
    }

    fun getOutgoingName(): String {
        return outgoing.name
    }

    fun getIncomingName(): String {
        return incoming.name
    }
}
