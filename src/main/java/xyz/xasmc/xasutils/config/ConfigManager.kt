package xyz.xasmc.xasutils.config

import org.bukkit.configuration.file.YamlConfiguration
import xyz.xasmc.xasutils.XasUtils
import java.io.File

object ConfigManager {
    fun loadConfig(): PluginConfig {
        val plugin = XasUtils.instance
        val configFile = File(plugin.dataFolder, "config.yml")
        if (!configFile.exists()) plugin.saveResource("config.yml", false)
        val config = YamlConfiguration.loadConfiguration(configFile)
        return PluginConfig(
            enable = config.getBoolean("enable"),
            creeperExplosionProtection = config.getBoolean("creeperExplosionProtection"),
        )
    }
}