package com.xdefcon.bungeeping;

import com.google.common.io.ByteStreams;
import com.xdefcon.bungeeping.bstats.Metrics;
import com.xdefcon.bungeeping.commands.PingCommand;
import com.xdefcon.bungeeping.commands.ReloadCommand;
import com.xdefcon.bungeeping.listeners.TabCompleteListener;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.*;


public class BungeePing extends Plugin {
    private Configuration config;
    private BungeePing instance;


    public void onEnable() {
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new PingCommand(this));
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new ReloadCommand(this));
        ProxyServer.getInstance().getPluginManager().registerListener(this, new TabCompleteListener(this));
        this.loadConfig();
        new Metrics(this);
    }

    public void reload() {
        ProxyServer.getInstance().getConsole().sendMessage(ChatColor.GREEN + "Reloading the plugin...");
        this.loadConfig();
        ProxyServer.getInstance().getConsole().sendMessage(ChatColor.GREEN + "Plugin reloaded.");
    }

    private void loadConfig() {
        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdir();
            }
            File config = new File(getDataFolder().getPath(), "config.yml");
            if (!config.exists()) {
                try {
                    config.createNewFile();
                    try (InputStream is = getResourceAsStream("config.yml");
                         OutputStream os = new FileOutputStream(config)) {
                        ByteStreams.copy(is, os);
                    }
                } catch (IOException e) {
                    throw new RuntimeException("Unable to create configuration file", e);
                }
            }
            this.config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Configuration getConfig() {
        return config;
    }

    public BungeePing getInstance() {
        return instance;
    }
}
