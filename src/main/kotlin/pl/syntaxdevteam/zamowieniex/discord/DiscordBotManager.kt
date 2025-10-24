
package pl.syntaxdevteam.zamowieniex.discord

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel
import net.dv8tion.jda.api.requests.GatewayIntent
import org.bukkit.entity.Player
import pl.syntaxdevteam.zamowieniex.ZamowienieX

class DiscordBotManager(private val plugin: ZamowienieX) {

    private var jda: JDA? = null
    private var chatChannel: TextChannel? = null

    fun start() {
        val token = plugin.config.getString("discord.token")
        val channelId = plugin.config.getString("discord.channel-id")

        if (token.isNullOrEmpty() || token == "YOUR_TOKEN") {
            plugin.logger.warning("Your bot token is not set in config.yml!")
            return
        }

        if (channelId.isNullOrEmpty()) {
            plugin.logger.warning("Discord channel ID is not set in config.yml!")
            return
        }

        try {
            jda = JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(DiscordListener(plugin))
                .build()
                .awaitReady()

            chatChannel = jda?.getTextChannelById(channelId)

            if (chatChannel == null) {
                plugin.logger.warning("Channel ID not found: $channelId. Check config.yml.")
            } else {
                plugin.logger.info("Connected to Discord -> #${chatChannel?.name}.")
            }

        } catch (e: Exception) {
            plugin.logger.severe("Failed to connect to discord: ${e.message}")
        }
    }

    fun stop() {
        jda?.shutdown()
        plugin.logger.info("The bot has been disabled.")
    }

    fun sendMessageToDiscord(player: Player, message: String) {
        if (chatChannel == null) return

        val format = plugin.langManager.getMessage("discord", "minecraft-to-discord")
        val content = format
            .replace("{player}", player.name, ignoreCase = false)
            .replace("{message}", message, ignoreCase = false)
        chatChannel?.sendMessage(content)?.queue()
    }
}
