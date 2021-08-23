package com.milkory.worldslogan.listener;

import com.milkory.worldslogan.WorldSloganManager;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @author Milkory
 */
public class PlayerListener implements Listener {

    private static final WorldSloganManager manager = WorldSloganManager.getInstance();

    @EventHandler
    public void onChangeWorld(PlayerChangedWorldEvent event) {
        sendWorldMessage(event.getPlayer());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        sendWorldMessage(event.getPlayer());
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("world-slogan.send.command")) return;
        String command = event.getMessage().substring(1);
        if (manager.getCommands().containsKey(command)) {
            manager.getCommandDisplay(command).sendTo(player);
            event.setCancelled(true);
        }
    }

    private void sendWorldMessage(Player player) {
        if (!player.hasPermission("world-slogan.send.world")) return;
        World world = player.getLocation().getWorld();
        if (world == null) return;
        manager.getWorldDisplay(world.getName()).sendTo(player);
    }

}
