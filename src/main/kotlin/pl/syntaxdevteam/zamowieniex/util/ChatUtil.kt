package pl.syntaxdevteam.zamowieniex.util

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import pl.syntaxdevteam.zamowieniex.ZamowienieX

object ChatUtil {
    private val miniMessage = MiniMessage.miniMessage()

    private fun getPrefix(): String {
        return ZamowienieX.instance.langManager.getMessage("prefix")
    }

    fun colorize(text: String): Component {
        val processedText = text.replace("\$prefix", getPrefix(), ignoreCase = true)
        return miniMessage.deserialize(processedText)
    }
}