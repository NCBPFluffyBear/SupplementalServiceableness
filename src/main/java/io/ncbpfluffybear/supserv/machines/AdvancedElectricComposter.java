package io.ncbpfluffybear.supserv.machines;

import io.github.thebusybiscuit.exoticgarden.Berry;
import io.github.thebusybiscuit.exoticgarden.ExoticGarden;
import io.github.thebusybiscuit.exoticgarden.Tree;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class AdvancedElectricComposter extends AContainer implements RecipeDisplayItem {

    public static final int CAPACITY = 256;
    public static final int ENERGY_CONSUMPTION = 75;

    public AdvancedElectricComposter(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    protected void registerDefaultRecipes() {
        for (Berry plant : ExoticGarden.getBerries()) {
            if (plant.getID().contains("ESSENCE")) {
                continue;
            }

            registerRecipe(5, new CustomItemStack(SlimefunItem.getById(plant.getID() + "_BUSH").getItem(), 5),
                    new ItemStack(Material.DIRT)
            );
        }

        for (Tree plant : ExoticGarden.getTrees()) {
            registerRecipe(5, new CustomItemStack(plant.getItem(), 5),
                    new ItemStack(Material.DIRT)
            );
        }

        registerRecipe(1, new ItemStack(Material.SOUL_SOIL),
                new ItemStack(Material.SOUL_SAND)
        );
    }

    @Nonnull
    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> displayRecipes = new ArrayList<>(recipes.size() * 2);

        for (MachineRecipe recipe : recipes) {
            displayRecipes.add(recipe.getInput()[0]);
            displayRecipes.add(recipe.getOutput()[recipe.getOutput().length - 1]);
        }

        return displayRecipes;
    }


    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.LAVA_BUCKET);
    }

    @Nonnull
    @Override
    public String getMachineIdentifier() {
        return "ADVANCED_ELECTRIC_COMPOSTER";
    }

    @Nonnull
    @Override
    public String getInventoryTitle() {
        return "&cAdvanced Electric Composter";
    }

    @Override
    public int getCapacity() {
        return CAPACITY;
    }

    @Override
    public int getEnergyConsumption() {
        return ENERGY_CONSUMPTION;
    }

    @Override
    public int getSpeed() {
        return 1;
    }
}
