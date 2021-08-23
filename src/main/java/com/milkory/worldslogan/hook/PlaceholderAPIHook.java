package com.milkory.worldslogan.hook;

import com.milkory.worldslogan.common.Logger;
import lombok.Getter;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderAPIPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

/**
 * @author Milkory
 */
public class PlaceholderAPIHook {

    @Getter private static final PlaceholderAPIHook hook = new PlaceholderAPIHook();

    @Getter @Nullable private PlaceholderAPIPlugin plugin;

    public void hook() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("PlaceholderAPI");
        if (plugin == null) return;
        this.plugin = (PlaceholderAPIPlugin) plugin;
        Logger.info("Hooked into PlaceholderAPI.");
    }

    public String setPlaceholders(Player player, String text) {
        if (plugin != null) {
            return PlaceholderAPI.setPlaceholders(player, text);
        } else return text;
    }

}
