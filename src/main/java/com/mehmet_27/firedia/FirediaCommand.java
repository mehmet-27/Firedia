package com.mehmet_27.firedia;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FirediaCommand implements CommandExecutor, TabCompleter {

    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        if (str.equalsIgnoreCase("firedia")) {
            if (!(sender instanceof Player)) {
                Player player;
                if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
                    Firedia.getInstance().reloadConfig();
                    sender.sendMessage(Utils.getString("messages.reload"));
                }

                if (args.length > 0 && args[0].equalsIgnoreCase("give")) {
                    if (args.length == 3) {
                        player = Bukkit.getPlayerExact(args[1]);
                        if (player == null || !player.isOnline()) {
                            sender.sendMessage(Utils.getString("messages.notFound"));
                            return false;
                        }
                        PickaxeType type;
                        try {
                            type = PickaxeType.valueOf(args[2].toUpperCase(Locale.ENGLISH));
                        } catch (IllegalArgumentException e) {
                            sender.sendMessage(Utils.getString("messages.wrongType"));
                            return false;
                        }
                        if (player.getInventory().firstEmpty() == -1) {
                            Location loc = player.getLocation();
                            World world = player.getWorld();
                            world.dropItemNaturally(loc, (new CreatePickaxe()).create(type));
                            player.sendMessage(Utils.getString("messages.fullInventory"));
                            sender.sendMessage(Utils.getString("messages.done").replace("%player%", player.getDisplayName()));
                            return true;
                        }

                        player.getInventory().addItem(new CreatePickaxe().create(type));
                        player.sendMessage(Utils.getString("messages.getPickaxe"));
                        sender.sendMessage(Utils.getString("messages.done").replace("%player%", player.getDisplayName()));
                        return true;
                    } else {
                        sender.sendMessage(Utils.getString("messages.wrongUsage"));
                        return false;
                    }

                }
            } else {
                sender.sendMessage(Utils.getString("messages.onlyConsole"));
                return false;
            }
        }
        return false;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) return new ArrayList<>();
        List<String> completes;
        if (args.length == 1) {
            completes = new ArrayList<>();
            completes.add("give");
            completes.add("reload");
            return completes;
        } else if (args.length == 2 && args[0].equalsIgnoreCase("give")) {
            completes = new ArrayList<>();
            Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
            Bukkit.getServer().getOnlinePlayers().toArray(players);

            for (Player player : players) {
                completes.add(player.getName());
            }

            return completes;
        }else if (args.length == 3 && args[0].equalsIgnoreCase("give")) {
            completes = new ArrayList<>();
            for (PickaxeType type : PickaxeType.values()) {
                completes.add(type.name());
            }

            return completes;
        } else {
            return new ArrayList<>();
        }
    }
}
