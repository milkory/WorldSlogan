package com.milkory.worldslogan.common;

import com.milkory.worldslogan.WorldSlogan;

/**
 * @author Milkory
 */
public class Logger {

    private static final WorldSlogan plugin = WorldSlogan.getInstance();

    public static void info(String msg) {
        plugin.getLogger().info(msg);
    }

    public static void warning(String msg) {
        plugin.getLogger().warning(msg);
    }

    public static void fine(String msg) {
        plugin.getLogger().fine(msg);
    }


}
