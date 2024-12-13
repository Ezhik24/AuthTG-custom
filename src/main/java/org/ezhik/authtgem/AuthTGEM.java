package org.ezhik.authtgem;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.ezhik.authtgem.events.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.telegram.telegrambots.meta.TelegramBotsApi;

public final class AuthTGEM extends JavaPlugin {
    public static BotTelegram bot;

    @Override
    public void onEnable() {
        System.out.println("[AuthTG] Пожалуйста,подпишитесь на мой телеграмм канал https://t.me/ezhichek11");
        System.out.println("[AuthTG] Please,subcribe for my telegram channel https://t.me/ezhichek11");
        System.out.println("[AuthTG] Плагин включен!");
        System.out.println("[AuthTG] Plugin enabled!");
        Bukkit.getServer().getPluginManager().registerEvents(new FreezerEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new OnJoinEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new MuterEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockCommandEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockDropItemEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockBreakEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockPlaceEvent(),this);
        Handler handler = new Handler();
        handler.runTaskTimer(this,0,1);
        getCommand("code").setExecutor(new CodeCMD());
        bot = new BotTelegram();
        if (bot.getBotToken() == "changeme" && bot.getBotUsername() == "changeme") {
            System.out.println("Please set your bot token and username in config.yml");
            System.out.println("Пожалуйста, укажите ваш токен и имя в config.yml");
        } else {
            TelegramBotsApi botsApi = null;
            try {
                botsApi = new TelegramBotsApi(DefaultBotSession.class);
                botsApi.registerBot(bot);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }

        }

    }

    @Override
    public void onDisable() {
        System.out.println("[AuthTG] Пожалуйста,подпишитесь на мой телеграмм канал https://t.me/ezhichek11");
        System.out.println("[AuthTG] Please,subcribe for my telegram channel https://t.me/ezhichek11");
        System.out.println("[AuthTG] Плагин выключен!");
        System.out.println("[AuthTG] Plugin disabled!");
    }


}
