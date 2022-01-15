package io.ncbpfluffybear.supserv.items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import io.ncbpfluffybear.supserv.SupServItems;
import io.ncbpfluffybear.supserv.SupServPlugin;
import java.util.Optional;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTeleportEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class EndermanTether extends SlimefunItem implements Listener, NotPlaceable {

    private final NamespacedKey NSK = new NamespacedKey(SupServPlugin.getInstance(), "enderman-tether");
    private final ItemStack SKULL = SlimefunUtils.getCustomHead(
            "883dd0f90df05fe6a09aaccaf54bc043e455e1c865bda1fd272e3f47fb9bb910");

    public EndermanTether(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        Bukkit.getPluginManager().registerEvents(this, SupServPlugin.getInstance());

        addItemHandler(onPlace());
    }

    private ItemUseHandler onPlace() {
        return e -> {
            e.cancel();
            e.getItem().setAmount(e.getItem().getAmount() - 1);

            Optional<Block> optB = e.getClickedBlock();

            if (!optB.isPresent()) {
                return;
            }

            Block b = optB.get();

            Location l = b.getRelative(e.getClickedFace()).getLocation();
            ArmorStand stand = (ArmorStand) l.getWorld().spawnEntity(l.add(0.5, -0.5, 0.5), EntityType.ARMOR_STAND);

            stand.setGravity(false);
            stand.setCanPickupItems(false);
            stand.setVisible(false);
            stand.setSmall(true);
            stand.getEquipment().setHelmet(SKULL.clone());
            stand.getPersistentDataContainer().set(NSK, PersistentDataType.INTEGER, 1);
        };
    }

    @EventHandler
    private void onEndermanTeleport(EntityTeleportEvent e) {
        if (e.getEntityType() != EntityType.ENDERMAN) {
            return;
        }

        for (Entity en : e.getEntity().getNearbyEntities(3, 2, 3)) {
            if (en instanceof ArmorStand && en.getPersistentDataContainer().getOrDefault(NSK, PersistentDataType.INTEGER, 0) == 1) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    private void onArmorStandInteract(PlayerInteractAtEntityEvent e) {
        Entity en = e.getRightClicked();

        if (en.getType() == EntityType.ARMOR_STAND && en.getPersistentDataContainer().getOrDefault(NSK, PersistentDataType.INTEGER, 0) == 1) {
            e.setCancelled(true);

            en.remove();
            en.getLocation().getWorld().dropItemNaturally(en.getLocation().add(-0.5, 0.5, -0.5), SupServItems.ENDERMAN_TETHER);
        }
    }

}
