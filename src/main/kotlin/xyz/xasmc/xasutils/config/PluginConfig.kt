package xyz.xasmc.xasutils.config

data class PluginConfig(
    val enable: Boolean = true,
    val creeperExplosionProtection: Boolean = true,
    val snowballIgniteEntity: Boolean = true,
    val snowballHasDamage: Boolean = true,
)
