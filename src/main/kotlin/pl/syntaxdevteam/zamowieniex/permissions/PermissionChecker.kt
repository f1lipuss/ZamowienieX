package pl.syntaxdevteam.zamowieniex.permissions

import org.bukkit.command.CommandSender
import pl.syntaxdevteam.zamowieniex.ZamowienieX
import pl.syntaxdevteam.zamowieniex.util.ChatUtil

object PermissionChecker {

    object Permissions {
        const val CMD_RELOAD = "zamowieniex.cmd.reload"
        const val CMD_HELP = "zamowieniex.cmd.help"
    }

    fun check(sender: CommandSender, permission: String): Boolean {
        if (has(sender, permission)) {
            return true
        }
        sendNoPermissionMessage(sender)
        return false
    }

    fun has(sender: CommandSender, permission: String): Boolean {
        return sender.hasPermission(permission) || sender.hasPermission("zamowieniex.*")
    }

    private fun sendNoPermissionMessage(sender: CommandSender) {
        val noPermissionMessage = ZamowienieX.instance.langManager.getMessage("error", "nopermission")
        sender.sendMessage(ChatUtil.colorize(noPermissionMessage))
    }
}