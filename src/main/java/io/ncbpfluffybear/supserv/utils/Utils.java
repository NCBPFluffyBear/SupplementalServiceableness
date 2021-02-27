package io.ncbpfluffybear.supserv.utils;

import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.ncbpfluffybear.supserv.SupServItems;
import me.mrCookieSlime.Slimefun.cscorelib2.chat.ChatColors;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Utils {

    public static void send(Player p, String message) {
        p.sendMessage(ChatColor.GREEN + "[SupplementalServiceableness] " + ChatColors.color(message));
    }

    public static ItemStack[] build3x3Recipe(ItemStack item) {
        return new ItemStack[] {
            item, item, item, item, item, item, item, item, item
        };
    }

}
