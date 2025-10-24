package pl.syntaxdevteam.zamowieniex

import org.bukkit.plugin.java.JavaPlugin
import pl.syntaxdevteam.zamowieniex.commands.CommandManager
import pl.syntaxdevteam.zamowieniex.discord.DiscordBotManager
import pl.syntaxdevteam.zamowieniex.lang.LangManager
import pl.syntaxdevteam.zamowieniex.listeners.MinecraftChatListener

class ZamowienieX : JavaPlugin() {

    lateinit var langManager: LangManager
        private set

    var discordBotManager: DiscordBotManager? = null

    override fun onEnable() {
        instance = this
        saveDefaultConfig()


        CommandManager(this).registerCommands()


        langManager = LangManager(this)
        langManager.load()


        discordBotManager = DiscordBotManager(this)
        discordBotManager?.start()
        server.pluginManager.registerEvents(MinecraftChatListener(this), this)

        logger.info("ZamowienieX wlaczony")
    }

    override fun onDisable() {
        discordBotManager?.stop()
        logger.info("ZamowienieX wylaczony")
    }

    companion object {
        lateinit var instance: ZamowienieX
        private set
    }
}
