package com.bennyboy12306.welcomemessage;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * This class is the main entry point for the mod.
 * @author Bennyboy12306
 */

public class WelcomeMessage implements ModInitializer {
    private static final String DEFAULT_MESSAGE = "Welcome to the server, %player%!";
    private String welcomeMessage = DEFAULT_MESSAGE;

    /**
     * This method is called when the mod is initialized.
     */
    @Override
    public void onInitialize() {
        System.out.println("Welcome Message Mod Initialized");

        // Load the config file
        loadConfig();

        // Register the event for player joining to send the welcome message
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayerEntity player = handler.getPlayer();
            sendWelcomeMessage(player);
        });
    }

    /**
     * This method loads welcome message from the config file or creates the default config file if none exists.
     */
    private void loadConfig() {
        // Define the path to the config file
        Path configFilePath = Paths.get("config", "welcome-message", "config.json");

        // Check if the config file exists
        if (Files.exists(configFilePath)) {
            try {
                // Read the existing config file
                String jsonContent = new String(Files.readAllBytes(configFilePath), StandardCharsets.UTF_8);
                Gson gson = new Gson();
                JsonObject config = gson.fromJson(jsonContent, JsonObject.class);

                // Check if the config is valid
                if (config != null && config.has("welcomeMessage")) {
                    welcomeMessage = config.get("welcomeMessage").getAsString();
                } else {
                    System.out.println("Invalid config.json or missing 'welcomeMessage' field.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (com.google.gson.JsonSyntaxException e) {
                System.out.println("Error parsing the config file. It might be malformed.");
                e.printStackTrace();
            }
        } else {
            // If the config file doesn't exist, create it with default data
            System.out.println("Config file not found. Creating default config.");

            // Set the default welcome message
            welcomeMessage = "&e Welcome to the server, %player%!";

            // Create a new JsonObject with default data
            JsonObject defaultConfig = new JsonObject();
            defaultConfig.addProperty("welcomeMessage", welcomeMessage);

            // Create the necessary directories and the config file
            try {
                Files.createDirectories(configFilePath.getParent()); // Ensure the parent directories exist
                Files.write(configFilePath, defaultConfig.toString().getBytes(StandardCharsets.UTF_8)); // Write the default data
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Getter for welcomeMessage
    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    /**
     * This method sends the welcome message to the player.
     * @param player the player to send the message to.
     */
    private void sendWelcomeMessage(ServerPlayerEntity player) {
        String message = welcomeMessage.replace("%player%", player.getName().getString());
        // Send the message with color support (parse color codes)
        message = message.replace("&", "§");
        Text textMessage = Text.literal(message);
        player.sendMessage(textMessage, false);
    }
}