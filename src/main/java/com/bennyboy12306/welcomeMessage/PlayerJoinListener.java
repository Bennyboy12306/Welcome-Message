package com.bennyboy12306.welcomeMessage;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * This class handles the player join event and sends a message when a player joins
 * @author Bennyboy12306
 */

public class PlayerJoinListener implements Listener
{
    private final WelcomeMessage plugin;

    /**
     * Constructor
     * @param plugin a reference to the main plugin class
     */
    public PlayerJoinListener(WelcomeMessage plugin)
    {
        this.plugin = plugin;
    }

    /**
     * This method gets the player that triggered the event and sends the welcome message to them
     * @param event the join event that triggered this method to be called
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();

        // Load welcome message from config file
        FileConfiguration config = plugin.getConfig();
        String message = config.getString("welcome-message");

        // Handle Player Placeholder
        if (message != null)
        {
            message = message.replace("%player%", player.getName());
        }

        player.sendMessage(message);
    }
}
