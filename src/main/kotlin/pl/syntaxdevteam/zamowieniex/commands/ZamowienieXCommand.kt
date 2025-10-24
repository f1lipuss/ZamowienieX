package pl.syntaxdevteam.zamowieniex.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import pl.syntaxdevteam.zamowieniex.ZamowienieX
import pl.syntaxdevteam.zamowieniex.permissions.PermissionChecker
import pl.syntaxdevteam.zamowieniex.util.ChatUtil

class ZamowienieXCommand(private val plugin: ZamowienieX) : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val langManager = plugin.langManager

        if (args.isEmpty()) {
            sender.sendMessage(ChatUtil.colorize(langManager.getMessage("command", "usage")))
            return true
        }

        when (args[0].lowercase())
        {
            "help" -> sendHelpDesk(sender)
            "reload" -> handleReload(sender)
            else -> sender.sendMessage(ChatUtil.colorize(langManager.getMessage("command", "unknown")))
        }

        return true
    }

    private fun sendHelpDesk(sender: CommandSender) {
        if (sender is Player) {
            if (!PermissionChecker.check(sender, PermissionChecker.Permissions.CMD_HELP))
                return
        }
        val helpMessages = plugin.langManager.getMessageList("command", "help")
        for (message in helpMessages) {
            sender.sendMessage(ChatUtil.colorize(message))
        }
    }

    private fun handleReload(sender: CommandSender) {
        if (sender is Player && !PermissionChecker.check(sender, PermissionChecker.Permissions.CMD_RELOAD))
            return
        plugin.reloadConfig()
        plugin.langManager.reload()
        sender.sendMessage(ChatUtil.colorize(plugin.langManager.getMessage("command", "reload", "success")))
    }
}
