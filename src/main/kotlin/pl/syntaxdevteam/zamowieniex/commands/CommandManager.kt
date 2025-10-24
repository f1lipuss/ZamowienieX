package pl.syntaxdevteam.zamowieniex.commands

import pl.syntaxdevteam.zamowieniex.ZamowienieX

class CommandManager(private val plugin: ZamowienieX) {

    fun registerCommands() {
        plugin.getCommand("zamowienie")?.setExecutor(ZamowienieXCommand(plugin))
    }
}