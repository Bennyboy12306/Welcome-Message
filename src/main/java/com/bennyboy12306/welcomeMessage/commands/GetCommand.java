package com.bennyboy12306.welcomeMessage.commands;

import com.bennyboy12306.welcomeMessage.WelcomeMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

/**
 * This class gets the current welcome message from the configuration
 * @author Bennyboy12306
 */
public class GetCommand implements CommandExecutor
{

    private final WelcomeMessage plugin;

    //Constructor pass in the main plugin instance
    public GetCommand(WelcomeMessage plugin)
    {
        this.plugin = plugin;
    }

    /**
     * This method handles the command
     * @param sender the thing that sent the command
     * @param command the command that was sent
     * @param label the command label
     * @param args the arguments that were passed for this command
     * @return if the command was successfully handled (returning false automatically sends the usage message, so do not return false if you already provide an error message with usage in it)
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.hasPermission("welcome-message.base"))
        {
            FileConfiguration config = plugin.getConfig();
            String welcomeMessage = config.getString("welcome-message");
            sender.sendMessage("Current welcome message: " + welcomeMessage);
        }
        else
        {
            sender.sendMessage( "You don't have permission to execute this command.");
        }
        return true;

    }
}
