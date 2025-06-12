package me.blurrysteve.com;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandAliasesCommand implements CommandExecutor {

    private final CommandAliases plugin;

    public CommandAliasesCommand(CommandAliases plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("commandaliases.admin")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
            return true;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfig();

            plugin.loadAliases();

            sender.sendMessage(ChatColor.GREEN + "CommandAliases configuration has been reloaded!");
            return true;
        }

        sender.sendMessage(ChatColor.YELLOW + "Usage: /cmdalias reload");
        return true;
    }
}
