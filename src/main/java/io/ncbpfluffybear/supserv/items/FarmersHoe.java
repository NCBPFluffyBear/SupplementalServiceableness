package io.ncbpfluffybear.supserv.items;

import io.github.thebusybiscuit.slimefun4.core.attributes.DamageableItem;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
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

public class FarmersHoe  extends SimpleSlimefunItem<ItemUseHandler> implements NotPlaceable, DamageableItem {

    public FarmersHoe(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

    }
    public static final List<Material> breakOnly = Arrays.asList(Material.MELON, Material.PUMPKIN);
    public static final List<Material> farmables = Arrays.asList(Material.DIRT, Material.GRASS_BLOCK, Material.COARSE_DIRT);
    public static final List<Material> Spectallplants = Arrays.asList(Material.BAMBOO, Material.CHORUS_PLANT);
    public static final List<Material> tallplants = Arrays.asList(Material.SUGAR_CANE, Material.CACTUS);
    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {

            Player p = e.getPlayer();
            ItemStack item = e.getItem();


            if(!e.getClickedBlock().isPresent()) {
                return;    // Check if clicked block is null > return
            }
            Block b = e.getClickedBlock().get();
            if(farmables.contains(b.getType())) {
                return;
            }
            e.cancel();
            if(!SlimefunPlugin.getProtectionManager().hasPermission(e.getPlayer(), b,
                    ProtectableAction.BREAK_BLOCK)) {// Check if player may 'break' the block to harvest
                return;
            }
            //p.sendMessage(b.getType().toString()) Debug only
            Material pastMat = b.getType();
            if(Spectallplants.contains(b.getType())) { // type is growing vertically
                b.breakNaturally(item);
                b.getRelative(0, 1, 0).breakNaturally(item);
                b.setType(getcrop(pastMat));
                damageItem(p, item);
                return;
            }

            if(tallplants.contains(b.getType())) { // type is growing vertically
                if(b.getRelative(0,1,0).getType() != b.getType()) { //to prevent people farming 1 high cactus/sugarcane
                    return;
                }
                b.breakNaturally(item);
                b.getRelative(0, 1, 0).breakNaturally(item);
                b.setType(getcrop(pastMat));
                damageItem(p, item);
                return;
            }

            if(breakOnly.contains(b.getType())) { // type is growing vertically
                b.breakNaturally(item);
                damageItem(p, item);
                return;
            }

            if(!(b.getBlockData() instanceof Ageable)) {
                return;  // Check if clicked block is Ageable (and thus a crop), if not return
            }

            Ageable crop = (Ageable) b.getBlockData();
            if(crop.getAge() != crop.getMaximumAge()) {
                return; // if crop is not fully grown return
            }

            b.breakNaturally(item);
            b.setType(e.getClickedBlock().get().getType());

            crop.setAge(CropState.SEEDED.ordinal());
            b.setBlockData(crop, false);
            damageItem(p, item);
        };
    }

    @Override
    public boolean isDamageable() {
        return true;
    }

    public Material getcrop(Material mat) {
        switch (mat) {
            case BAMBOO:
                return Material.BAMBOO_SAPLING;
            case CHORUS_PLANT:
                return Material.CHORUS_FLOWER;
            default:
                return mat;
        }
    }
}
