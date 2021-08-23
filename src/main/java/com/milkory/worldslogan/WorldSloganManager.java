package com.milkory.worldslogan;

import com.milkory.worldslogan.common.Logger;
import com.milkory.worldslogan.entity.Display;
import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Milkory
 */
public class WorldSloganManager {

    @Getter public static final WorldSloganManager instance = new WorldSloganManager();

    @Getter public final Map<String, Display> worlds = new HashMap<>();
    @Getter public final Map<String, Display> commands = new HashMap<>();

    public Display getWorldDisplay(String world) {
        return getDisplayOrDefault(world, worlds);
    }

    public Display getCommandDisplay(String command) {
        return getDisplayOrDefault(command, commands);
    }

    private Display getDisplayOrDefault(String key, Map<String, Display> map) {
        return map.getOrDefault(key, Display.EMPTY);
    }

    public void loadFromConfig(ConfigurationSection config) {
        Logger.fine("Loading world slogans...");
        loadKeyToDisplay(config.getConfigurationSection("world-slogan"), worlds);
        Logger.fine("Loading custom commands...");
        loadKeyToDisplay(config.getConfigurationSection("custom-command"), commands);
        Logger.info("WorldSlogan loaded successfully!");
    }

    private void loadKeyToDisplay(ConfigurationSection config, Map<String, Display> map) {
        map.clear();
        if (config == null) return;
        for (String key : config.getKeys(false)) {
            ConfigurationSection section = config.getConfigurationSection(key);
            if (section == null) continue;
            map.put(key, Display.fromConfig(section));
        }
    }

}
