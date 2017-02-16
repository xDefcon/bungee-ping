package com.xdefcon.bungeeping;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;


public class Main extends Plugin {
    public String message;

    public void onEnable() {
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new PingCommand(this));
        bungeeConfig();
    }

    private void bungeeConfig() {
        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdir();
            }
            File config = new File(getDataFolder().getPath(), "config.yml");
            if (!config.exists()) {
                config.createNewFile();
            }
            Configuration conf = ConfigurationProvider.getProvider(YamlConfiguration.class).load(config);
            if (conf.get("Config.message") == null) {
                conf.set("Config.message", "&a[Ping] &7Your ping is: &a%ping%ms");
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(conf, config);
            }
            this.message = ChatColor.translateAlternateColorCodes('&', conf.getString("Config.message"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}