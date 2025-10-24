package pl.syntaxdevteam.zamowieniex

import org.bukkit.plugin.java.JavaPlugin
import pl.syntaxdevteam.zamowieniex.commands.CommandManager

class ZamowienieX : JavaPlugin() {

    override fun onEnable() {
        CommandManager(this).registerCommands()
        // Plugin startup logic
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
