package org.ezhik.authtgem.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.ezhik.authtgem.AuthTGEM;

public class BlockCommandEvent implements Listener {
    @EventHandler
    public void onCommmand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if(MuterEvent.isMute(player)) {
           if(!(event.getMessage().startsWith("/code"))) {
               event.setCancelled(true);
               player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l[&b&lMT&f&l] &c&lСначала привяжите аккаунт к телеграмму"));
           }
        }
    }
}
