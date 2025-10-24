package pl.syntaxdevteam.zamowieniex.lang

import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import pl.syntaxdevteam.zamowieniex.ZamowienieX
import java.io.File

class LangManager(private val plugin: ZamowienieX) {

    private lateinit var langConfig: FileConfiguration

    fun load() {
        loadLangFile()
    }

    init {
        loadLangFile()
    }

    private fun loadLangFile() {
        val langFileName = "pl.yml"
        val langFile = File(plugin.dataFolder, "lang/$langFileName")

        if (!langFile.parentFile.exists()) {
            langFile.parentFile.mkdirs()
        }

        if (!langFile.exists()) {
            plugin.saveResource("lang/$langFileName", false)
        }

        langConfig = YamlConfiguration.loadConfiguration(langFile)
    }

    fun getMessage(vararg keys: String): String {
        val key = keys.joinToString(".")
        return langConfig.getString(key) ?: "<red>Missing message for key: $key"
    }

    fun getMessageList(vararg keys: String): List<String> {
        val key = keys.joinToString(".")
        return langConfig.getStringList(key)
    }

    fun reload() {
        loadLangFile()
    }
}