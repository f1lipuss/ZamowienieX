
package pl.syntaxdevteam.zamowieniex.discord

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.bukkit.Bukkit
import pl.syntaxdevteam.zamowieniex.ZamowienieX
import pl.syntaxdevteam.zamowieniex.util.ChatUtil

class DiscordListener(private val plugin: ZamowienieX) : ListenerAdapter() {

    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (event.author.isBot) {
            return
        }

        val configuredChannelId = plugin.config.getString("discord.channel-id")
        if (event.channel.id != configuredChannelId) {
            return
        }

        val author = event.author.name
        val message = event.message.contentDisplay

        Bukkit.getScheduler().runTask(plugin, Runnable {
            val format = plugin.langManager.getMessage("discord", "discord-to-minecraft")
            val formattedMessage = format
                .replace("{author}", author, ignoreCase = false)
                .replace("{message}", message, ignoreCase = false)
            Bukkit.broadcast(ChatUtil.colorize(formattedMessage))
        })
    }
}
