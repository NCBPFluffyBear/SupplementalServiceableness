package io.ncbpfluffybear.supserv.items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.RayTraceResult;

import javax.annotation.Nonnull;

public class LavaSponge extends SimpleSlimefunItem<ItemUseHandler> {

    public LavaSponge(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, ItemStack recipeOutput) {
        super(category, item, recipeType, recipe, recipeOutput);

    }

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {

            Player p = e.getPlayer();
            ItemStack item = e.getItem();

            if (!isItem(item))
                return;

            e.cancel();

            RayTraceResult rayResult = p.rayTraceBlocks(5d, FluidCollisionMode.SOURCE_ONLY);

            if (rayResult != null) {

                Block b = rayResult.getHitBlock();

                if (b == null || b.getType() != Material.LAVA) {
                    return;
                }

                // 3x3x3 Radius
                for (int x = -1; x < 2; x++) {
                    for (int y = -1; y < 2; y++) {
                        for (int z = -1; z < 2; z++) {
                            Block rel = b.getRelative(x, y, z);
                            if (rel.getType() == Material.LAVA
                                && Slimefun.getProtectionManager().hasPermission(e.getPlayer(), rel,
                                Interaction.BREAK_BLOCK)
                            ) {
                                rel.setType(Material.AIR);
                            }
                        }
                    }
                }

                p.getLocation().getWorld().playSound(p.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 0.5F, 0.5F);
                item.setAmount(item.getAmount() - 1);

            }
        };
    }
}
