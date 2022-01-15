package io.ncbpfluffybear.supserv.utils;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.ncbpfluffybear.supserv.items.WateringCan;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Events implements Listener {

    public Events() {}

    @EventHandler(ignoreCancelled = true)
    public void onWateringCanSplash(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInMainHand();
        SlimefunItem wateringCan = SlimefunItem.getByItem(item);

        if (wateringCan == null || !wateringCan.getId().startsWith("WATERING_CAN")) {
            return;
        }

        e.setCancelled(true);
        Entity target = e.getRightClicked();
        if (target instanceof Player && WateringCan.updateUses((WateringCan) wateringCan, p, item, 3)) {
            Utils.send(p, "&bSplash!");
            Utils.send((Player) target, "&bYou were splashed by " + p.getDisplayName() + "!");
            ((Player) target).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 1));
        }
    }

}
