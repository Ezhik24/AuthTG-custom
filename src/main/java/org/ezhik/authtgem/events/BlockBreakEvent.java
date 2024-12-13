package org.ezhik.authtgem.events;

import javassist.bytecode.analysis.ControlFlow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.net.http.WebSocket;

public class BlockBreakEvent implements Listener {
    @EventHandler
    public void onBlockBreak(BlockPlaceEvent event) {
        if (FreezerEvent.isFreeze(event.getPlayer())) event.setCancelled(true);
    }
}
