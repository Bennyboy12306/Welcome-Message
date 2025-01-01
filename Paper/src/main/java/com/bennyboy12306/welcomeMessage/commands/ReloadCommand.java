package com.bennyboy12306.welcomeMessage.commands;

import com.bennyboy12306.welcomeMessage.WelcomeMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * This class reloads the plugin's configuration.
 * @author Bennyboy12306
 */
public class ReloadCommand implements CommandExecutor
{

    private final WelcomeMessage plugin;

    /**
     * Constructor.
     * @param plugin a reference to the main plugin class.
     */
    public ReloadCommand(WelcomeMessage plugin)
    {
        this.plugin = plugin;
    }

    /**
     * This method handles the reload command.
     * @param sender the thing that sent the command.
     * @param command the command that was sent.
     * @param label the command label.
     * @param args the arguments that were passed for this command.
     * @return if the command was successfully handled (returning false automatically sends the usage message, so do not return false if you already provide an error message with usage in it).
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.hasPermission("welcome-message.base"))
        {
            plugin.reloadConfig();
            sender.sendMessage("Welcome Message configuration reloaded!");
        }
        else
        {
            sender.sendMessage( "You don't have permission to execute this command.");
        }
        return true;
    }
}
