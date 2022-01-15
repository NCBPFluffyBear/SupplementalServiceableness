package io.ncbpfluffybear.supserv.machines;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.handlers.SimpleBlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.inventory.InvUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nonnull;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class HeadGrinder extends SlimefunItem implements EnergyNetComponent {

    private final int[] BORDER = new int[]{
            0, 1, 2, 3, 4, 5, 6, 7, 8,
            9, 17,
            18, 22, 25, 26,
            27, 31, 34, 35,
            36, 37, 38, 39, 44,
            45, 46, 47, 48, 49, 50, 51, 52, 53
    };

    private final int[] INPUT_BORDER = new int[]{
            10, 11, 12,
            19, 21,
            28, 29, 30
    };

    private final int[] OUTPUT_BORDER = new int[]{
            13, 14, 15, 16,
            22, 25,
            31, 34,
            40, 41, 42, 43
    };

    private final int INPUT_SLOT = 20;
    private final int[] OUTPUT_SLOTS = new int[]{23, 24, 32, 33};

    public static final int ENERGY_CONSUMPTION = 128;
    public static final int CAPACITY = 1024;

    private static final Material[] MUSIC_DISCS = new Material[]{
            Material.MUSIC_DISC_11, Material.MUSIC_DISC_13, Material.MUSIC_DISC_CAT,
            Material.MUSIC_DISC_BLOCKS, Material.MUSIC_DISC_CHIRP, Material.MUSIC_DISC_MALL,
            Material.MUSIC_DISC_MELLOHI, Material.MUSIC_DISC_FAR, Material.MUSIC_DISC_PIGSTEP,
            Material.MUSIC_DISC_STAL, Material.MUSIC_DISC_STRAD, Material.MUSIC_DISC_WAIT,
            Material.MUSIC_DISC_WARD
    };

    private static final ItemStack[] ZOMBIE_MAX_DROPS = new ItemStack[]{new ItemStack(Material.ROTTEN_FLESH, 2)};
    private static final ItemStack[] SKELETON_MAX_DROPS = new ItemStack[]{
            new ItemStack(Material.BONE, 2),
            new ItemStack(Material.ARROW, 2)
    };
    private static final ItemStack[] CREEPER_MAX_DROPS = new ItemStack[]{
            new ItemStack(Material.GUNPOWDER, 2),
            new ItemStack(Material.MUSIC_DISC_11) // Generic non-stackable disc
    };
    private static final ItemStack[] WITHER_SKELETON_DROPS = new ItemStack[]{
            new ItemStack(Material.BONE, 2),
            new ItemStack(Material.COAL, 1),
            new ItemStack(Material.WITHER_SKELETON_SKULL, 1)
    };


    public HeadGrinder(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        new BlockMenuPreset(getId(), "&4Head Grinder") {

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
                if (flow == ItemTransportFlow.INSERT) {
                    return new int[]{INPUT_SLOT};
                } else {
                    return OUTPUT_SLOTS;
                }
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

        for (int slot : OUTPUT_BORDER) {
            menu.addItem(slot, ChestMenuUtils.getOutputSlotTexture());
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
                    inv.dropItems(b.getLocation(), OUTPUT_SLOTS);
                }
            }

        };
    }

    @Override
    public void preRegister() {
        this.addItemHandler(new BlockTicker() {
            public void tick(Block b, SlimefunItem sf, Config data) {
                HeadGrinder.this.tick(b);
            }

            public boolean isSynchronized() {
                return false;
            }
        });
    }

    private void tick(@Nonnull Block b) {
        if (getCharge(b.getLocation()) < ENERGY_CONSUMPTION) {
            return;
        }

        BlockMenu menu = BlockStorage.getInventory(b);
        ItemStack skull = menu.getItemInSlot(INPUT_SLOT);

        if (skull == null) {
            return;
        }

        ItemStack[] drops = getMobDrops(skull, menu);

        if (drops == null) { // Input not compatible
            return;
        }

        // Consume; Move all to output
        menu.consumeItem(INPUT_SLOT, 1);
        for (ItemStack item : drops) {
            menu.pushItem(item, OUTPUT_SLOTS);
        }

        // Consume charge
        removeCharge(b.getLocation(), ENERGY_CONSUMPTION);
    }

    /**
     * Gets the mob drops of a skull if applicable
     */
    private ItemStack[] getMobDrops(ItemStack item, BlockMenu menu) {
        Material mat = item.getType();
        List<ItemStack> drops = new ArrayList<>();
        ThreadLocalRandom rand = ThreadLocalRandom.current(); // Upper bound is exclusive

        switch (mat) {
            case ZOMBIE_HEAD:
                if (isFitImpossible(ZOMBIE_MAX_DROPS, menu)) {
                    return null;
                }

                drops.add(new ItemStack(Material.ROTTEN_FLESH, rand.nextInt(0, 3)));
                break;
            case SKELETON_SKULL:
                if (isFitImpossible(SKELETON_MAX_DROPS, menu)) {
                    return null;
                }

                drops.add(new ItemStack(Material.BONE, rand.nextInt(0, 3)));
                drops.add(new ItemStack(Material.ARROW, rand.nextInt(0, 3)));
                break;
            case CREEPER_HEAD:
                if (isFitImpossible(CREEPER_MAX_DROPS, menu)) {
                    return null;
                }

                drops.add(new ItemStack(Material.GUNPOWDER, rand.nextInt(0, 3)));

                if (rand.nextInt(0, 100) == 0) { // 1% chance of music disk
                    drops.add(new ItemStack(MUSIC_DISCS[rand.nextInt(0, 13)], 1)); // Random disc
                }
                break;

            case WITHER_SKELETON_SKULL:
                if (isFitImpossible(WITHER_SKELETON_DROPS, menu)) {
                    return null;
                }

                drops.add(new ItemStack(Material.BONE, rand.nextInt(0, 3)));

                if (rand.nextInt(0, 3) == 0) { // 1/3rd chance
                    drops.add(new ItemStack(Material.COAL, 1));
                }

                if (rand.nextDouble(0, 1) <= 0.25) { // 0.25 % chance
                    drops.add(new ItemStack(Material.WITHER_SKELETON_SKULL, rand.nextInt(0, 2)));
                }
                break;

            default:
                return null;
        }

        // Convert to array
        ItemStack[] finalDrops = new ItemStack[drops.size()];
        finalDrops = drops.toArray(finalDrops);

        return finalDrops;
    }

    /**
     * Used to check if inventory has enough space for all potential drops.
     * Prevents exploiting the machine by clogging slots.
     */
    private boolean isFitImpossible(ItemStack[] drops, BlockMenu menu) {
        return !InvUtils.fitAll(menu.toInventory(), drops, OUTPUT_SLOTS);
    }

    @Nonnull
    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    @Override
    public int getCapacity() {
        return 1024;
    }
}
