package io.ncbpfluffybear.supserv.objects;

import io.ncbpfluffybear.supserv.SupServPlugin;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class NonInteractableItem extends SlimefunItem implements Listener {

    public NonInteractableItem(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        Bukkit.getPluginManager().registerEvents(this, SupServPlugin.getInstance());
    }

    public NonInteractableItem(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, ItemStack recipeOutput) {
        super(category, item, recipeType, recipe, recipeOutput);

        Bukkit.getPluginManager().registerEvents(this, SupServPlugin.getInstance());
    }

    @EventHandler
    private void onNonInteractableUse(PlayerInteractEvent e) {
        if (SlimefunItem.getByItem(e.getItem()) instanceof  NonInteractableItem) {
            e.setCancelled(true);
        }
    }
}
