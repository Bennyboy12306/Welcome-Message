package com.bennyboy12306.welcomeMessage;

import com.bennyboy12306.welcomeMessage.commands.GetCommand;
import com.bennyboy12306.welcomeMessage.commands.ReloadCommand;
import com.bennyboy12306.welcomeMessage.commands.SetCommand;
import org.bukkit.Bukkit;
import org.bstats.bukkit.Metrics;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * This class is the main entry point for the plugin.
 * @author Bennyboy12306
 */

public final class WelcomeMessage extends JavaPlugin
{

    /**
     * This method handles enabling the plugin.
     */
    @Override
    public void onEnable()
    {
        // Plugin startup logic

        //Initialise bStats
        int pluginId = 23578; //If you have modified this plugin you may want to change this ID to your own
        Metrics metrics = new Metrics(this, pluginId);

        // Save default config if no config exists
        saveDefaultConfig();

        // Register event listener
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(this), this);

        // Register the commands
        getCommand("welcome-message-get").setExecutor(new GetCommand(this));
        getCommand("welcome-message-set").setExecutor(new SetCommand(this));
        getCommand("welcome-message-reload").setExecutor(new ReloadCommand(this));

        //Log plugin start up
        getLogger().info("The Welcome Message Plugin has been enabled");

    }

    /**
     * This method handles disabling the plugin.
     */
    @Override
    public void onDisable()
    {
        // Plugin shutdown logic

        //Log plugin shut down
        getLogger().info("The Welcome Message Plugin has been disabled");
    }
}
