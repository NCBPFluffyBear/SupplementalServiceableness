package io.ncbpfluffybear.supserv;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.Composter;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.machines.ElectricPress;
import io.github.thebusybiscuit.slimefun4.implementation.items.multiblocks.OreCrusher;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import io.ncbpfluffybear.supserv.machines.AdvancedElectricComposter;
import io.ncbpfluffybear.supserv.machines.Baler;
import io.ncbpfluffybear.supserv.machines.ExpGenerator;
import io.ncbpfluffybear.supserv.machines.HeadGrinder;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class SupServItems {

    private SupServItems() {
    }

    // Category
    public static final ItemGroup SUPSERV_CATEGORY = new ItemGroup(new NamespacedKey(SupServPlugin.getInstance(),
            "supservcategory"),
            new CustomItemStack(Material.NETHERITE_BLOCK, "&aSupplementalServiceableness")
    );

    public static final SlimefunItemStack LAVA_SPONGE = new SlimefunItemStack("LAVA_SPONGE",
            Material.BROWN_DYE,
            "&6Lava Sponge",
            "",
            "&7A sponge that soaks up lava",
            "&eRight Click &7to remove a 3x3x3 area of lava",
            "&7Can not be reused",
            "",
            "&9Suggested by CrystalNeko"
    );

    public static final SlimefunItemStack CHIPPED_WITHER_SKELETON_SKULL = new SlimefunItemStack("CHIPPED_WITHER_SKELETON_SKULL",
            Material.MELON_SEEDS,
            "&7&lChipped Wither Skeleton Skull",
            "",
            "&7A very small piece of a wither skeleton skull",
            "",
            "&9Suggested by CrystalNeko"
    );

    public static final SlimefunItemStack FRAGMENTED_WITHER_SKELETON_SKULL = new SlimefunItemStack("FRAGMENTED_WITHER_SKELETON_SKULL",
            Material.FLINT,
            "&7&lFragmented Wither Skeleton Skull",
            "",
            "&7A small piece of a wither skeleton skull",
            "",
            "&9Suggested by CrystalNeko"
    );

    public static final SlimefunItemStack CONDENSED_NETHER_STAR_BLOCK = new SlimefunItemStack("CONDENSED_NETHER_STAR_BLOCK",
            Material.QUARTZ_BRICKS,
            "&f&lCondensed Nether Star Block",
            "",
            "&7Many &eNether Stars &7smashed together"
    );

    public static final SlimefunItemStack INGOT_OF_AFTERLIFE = new SlimefunItemStack("INGOT_OF_AFTERLIFE",
            Material.NETHER_BRICK,
            "&4Ingot of Afterlife",
            "",
            "&7Condensed &4Essence of Afterlife"
    );

    public static final SlimefunItemStack DRAGON_ESSENCE = new SlimefunItemStack("DRAGON_ESSENCE",
            Material.DRAGON_BREATH,
            "&4&lDragon Essence",
            "",
            "&7The essence of a &4&lDragon &7captured in a bottle"
    );

    public static final SlimefunItemStack WITHER_SKELETON_SKULL = new SlimefunItemStack("WITHER_SKELETON_SKULL",
            new ItemStack(Material.WITHER_SKELETON_SKULL));

    public static final SlimefunItemStack DRAGON_EGG = new SlimefunItemStack("DRAGON_EGG",
            new ItemStack(Material.DRAGON_EGG));

    public static final SlimefunItemStack FARMERS_HOE = new SlimefunItemStack("FARMERS_HOE",
            Material.IRON_HOE,
            "&3Farmers Hoe",
            "",
            "&7Makes sure harvested plots get reseeded, ",
            "&7at the cost of durability",
            "",
            "&9Suggested by Caveman"
    );

    public static final SlimefunItemStack WATERING_CAN = new SlimefunItemStack("SS_WATERING_CAN",
            "6484da45301625dee79ae29ff513efa583f1ed838033f20db80963cedf8aeb0e",
            "&bWatering Can",
            "",
            "&fWaters Plants",
            "",
            "&7> &eRight Click &7a water to fill your watering can",
            "&7> &eRight Click &7a plant to speed up growth.",
            "&7> &eRight Click &7a player to slow them down",
            "",
            "&aUses Left: &e0"
    );

    public static final SlimefunItemStack WATERING_CAN_IRON = new SlimefunItemStack("WATERING_CAN_IRON",
            "6484da45301625dee79ae29ff513efa583f1ed838033f20db80963cedf8aeb0e",
            "&bWatering Can [IRON]",
            "",
            "&fWaters Plants",
            "",
            "&7> &eRight Click &7a water to fill your watering can",
            "&7> &eRight Click &7a plant to speed up growth.",
            "&7> &eRight Click &7a player to slow them down",
            "",
            "&aUses Left: &e0"
    );

    //DUMMY ITEM FOR IRON WATERING CAN RECIPE USING THE WATERING CAN FROM FLUFFYMACHINES
    public static final SlimefunItemStack WATERING_CAN_IRON_FM = new SlimefunItemStack("WATERING_CAN_IRON_FM",
            "6484da45301625dee79ae29ff513efa583f1ed838033f20db80963cedf8aeb0e",
            "&bWatering Can [IRON] &4USE FOR CONVERSION ONLY",
            "",
            "&4NOTE: ONLY USE FOR CONVERSION FROM ",
            "&4Fluffy Machines to SupplementalServiceableness"
    );
    public static final SlimefunItemStack WATERING_CAN_GOLD = new SlimefunItemStack("WATERING_CAN_GOLD",
            "6484da45301625dee79ae29ff513efa583f1ed838033f20db80963cedf8aeb0e",
            "&bWatering Can [GOLD]",
            "",
            "&fWaters Plants",
            "",
            "&7> &eRight Click &7a water to fill your watering can",
            "&7> &eRight Click &7a plant to speed up growth.",
            "&7> &eRight Click &7a player to slow them down",
            "",
            "&aUses Left: &e0"
    );

    public static final SlimefunItemStack WATERING_CAN_DIAMOND = new SlimefunItemStack("WATERING_CAN_DIAMOND",
            "6484da45301625dee79ae29ff513efa583f1ed838033f20db80963cedf8aeb0e",
            "&bWatering Can [DIAMOND]",
            "",
            "&fWaters Plants",
            "",
            "&7> &eRight Click &7a water to fill your watering can",
            "&7> &eRight Click &7a plant to speed up growth.",
            "&7> &eRight Click &7a player to slow them down",
            "",
            "&aUses Left: &e0"
    );

    public static final SlimefunItemStack WATERING_CAN_EMERALD = new SlimefunItemStack("WATERING_CAN_EMERALD",
            "6484da45301625dee79ae29ff513efa583f1ed838033f20db80963cedf8aeb0e",
            "&bWatering Can [EMERALD]",
            "",
            "&fWaters Plants",
            "",
            "&7> &eRight Click &7a water to fill your watering can",
            "&7> &eRight Click &7a plant to speed up growth.",
            "&7> &eRight Click &7a player to slow them down",
            "",
            "&aUses Left: &e0"
    );

    public static final SlimefunItemStack WATERING_CAN_NETHERITE = new SlimefunItemStack("WATERING_CAN_NETHERITE",
            "6484da45301625dee79ae29ff513efa583f1ed838033f20db80963cedf8aeb0e",
            "&bWatering Can [NETHERITE]",
            "",
            "&fWaters Plants",
            "",
            "&7> &eRight Click &7a water to fill your watering can",
            "&7> &eRight Click &7a plant to speed up growth.",
            "&7> &eRight Click &7a player to slow them down",
            "",
            "&aUses Left: &e0"
    );

    public static final SlimefunItemStack VOLTMETER = new SlimefunItemStack("VOLTMETER", Material.CLOCK,
            "&eVoltmeter",
            "",
            "&7This tool shows you statistics",
            "&7about your energy network",
            "&7&o(might not work with specific machines)",
            "",
            "&7> &eRight click &7your network component",
            "&7> &eShift + Right click &7to change mode"
    );

    public static final SlimefunItemStack COMPRESSED_COBBLESTONE = new SlimefunItemStack("COMPRESSED_COBBLESTONE",
            "57f99971601ee77666c05923f341a89ceba1357532279e3926aea79f55d263a0",
            "&f&lCompressed Cobblestone",
            "",
            "&7Contains 9 Cobblestone",
            "",
            "&9Suggested by Skizzles"
    );

    public static final SlimefunItemStack DOUBLE_COMPRESSED_COBBLESTONE = new SlimefunItemStack("DOUBLE_COMPRESSED_COBBLESTONE",
            "8c96220dc7b85b909a575acfaffb06c8c878a2d515dbec28bf2680346acf173f",
            "&f&lDouble Compressed Cobblestone",
            "",
            "&7Contains 81 Cobblestone",
            "",
            "&9Suggested by Skizzles"
    );

    public static final SlimefunItemStack TRIPLE_COMPRESSED_COBBLESTONE = new SlimefunItemStack("TRIPLE_COMPRESSED_COBBLESTONE",
            "9e2b0924aa2b424e0ff6616a93c8ef487057745af1aa5cd223c541ebd3a688a3",
            "&7&lTriple Compressed Cobblestone",
            "",
            "&7Contains 729 Cobblestone",
            "",
            "&9Suggested by Skizzles"
    );

    public static final SlimefunItemStack QUADRUPLE_COMPRESSED_COBBLESTONE = new SlimefunItemStack("QUADRUPLE_COMPRESSED_COBBLESTONE",
            "37fba6ba0e17007ae17cb1b48f49c26a0256a7d2e8884c3cf07aaede025ebb72",
            "&7&lQuadruple Compressed Cobblestone",
            "",
            "&7Contains 6,561 Cobblestone",
            "",
            "&9Suggested by Skizzles"
    );

    public static final SlimefunItemStack QUINTUPLE_COMPRESSED_COBBLESTONE = new SlimefunItemStack("QUINTUPLE_COMPRESSED_COBBLESTONE",
            "b5a86541ed9cd29fcac0a801cece9c27a00549ecf41c46ded300bb012da59390",
            "&7&lQuintuple Compressed Cobblestone",
            "",
            "&7Contains 59,049 Cobblestone",
            "",
            "&9Suggested by Skizzles"
    );

    public static final SlimefunItemStack SEXTUPLE_COMPRESSED_COBBLESTONE = new SlimefunItemStack("SEXTUPLE_COMPRESSED_COBBLESTONE",
            "9ec69a73450820bb97b51509b334eb0d9f6c1f8a9d515fad57f3b7619aa3af9a",
            "&8&lSextuple Compressed Cobblestone",
            "",
            "&7Contains 531,441 Cobblestone",
            "",
            "&9Suggested by Skizzles"
    );

    public static final SlimefunItemStack SEPTUPLE_COMPRESSED_COBBLESTONE = new SlimefunItemStack("SEPTUPLE_COMPRESSED_COBBLESTONE",
            "b6ebc30aa2edfa1991a5ba77e2f2cb9d7398d375be99b1c1eff7aef2dddf7399",
            "&8&lSeptuple Compressed Cobblestone",
            "",
            "&7Contains 4,782,969 Cobblestone",
            "",
            "&9Suggested by Skizzles"
    );

    public static final SlimefunItemStack OCTUPLE_COMPRESSED_COBBLESTONE = new SlimefunItemStack("OCTUPLE_COMPRESSED_COBBLESTONE",
            "811b19b06813d0388eae03bb2c97621c48a78b34f735a925787934a6c304199a",
            "&8&lOctuple Compressed Cobblestone",
            "",
            "&7Contains 43,046,721 Cobblestone",
            "",
            "&9Suggested by Skizzles"
    );

    public static final SlimefunItemStack WITHER_PROOF_SEA_LANTERN = new SlimefunItemStack("WP_SEA_LANTERN",
            Material.SEA_LANTERN,
            "&3Wither-Proof Sea Lantern",
            "",
            "&7Lights up your area, while resistant",
            "&7against attacks from withers",
            "",
            "&9Suggested by GamingRabbit17"
    );

    public static final SlimefunItemStack WITHER_PROOF_GLOWSTONE = new SlimefunItemStack("WP_GLOWSTONE",
            Material.GLOWSTONE,
            "&3Wither-Proof Glowstone",
            "",
            "&7Lights up your area, while resistant",
            "&7against attacks from withers",
            "",
            "&9Suggested by GamingRabbit17"
    );

    public static final SlimefunItemStack WITHER_PROOF_SHROOMLIGHT = new SlimefunItemStack("WP_SHROOMLIGHT",
            Material.SHROOMLIGHT,
            "&3Wither-Proof Shroomlight",
            "",
            "&7Lights up your area, while resistant",
            "&7against attacks from withers",
            "",
            "&9Suggested by GamingRabbit17"
    );

    public static final SlimefunItemStack WITHER_PROOF_PEARLESCENT_FROGLIGHT = new SlimefunItemStack("WP_PEARLESCENT_FROGLIGHT",
            Material.PEARLESCENT_FROGLIGHT,
            "&3Wither-Proof Pearlescent Froglight",
            "",
            "&7Lights up your area, while resistant",
            "&7against attacks from withers",
            "",
            "&9Suggested by Xanolok"
    );

    public static final SlimefunItemStack WITHER_PROOF_VERDANT_FROGLIGHT = new SlimefunItemStack("WP_VERDANT_FROGLIGHT",
            Material.VERDANT_FROGLIGHT,
            "&3Wither-Proof Verdant Froglight",
            "",
            "&7Lights up your area, while resistant",
            "&7against attacks from withers",
            "",
            "&9Suggested by Xanolok"
    );

    public static final SlimefunItemStack WITHER_PROOF_OCHRE_FROGLIGHT = new SlimefunItemStack("WP_OCHRE_FROGLIGHT",
            Material.OCHRE_FROGLIGHT,
            "&3Wither-Proof Ochre Froglight",
            "",
            "&7Lights up your area, while resistant",
            "&7against attacks from withers",
            "",
            "&9Suggested by Xanolok"
    );

    public static final SlimefunItemStack ADVANCED_ELECTRIC_COMPOSTER = new SlimefunItemStack("ADVANCED_ELECTRIC_COMPOSTER",
            Material.RED_TERRACOTTA,
            "&cAdvanced Electric Composter",
            "",
            "&7Composts Exotic Garden saplings",
            "&7also turns soul soil into soul sand",
            "",
            "&aMedium Machine",
            LoreBuilder.powerBuffer(AdvancedElectricComposter.CAPACITY),
            LoreBuilder.powerPerSecond(AdvancedElectricComposter.ENERGY_CONSUMPTION),
            "",
            "&9Suggested by VoidTenno"
    );

    public static final SlimefunItemStack EXP_GENERATOR = new SlimefunItemStack("EXP_GENERATOR",
            Material.LIME_STAINED_GLASS,
            "&aExp Generator",
            "",
            "&7Converts experience to electricity",
            "",
            "&aMedium Generator",
            LoreBuilder.powerBuffer(ExpGenerator.CAPACITY),
            LoreBuilder.powerPerSecond(ExpGenerator.ENERGY_PER_BOTTLE),
            "",
            "&9Suggested by ShenDaShep"
    );

    public static final SlimefunItemStack BALER = new SlimefunItemStack("BALER",
            Material.BARREL,
            "&eBaler",
            "",
            "&7Compresses wheat into hay blocks",
            "",
            "&eBasic Machine",
            LoreBuilder.powerBuffer(Baler.CAPACITY),
            LoreBuilder.powerPerSecond(Baler.ENERGY_CONSUMPTION),
            "",
            "&9Suggested by PlasticGerm8702"
    );

    public static final SlimefunItemStack ENDERMAN_TETHER = new SlimefunItemStack("ENDERMAN_TETHER",
            Material.END_ROD,
            "&3Enderman Tether",
            "",
            "&7Prevents enderman from teleporting",
            "&7out of a 3x3 area",
            "",
            "&7Right Click to pick up",
            "",
            "&9Suggested by VoidTenno"
    );

    public static final SlimefunItemStack HEAD_GRINDER = new SlimefunItemStack("HEAD_GRINDER",
            Material.SMOKER,
            "&4Head Grinder",
            "",
            "&7Grinds mob heads into drops",
            "",
            "&4End-Game Machine",
            LoreBuilder.powerBuffer(HeadGrinder.CAPACITY),
            LoreBuilder.powerPerSecond(HeadGrinder.ENERGY_CONSUMPTION),
            "",
            "&9Suggested by Stoneshot"
    );

    static {

        // Press Recipes
        addPressRecipe(3, new ItemStack(Material.COBBLESTONE, 9),
                COMPRESSED_COBBLESTONE
        );
        addPressRecipe(6, new SlimefunItemStack(COMPRESSED_COBBLESTONE, 9),
                DOUBLE_COMPRESSED_COBBLESTONE
        );
        addPressRecipe(9, new SlimefunItemStack(DOUBLE_COMPRESSED_COBBLESTONE, 9),
                TRIPLE_COMPRESSED_COBBLESTONE
        );
        addPressRecipe(12, new SlimefunItemStack(TRIPLE_COMPRESSED_COBBLESTONE, 9),
                QUADRUPLE_COMPRESSED_COBBLESTONE
        );
        addPressRecipe(15, new SlimefunItemStack(QUADRUPLE_COMPRESSED_COBBLESTONE, 9),
                QUINTUPLE_COMPRESSED_COBBLESTONE
        );
        addPressRecipe(18, new SlimefunItemStack(QUINTUPLE_COMPRESSED_COBBLESTONE, 9),
                SEXTUPLE_COMPRESSED_COBBLESTONE
        );
        addPressRecipe(21, new SlimefunItemStack(SEXTUPLE_COMPRESSED_COBBLESTONE, 9),
                SEPTUPLE_COMPRESSED_COBBLESTONE
        );
        addPressRecipe(24, new SlimefunItemStack(SEPTUPLE_COMPRESSED_COBBLESTONE, 9),
                OCTUPLE_COMPRESSED_COBBLESTONE
        );

        // Ore Crusher Recipes
        addOreCrusherRecipe(new ItemStack[]{new ItemStack(Material.RAW_COPPER_BLOCK, 1)},
                new SlimefunItemStack(SlimefunItems.COPPER_DUST, 9)
        );
        addOreCrusherRecipe(new ItemStack[]{new ItemStack(Material.RAW_IRON_BLOCK, 1)},
                new SlimefunItemStack(SlimefunItems.IRON_DUST, 9)
        );
        addOreCrusherRecipe(new ItemStack[]{new ItemStack(Material.RAW_GOLD_BLOCK, 1)},
                new SlimefunItemStack(SlimefunItems.GOLD_DUST, 9)
        );
        addOreCrusherRecipe(new ItemStack[]{new ItemStack(Material.STONE, 8)},
                new ItemStack(Material.COBBLESTONE, 8)
        );

        // Composter Recipes
        addComposterRecipe(new ItemStack(Material.SOUL_SOIL), new ItemStack(Material.SOUL_SAND));
    }

    /**
     * Registers a recipe to both tiers of Electric Presses
     *
     * @param seconds the time it takes to run
     * @param input   the item that is inserted
     * @param output  the output item
     */
    private static void addPressRecipe(int seconds, ItemStack input, ItemStack output) {
        ((ElectricPress) SlimefunItems.ELECTRIC_PRESS.getItem()).registerRecipe(
                seconds, input, output
        );

        ((ElectricPress) SlimefunItems.ELECTRIC_PRESS_2.getItem()).registerRecipe(
                seconds, input, output
        );
    }

    /**
     * Registers a recipe to Ore Crusher
     *
     * @param input an array of items that are inserted
     * @param output the output item
     */
    private static void addOreCrusherRecipe(ItemStack[] input, ItemStack output) {
        ((OreCrusher) SlimefunItems.ORE_CRUSHER.getItem()).addRecipe(input, output);
    }

    /**
     * Register a recipe to Composter
     *
     * @param input the item that is inserted
     * @param output the output item
     */
    private static void addComposterRecipe(ItemStack input, ItemStack output) {
        ((Composter) SlimefunItems.COMPOSTER.getItem()).getDisplayRecipes().addAll(
                Arrays.asList(input, output)
        );
    }
}
