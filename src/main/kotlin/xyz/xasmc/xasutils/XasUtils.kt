package xyz.xasmc.xasutils

import org.bukkit.plugin.java.JavaPlugin
import xyz.xasmc.xasutils.config.ConfigManager
import xyz.xasmc.xasutils.config.PluginConfig
import xyz.xasmc.xasutils.listener.DamageListener
import xyz.xasmc.xasutils.listener.ExplodeListener
import java.io.File

class XasUtils : JavaPlugin() {
    lateinit var config: PluginConfig

    override fun onEnable() {
        instance = this
        this.config = ConfigManager.loadConfig()
        this.server.pluginManager.registerEvents(DamageListener(), this)
        this.server.pluginManager.registerEvents(ExplodeListener(), this)
    }

    override fun onDisable() {
    }

    companion object {
        lateinit var instance: XasUtils

        fun getConfig(): PluginConfig {
            return instance.config
        }

        fun getDataFolder(): File {
            return instance.dataFolder
        }
    }
}
