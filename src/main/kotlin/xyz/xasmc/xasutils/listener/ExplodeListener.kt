package xyz.xasmc.xasutils.listener

import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityExplodeEvent
import xyz.xasmc.xasutils.XasUtils

class ExplodeListener : Listener {
    @EventHandler
    fun onEntityExplodeEvent(event: EntityExplodeEvent) {
        if (!(XasUtils.getConfig().enable && XasUtils.getConfig().creeperExplosionProtection)) return
        if (event.entity.type != EntityType.CREEPER) return
        event.blockList().clear()
    }

}