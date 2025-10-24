
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

        if (token.isNullOrEmpty() || token == "TWOJ_TOKEN_BOTA") {
            plugin.logger.warning("Token bota Discord nie jest ustawiony w config.yml!")
            return
        }

        if (channelId.isNullOrEmpty()) {
            plugin.logger.warning("ID kanału Discord nie jest ustawione w config.yml! Most czatu nie zostanie uruchomiony.")
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
                plugin.logger.warning("Nie znaleziono kanału o ID: $channelId. Sprawdź config.yml.")
            } else {
                plugin.logger.info("Połączono z Discordem! Most czatu jest aktywny na kanale #${chatChannel?.name}.")
            }

        } catch (e: Exception) {
            plugin.logger.severe("Nie udało się połączyć z discordem: ${e.message}")
        }
    }

    fun stop() {
        jda?.shutdown()
        plugin.logger.info("Bot został wylaczony z discordem")
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
