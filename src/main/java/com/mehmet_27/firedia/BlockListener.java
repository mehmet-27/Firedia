package com.mehmet_27.firedia;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class BlockListener implements Listener {

    @EventHandler(priority = EventPriority.LOW)
    public void onBreak(BlockBreakEvent event) {
        if (event.isCancelled()) return;

        Player player = event.getPlayer();
        Block block = event.getBlock();
        if (Utils.isSpecialPickaxe(player.getInventory().getItemInMainHand(), PickaxeType.PISIRME)) {
            Material drop;
            switch (block.getType()) {
                case IRON_ORE:
                    drop = Material.IRON_INGOT;
                    break;
                case GOLD_ORE:
                    drop = Material.GOLD_INGOT;
                    break;
                default:
                    drop = Material.AIR;
                    break;
            }
            if (drop.equals(Material.AIR)) return;
            block.breakNaturally(new ItemStack(Material.AIR));
            block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(drop, 1));
        }

        if (Utils.isSpecialPickaxe(player.getInventory().getItemInMainHand(), PickaxeType.SPAWNER)) {
            if (!block.getType().equals(Material.SPAWNER)) return;
            int number = new Random().nextInt(2);
            // 1 veya 0 gelecek (%50 şans)
            if (number == 0) {
                event.setCancelled(true);
                block.setType(Material.AIR);
            } else {
                // silk spawner drobu düşürecektir.
            }
        }

        if (Utils.isSpecialPickaxe(player.getInventory().getItemInMainHand(), PickaxeType.SUPER)) {
            Material drop;
            switch (block.getType()) {
                case IRON_ORE:
                    drop = Material.IRON_INGOT;
                    break;
                case GOLD_ORE:
                    drop = Material.GOLD_INGOT;
                    break;
                case SPAWNER:
                    drop = Material.SPAWNER;
                    break;
                default:
                    drop = Material.AIR;
                    break;
            }
            if (drop.equals(Material.AIR)) return;
            if (drop.equals(Material.SPAWNER)) {
                int number = new Random().nextInt(2);
                // 1 veya 0 gelecek (%50 şans)
                if (number == 0) {
                    event.setCancelled(true);
                    block.setType(Material.AIR);
                } else {
                    // silk spawner drobu düşürecektir.
                }
            } else {
                block.breakNaturally(new ItemStack(Material.AIR));
                block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(drop, 1));
            }
        }
    }
}
