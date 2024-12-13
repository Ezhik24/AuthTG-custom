package org.ezhik.authtgem.events;

import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.ezhik.authtgem.AuthTGEM;
import org.ezhik.authtgem.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class OnJoinEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        YamlConfiguration userconfig = new YamlConfiguration();
        File file = new File("plugins/Minetelegram/users/" + p.getUniqueId() + ".yml");
        FreezerEvent.freezeplayer(p.getName());
        if (!file.exists()) {
            MuterEvent.mute(p.getName(), ChatColor.translateAlternateColorCodes('&', "&a&lПривяжите аккаунт к телеграмму"));
            p.sendTitle(ChatColor.translateAlternateColorCodes('&', "&f&lПривяжите аккаунт"), "к телеграмму", 20, 10000000, 0);
            try {
                userconfig.load(file);
            } catch (IOException e) {
                System.out.println("Error loading config file: " + e);
            } catch (InvalidConfigurationException e) {
                System.out.println("Error parsing config file: " + e);
            }
            userconfig.set("playername", p.getName());
            userconfig.set("ChatID", null);
            try {
                userconfig.save(file);
            } catch (IOException e) {
                System.out.println("Error saving config file: " + e);
            }
        }
        else {
            try {
                userconfig.load(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InvalidConfigurationException e) {
                throw new RuntimeException(e);
            }
            if (userconfig.getBoolean("active")) {
                MuterEvent.mute(p.getName(), ChatColor.translateAlternateColorCodes('&', "&a&lПотвердите вход через телеграмм"));
                p.sendTitle(ChatColor.translateAlternateColorCodes('&', "&f&lПотвердите вход"), "через телеграмм", 20, 10000000, 0);
                User user = User.getUser(p.getUniqueId());
                user.sendLoginAccepted("На ваш аккаунт зашли. Это были вы?");
            } else {
                MuterEvent.mute(p.getName(), ChatColor.translateAlternateColorCodes('&', "&a&lПривяжите аккаунт к телеграмму"));
                p.sendTitle(ChatColor.translateAlternateColorCodes('&', "&f&lПривяжите аккаунт"), "к телеграмму", 20, 10000000, 0);
                try {
                    userconfig.load(file);
                } catch (IOException e) {
                    System.out.println("Error loading config file: " + e);
                } catch (InvalidConfigurationException e) {
                    System.out.println("Error parsing config file: " + e);
                }
                userconfig.set("playername", p.getName());
                try {
                    userconfig.save(file);
                } catch (IOException e) {
                    System.out.println("Error saving config file: " + e);
                }

            }
        }
    }
}