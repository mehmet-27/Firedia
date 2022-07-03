package com.mehmet_27.firedia;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Utils {

    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static List<String> color(List<String> list) {
        List<String> colored = new ArrayList<>();
        for (String str : list) {
            colored.add(Utils.color(str));
        }
        return colored;
    }

    public static String getString(String path) {
        return color(Firedia.getInstance().getConfig().getString(path, path));
    }

    public static boolean isSpecialPickaxe(ItemStack item, PickaxeType pickaxeType) {
        FileConfiguration config = Firedia.getInstance().getConfig();
        ItemMeta meta = item.getItemMeta();
        String type = pickaxeType.name().toLowerCase(Locale.ENGLISH);
        String namePath = "pickaxes." + type + ".name";
        String lorePath = "pickaxes." + type + ".lore";
        return meta != null &&
                meta.hasDisplayName() &&
                meta.getDisplayName().equals(Utils.color(config.getString(namePath))) &&
                meta.hasLore() &&
                Objects.equals(meta.getLore(), Utils.color(config.getStringList(lorePath)));
    }

}
