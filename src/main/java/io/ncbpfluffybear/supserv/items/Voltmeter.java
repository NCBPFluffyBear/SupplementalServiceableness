package io.ncbpfluffybear.supserv.items;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNet;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Voltmeter extends SimpleSlimefunItem<ItemUseHandler> {

    public Voltmeter(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return new ItemUseHandler() {
            @Override
            public void onRightClick(PlayerRightClickEvent e) {
                ItemStack item = e.getItem();
                Optional<Block> block = e.getClickedBlock();

                if (!isItem(item) || !block.isPresent()) {
                    return;
                }

                Player player = e.getPlayer();
                EnergyNet energyNet = EnergyNet.getNetworkFromLocation(block.get().getLocation());

                if(Objects.isNull(energyNet)) {
                    return;
                }

                Map<Location, EnergyNetComponent> value = null;

                try {
                    Field field = energyNet.getClass().getDeclaredField("generators");
                    field.setAccessible(true);

                    value = (Map<Location, EnergyNetComponent>)field.get(energyNet);

                } catch (NoSuchFieldException ex) {
                    ex.printStackTrace();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }

                System.out.println(value);
            }
        };
    }
}
