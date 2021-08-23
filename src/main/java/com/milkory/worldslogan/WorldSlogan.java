package com.milkory.worldslogan;

import com.milkory.worldslogan.common.Config;
import com.milkory.worldslogan.common.Logger;
import com.milkory.worldslogan.hook.PlaceholderAPIHook;
import com.milkory.worldslogan.listener.PlayerListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * @author Milkory
 */
public class WorldSlogan extends JavaPlugin {

    @Getter private static WorldSlogan instance;

    public WorldSlogan() {
        instance = this;
    }

    @Override public void onLoad() {
        Config.load();
        WorldSloganManager.getInstance().loadFromConfig(Config.getConfig());
    }

    @Override public void onEnable() {
        PlaceholderAPIHook.getHook().hook();
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        Logger.info("Could it? Could it? Could single rhymings be count as rhyming? (Enabled)");
    }

    @Override public void onDisable() {
        Logger.info("Why do they want to prove that they are playing the real rap and how hot their hearts are? (Disabled)");
    }

    public InputStreamReader getResourceReader(String file) {
        return new InputStreamReader(Objects.requireNonNull(this.getResource(file)));
    }

    public File saveResource(String path) {
        File file = new File(getDataFolder(), path);
        if (!file.exists()) saveResource(path, false);
        return file;
    }

    @Override public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1 && "reload".equalsIgnoreCase(args[0]) && sender.hasPermission("world-slogan.command.reload")) {
            onLoad();
            sender.sendMessage(ChatColor.GREEN + "Reloaded successfully!");
        } else sender.sendMessage(ChatColor.RED + "Unknown command or lack permission.");
        return true;
    }

}
