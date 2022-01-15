package io.ncbpfluffybear.supserv.machines;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.handlers.SimpleBlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import javax.annotation.Nonnull;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ExpGenerator extends SlimefunItem implements EnergyNetProvider {

    private final int[] BORDER = new int[]{
            0, 1, 2, 3, 4, 5, 6, 7, 8,
            9, 10, 11, 15, 16, 17,
            18, 19, 20, 24, 25, 26,
            27, 28, 29, 33, 34, 35,
            36, 37, 38, 39, 40, 41, 42, 43, 44
    };

    private final int[] INPUT_BORDER = new int[]{
            12, 13, 14,
            21, 23,
            30, 31, 32
    };

    private final int INPUT_SLOT = 22;

    public static final int CAPACITY = 1024;
    public static final int ENERGY_PER_BOTTLE = 128;

    public ExpGenerator(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        new BlockMenuPreset(getId(), "&aExp Generator") {

            @Override
            public void init() {
                buildBorder(this);
            }

            @Override
            public boolean canOpen(@Nonnull Block b, @Nonnull Player p) {
                return p.hasPermission("slimefun.inventory.bypass")
                        || Slimefun.getProtectionManager().hasPermission(p, b.getLocation(),
                        Interaction.INTERACT_BLOCK);
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                return new int[0];
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(DirtyChestMenu menu, ItemTransportFlow flow, ItemStack item) {
                if (flow == ItemTransportFlow.INSERT && item.getType() == Material.EXPERIENCE_BOTTLE) {
                    return new int[]{INPUT_SLOT};
                }

                return null;
            }
        };

        addItemHandler(onBlockBreak());
    }

    private void buildBorder(BlockMenuPreset menu) {
        for (int slot : BORDER) {
            menu.addItem(slot, ChestMenuUtils.getBackground());
            menu.addMenuClickHandler(slot, ChestMenuUtils.getEmptyClickHandler());
        }

        for (int slot : INPUT_BORDER) {
            menu.addItem(slot, ChestMenuUtils.getInputSlotTexture());
            menu.addMenuClickHandler(slot, ChestMenuUtils.getEmptyClickHandler());
        }
    }

    @Nonnull
    protected BlockBreakHandler onBlockBreak() {
        return new SimpleBlockBreakHandler() {

            @Override
            public void onBlockBreak(@Nonnull Block b) {
                BlockMenu inv = BlockStorage.getInventory(b);

                if (inv != null) {
                    inv.dropItems(b.getLocation(), INPUT_SLOT);
                }
            }

        };
    }

    @Override
    public void preRegister() {
        this.addItemHandler(new BlockTicker() {
            public void tick(Block b, SlimefunItem sf, Config data) {
                ExpGenerator.this.tick(b);
            }

            public boolean isSynchronized() {
                return false;
            }
        });
    }

    private void tick(@Nonnull Block b) {
        BlockMenu menu = BlockStorage.getInventory(b);
        ItemStack bottles = menu.getItemInSlot(INPUT_SLOT);

        // Not exp bottle or energy will surpass max, cancel
        if (bottles != null && bottles.getType() == Material.EXPERIENCE_BOTTLE
                && getCharge(b.getLocation()) + ENERGY_PER_BOTTLE <= CAPACITY
        ) {
            // Consume bottle, add energy
            bottles.setAmount(bottles.getAmount() - 1);
            addCharge(b.getLocation(), ENERGY_PER_BOTTLE);
        }
    }


    @Nonnull
    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.GENERATOR;
    }

    /**
     * No passive energy generation
     */
    @Override
    public int getGeneratedOutput(@Nonnull Location location, @Nonnull Config config) {
        return 0;
    }

    @Override
    public int getCapacity() {
        return 1024;
    }
}
