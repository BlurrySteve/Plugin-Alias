package me.blurrysteve.com;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class CommandAliases extends JavaPlugin {

    private Map<String, String> commandAliases = new HashMap<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();

        loadAliases();

        getCommand("cmdalias").setExecutor(new CommandAliasesCommand(this));

        getServer().getPluginManager().registerEvents(new CommandListener(this), this);

        getLogger().info("CommandAliases plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("CommandAliases plugin has been disabled!");
    }

    public void loadAliases() {
        commandAliases.clear();

        FileConfiguration config = getConfig();

        if (config.contains("aliases")) {
            for (String originalCommand : config.getConfigurationSection("aliases").getKeys(false)) {
                String targetCommand = config.getString("aliases." + originalCommand);

                commandAliases.put(originalCommand.toLowerCase(), targetCommand);

                getLogger().info("Loaded alias: /" + originalCommand + " -> /" + targetCommand);
            }
        }
    }

    public Map<String, String> getCommandAliases() {
        return commandAliases;
    }
}
