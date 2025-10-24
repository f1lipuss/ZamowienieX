package pl.syntaxdevteam.zamowieniex.permissions

import org.bukkit.command.CommandSender
import pl.syntaxdevteam.zamowieniex.util.ChatUtil

object PermissionChecker {

    object Permissions {
        const val OPEN_GUI1 = "zamowieniex.open_gui1"
        const val CMD_RELOAD = "zamowieniex.cmd.reload"
        const val CMD_HELP = "zamowieniex.cmd.help"
    }

    fun check(sender: CommandSender, permission: String): Boolean {
        if (has(sender, permission)) {
            return true
        }
        sendNoPermissionMessage(sender, permission)
        return false
    }

    fun has(sender: CommandSender, permission: String): Boolean {
        return sender.hasPermission(permission) || sender.hasPermission("zamowieniex.*")
    }

    private fun sendNoPermissionMessage(sender: CommandSender, permission: String) {
        sender.sendMessage(ChatUtil.colorize("\$prefix <red>You don't have permission for this command!"))
    }
}