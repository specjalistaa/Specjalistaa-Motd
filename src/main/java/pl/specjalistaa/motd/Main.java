package pl.specjalistaa.motd;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    public Main() {}

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
        getLogger().info(ChatColor.GREEN + "Pomyślnie załadowano plugin!");
    }

    @EventHandler
    public void onRefreshList(ServerListPingEvent event) {
        String title = getConfig().getString("motd-title");
        event.setMotd(ChatColor.translateAlternateColorCodes('&', title));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (label.equalsIgnoreCase("smotd")) {
            if (!sender.hasPermission("specjalistaa.motd.reload")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cNie posiadasz &cuprawnień &4specjalistaa.motd.reload"));
                return true;
            }
            if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
                reloadConfig();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPomyślnie &2przeładowano&a konfigurację!"));
                return true;
            }
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Poprawne użycie:&f /smotd reload"));
            return true;
        }
        return false;
    }

    @Override
    public void onDisable() {
        getLogger().info(ChatColor.RED + "Pomyślnie wyłączono plugin!");
    }
}
