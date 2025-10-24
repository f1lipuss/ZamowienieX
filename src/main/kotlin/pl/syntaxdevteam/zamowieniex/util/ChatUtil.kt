package pl.syntaxdevteam.zamowieniex.util

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage

object ChatUtil {
    private val miniMessage = MiniMessage.miniMessage()
    private const val PREFIX = "<bold><gradient:#E040FB:#8E24AA>[ZEX]</gradient></bold>"

    fun colorize(text: String): Component {
        val processedText = text.replace("\$prefix", PREFIX, ignoreCase = true)
        return miniMessage.deserialize(processedText)
    }
}