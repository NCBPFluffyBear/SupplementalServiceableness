package io.ncbpfluffybear.supserv.machines;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Baler extends AContainer implements RecipeDisplayItem {

    public static final int CAPACITY = 256;
    public static final int ENERGY_CONSUMPTION = 75;

    public Baler(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(5, new ItemStack(Material.WHEAT, 9), new ItemStack(Material.HAY_BLOCK));
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
        return new ItemStack(Material.IRON_HOE);
    }

    @Nonnull
    @Override
    public String getMachineIdentifier() {
        return "BALER";
    }

    @Nonnull
    @Override
    public String getInventoryTitle() {
        return "&eBaler";
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
