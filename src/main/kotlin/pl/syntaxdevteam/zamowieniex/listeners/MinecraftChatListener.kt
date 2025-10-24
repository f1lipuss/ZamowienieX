
package pl.syntaxdevteam.zamowieniex.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import pl.syntaxdevteam.zamowieniex.ZamowienieX

class MinecraftChatListener(private val plugin: ZamowienieX) : Listener {

    @EventHandler
    fun onPlayerChat(event: AsyncPlayerChatEvent) {
        plugin.discordBotManager?.let {
            val message = event.message
            it.sendMessageToDiscord(event.player, message)
        }
    }
}
