package com.xdefcon.bungeeping;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;


public class PingCommand extends Command {
    private Main plugin;

    public PingCommand(Main plugin) {
        super("ping");
        this.plugin = plugin;
    }

    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer pl = (ProxiedPlayer) sender;
            int pingInt = pl.getPing();
            String pings = "" + pingInt;
            this.plugin.message = this.plugin.message.replaceAll("%ping%", pings);
            pl.sendMessage(this.plugin.message);
        }
    }
}