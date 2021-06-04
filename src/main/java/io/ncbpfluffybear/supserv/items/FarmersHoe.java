package io.ncbpfluffybear.supserv.items;

import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.protection.ProtectableAction;
import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

public class FarmersHoe  extends SimpleSlimefunItem<ItemUseHandler> {

    public FarmersHoe(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, ItemStack recipeOutput) {
        super(category, item, recipeType, recipe, recipeOutput);

    }

    List<Material> farmables = Arrays.asList(Material.DIRT, Material.GRASS_BLOCK, Material.COARSE_DIRT);
    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {

            Player p = e.getPlayer();
            ItemStack item = e.getItem();

            if (!isItem(item))
                return;

            if(!e.getClickedBlock().isPresent()) return;    // Check if clicked block is null > return
            Block b = e.getClickedBlock().get();
            if(farmables.contains(b.getType())) return;
            e.cancel();
            if(SlimefunPlugin.getProtectionManager().hasPermission(e.getPlayer(), b,
                    ProtectableAction.BREAK_BLOCK)) // Check if player may 'break' the block to harvest

            if(!(b.getBlockData() instanceof Ageable)) return;  // Check if clicked block is Ageable (and thus a crop), if not return

            Ageable crop = (Ageable) b.getBlockData();
            if(crop.getAge() != crop.getMaximumAge()) return; // if crop is not fully grown return

            // ready to harvest
            for (ItemStack i : b.getDrops()) {
                b.getWorld().dropItemNaturally(b.getLocation(), i);
            }
            crop.setAge(CropState.SEEDED.ordinal());
            b.setBlockData(crop, false);
            item.setDurability((short) (item.getDurability()+1));
        };
    }
}
