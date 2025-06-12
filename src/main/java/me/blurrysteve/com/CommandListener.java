package me.blurrysteve.com;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {

    private final CommandAliases plugin;

    public CommandListener(CommandAliases plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onCommand(PlayerCommandPreprocessEvent event) {
        if (event.isCancelled()) return;

        String message = event.getMessage();

        if (message.length() <= 1) return;

        int spaceIndex = message.indexOf(' ');
        String commandName;
        String args = "";

        if (spaceIndex > 0) {
            commandName = message.substring(1, spaceIndex).toLowerCase();
            args = message.substring(spaceIndex + 1);
        } else {
            commandName = message.substring(1).toLowerCase();
        }

        String targetCommand = plugin.getCommandAliases().get(commandName);
        if (targetCommand != null) {
            event.setCancelled(true);

            Player player = event.getPlayer();

            if (args.isEmpty()) {
                player.performCommand(targetCommand);
            } else {
                player.performCommand(targetCommand + " " + args);
            }
        }
    }
}
