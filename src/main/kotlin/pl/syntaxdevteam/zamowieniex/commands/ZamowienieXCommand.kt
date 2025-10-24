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
        if (args.isEmpty()) {
            sender.sendMessage(ChatUtil.colorize("\$prefix <#D070F9>Type <white>/zamowieniex help</white> <#D070F9>to see available commands."))
            return true
        }

        when (args[0].lowercase())
        {
            "help" -> sendHelpDesk(sender)
            "reload" -> handleReload(sender)
            else -> sender.sendMessage(ChatUtil.colorize("\$prefix <#D070F9>Unknown command. Type <white>/zamowieniex help</white>"))
        }

        return true
    }

    private fun sendHelpDesk(sender: CommandSender) {
        if (sender is Player) {
            if (!PermissionChecker.check(sender, PermissionChecker.Permissions.CMD_HELP))
                return
        }
        sender.sendMessage(ChatUtil.colorize("\n              <bold><gradient:#E040FB:#8E24AA>[ZamowienieX Help]</gradient></bold>"))
        sender.sendMessage(ChatUtil.colorize("<gray>"))
        sender.sendMessage(ChatUtil.colorize("<white>/uex help</white> <dark_gray>- <#D070F9>Shows this help message."))
        sender.sendMessage(ChatUtil.colorize("<white>/uex reload</white> <dark_gray>- <#D070F9>Reloads the plugin configuration."))
    }

    private fun handleReload(sender: CommandSender) {
        if (sender is Player && !PermissionChecker.check(sender, PermissionChecker.Permissions.CMD_RELOAD))
            return
        plugin.reloadConfig()
        sender.sendMessage(ChatUtil.colorize("\$prefix <#D070F9>Configuration reloaded!"))
    }
}
