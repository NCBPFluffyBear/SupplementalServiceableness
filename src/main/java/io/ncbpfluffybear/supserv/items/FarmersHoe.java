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
import java.util.HashSet;
import java.util.Set;

public class FarmersHoe extends SimpleSlimefunItem<ItemUseHandler> implements NotPlaceable, DamageableItem {

  private static final Set<Material> BREAK_ONLY = new HashSet<>(Arrays.asList(Material.MELON, Material.PUMPKIN));
  private static final Set<Material> FARMABLES = new HashSet<>(Arrays.asList(Material.DIRT,
          Material.GRASS_BLOCK, Material.COARSE_DIRT));
  private static final Set<Material> TALL_PLANTS = new HashSet<>(Arrays.asList(Material.SUGAR_CANE,
          Material.CACTUS, Material.BAMBOO, Material.CHORUS_PLANT));

  public FarmersHoe(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
    super(category, item, recipeType, recipe);
  }

  @Nonnull
  @Override
  public ItemUseHandler getItemHandler() {
    return e -> {

      Player p = e.getPlayer();
      ItemStack item = e.getItem();

      if (!e.getClickedBlock().isPresent()) {
        return;    // Check if clicked block is null > return
      }

      Block b = e.getClickedBlock().get();
      if (FARMABLES.contains(b.getType())) {
        return;
      }

      e.cancel();
      if (!SlimefunPlugin.getProtectionManager().hasPermission(e.getPlayer(), b, ProtectableAction.BREAK_BLOCK)) {
        return;    // Check if player may 'break' the block to harvest
      }

      Material pastMat = b.getType();
      if (TALL_PLANTS.contains(b.getType())) { // type is growing vertically
        if (b.getRelative(0, 1, 0).getType() != b.getType()) { //to prevent people farming 1 high cactus/sugarcane
          return;
        }
        b.getRelative(0, 1, 0).breakNaturally(item);
        b.setType(getCrop(pastMat));
        damageItem(p, item);
        return;
      }

      if (BREAK_ONLY.contains(b.getType())) { // type is growing vertically
        b.breakNaturally(item);
        damageItem(p, item);
        return;
      }

      if (!(b.getBlockData() instanceof Ageable)) {
        return;  // Check if clicked block is Ageable (and thus a crop), if not return
      }

      Ageable crop = (Ageable) b.getBlockData();
      if (crop.getAge() != crop.getMaximumAge()) {
        return; // if crop is not fully grown return
      }

      b.breakNaturally(item);
      b.setType(e.getClickedBlock().get().getType());
      crop.setAge(CropState.SEEDED.ordinal());
      b.setBlockData(crop, false);
      damageItem(p, item);
    };
  }

  private Material getCrop(Material mat) {
    switch (mat) {
      case BAMBOO:
        return Material.BAMBOO_SAPLING;
      case CHORUS_PLANT:
        return Material.CHORUS_FLOWER;
      default:
        return mat;
    }
  }

  @Override
  public boolean isDamageable() {
    return true;
  }
}
