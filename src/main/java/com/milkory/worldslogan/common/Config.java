package com.milkory.worldslogan.common;

import com.milkory.worldslogan.WorldSlogan;
import lombok.Getter;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * @author Milkory
 */
public class Config {

    private static final WorldSlogan plugin = WorldSlogan.getInstance();

    @Getter private static Configuration config;

    public static void load() {
        config = loadConfig("config.yml");
    }

    public static YamlConfiguration loadConfig(String path) {
        return YamlConfiguration.loadConfiguration(plugin.saveResource(path));
    }

}
