package com.xdefcon.bungeeping.commands;

import com.xdefcon.bungeeping.BungeePing;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;


public class ReloadCommand extends Command {
    private BungeePing plugin;

    public ReloadCommand(BungeePing plugin) {
        super("pingreload");
        this.plugin = plugin;
    }

    public void execute(CommandSender sender, String[] args) {
        if (!sender.hasPermission("bungeeping.reload")) {
            String noPerm = plugin.getConfig().getString("permission-system.no-perm-message");
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', noPerm));
            return;
        }
        plugin.reload();
    }
}
