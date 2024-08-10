package xyz.xasmc.xasutils.listener

import org.bukkit.entity.EntityType
import org.bukkit.entity.Snowball
import org.bukkit.entity.Snowman
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import xyz.xasmc.xasutils.XasUtils

class DamageListener : Listener {
    @EventHandler
    fun onEntityDamageByEntity(event: EntityDamageByEntityEvent) {
        if (!(XasUtils.getConfig().enable && XasUtils.getConfig().snowballIgniteEntity)) return
        val damager = event.damager
        if (damager.type != EntityType.SNOWBALL) return
        if (damager.fireTicks > 0) {
            event.entity.fireTicks = 100
        }
        val snowball = damager as Snowball
        if (XasUtils.getConfig().snowballHasDamage && snowball.shooter is Snowman) {
            event.damage = 2.0
        }
    }

}