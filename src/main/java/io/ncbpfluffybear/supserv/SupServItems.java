package io.ncbpfluffybear.supserv;

import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.util.Locale;

public class SupServItems {

    private SupServItems() {}

    // Category
    public static final Category SUPSERV_CATEGORY = new Category(new NamespacedKey(SupServPlugin.getInstance(),
        "supservcategory"),
        new CustomItem(Material.NETHERITE_BLOCK, "&aSupplementalServiceableness")
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
        Material.WITHER_SKELETON_SKULL, null);

    public static final SlimefunItemStack DRAGON_EGG = new SlimefunItemStack("DRAGON_EGG",
        Material.DRAGON_EGG, null);

    public static final SlimefunItemStack FARMERS_HOE = new SlimefunItemStack("FARMERS_HOE",
            Material.IRON_HOE,
            "&3Farmers Hoe",
            "",
            "&7Makes sure harvested plots get reseeded, ",
            "&7at the cost of durability",
            "",
            "&9Suggested by Caveman"
    );
    public static final SlimefunItemStack WITHER_PROOF_SEA_LANTERN = new SlimefunItemStack("WP_SEA_LANTERN",
            Material.SEA_LANTERN,
            "&3Wither proof Sea Lantern",
            "",
            "&7Lights up your area, while resistant",
            "&7against attacks from withers",
            "",
            "&9Suggested by GamingRabbit17"
    );
    public static final SlimefunItemStack WITHER_PROOF_GLOWSTONE = new SlimefunItemStack("WP_GLOWSTONE",
            Material.GLOWSTONE,
            "&3Wither proof Glowstone",
            "",
            "&7Lights up your area, while resistant",
            "&7against attacks from withers",
            "",
            "&9Suggested by GamingRabbit17"
    );
    public static final SlimefunItemStack WITHER_PROOF_SHROOMLIGHT = new SlimefunItemStack("WP_SHROOMLIGHT",
            Material.SHROOMLIGHT,
            "&3Wither proof Shroomlight",
            "",
            "&7Lights up your area, while resistant",
            "&7against attacks from withers",
            "",
            "&9Suggested by GamingRabbit17"
    );

}
