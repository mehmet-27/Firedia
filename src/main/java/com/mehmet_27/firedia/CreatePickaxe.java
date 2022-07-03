package com.mehmet_27.firedia;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Locale;

public class CreatePickaxe {

    public ItemStack create(PickaxeType type) {
        ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta meta = pickaxe.getItemMeta();
        meta.setDisplayName(Utils.getString("pickaxes." + type.name().toLowerCase(Locale.ENGLISH) + ".name"));
        meta.setLore(Utils.color(Firedia.getInstance().getConfig().getStringList("pickaxes." + type.name().toLowerCase(Locale.ENGLISH) + ".lore")));
        pickaxe.setItemMeta(meta);
        return pickaxe;
    }
}
