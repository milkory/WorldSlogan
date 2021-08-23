package com.milkory.worldslogan.entity;

import com.google.common.base.Strings;
import com.milkory.worldslogan.hook.PlaceholderAPIHook;
import lombok.Data;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @author Milkory
 */
@Data
public class Display {

    @NotNull private final String title;
    @NotNull private final String subtitle;
    @NotNull private final String chat;

    @NotNull private final TitleTime time;

    public void sendTo(Player player) {
        if (this == EMPTY) return;
        if (!(title.isEmpty() && subtitle.isEmpty())) {
            player.sendTitle(format(player, title), format(player, subtitle), time.fadeIn, time.stay, time.fadeOut);
        }
        if (!chat.isEmpty()) {
            player.sendMessage(format(player, chat));
        }
    }

    private String format(Player player, String text) {
        return ChatColor.translateAlternateColorCodes('&',
                PlaceholderAPIHook.getHook().setPlaceholders(player, text)
                        .replace("\\n", "\n"))
                        .replace("{player}", player.getDisplayName());
    }

    public static Display EMPTY = new Display("", "", "", TitleTime.DEFAULT);

    public static Display fromConfig(ConfigurationSection section) {
        ConfigurationSection time = section.getConfigurationSection("time");
        return new Display(
                Strings.nullToEmpty(section.getString("title")),
                Strings.nullToEmpty(section.getString("subtitle")),
                Strings.nullToEmpty(section.getString("chat")),
                time != null ? TitleTime.fromConfig(time) : TitleTime.DEFAULT
        );
    }

    @Data
    public static class TitleTime {

        private final int fadeIn;
        private final int stay;
        private final int fadeOut;

        public static TitleTime DEFAULT = new TitleTime(10, 70, 20);

        public static TitleTime fromConfig(ConfigurationSection section) {
            return new TitleTime(
                    section.contains("in") ? section.getInt("in") : DEFAULT.getFadeIn(),
                    section.contains("stay") ? section.getInt("stay") : DEFAULT.getStay(),
                    section.contains("out") ? section.getInt("out") : DEFAULT.getFadeOut()
            );
        }

    }

}
