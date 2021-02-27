package io.ncbpfluffybear.supserv;

import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

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

    public static final SlimefunItemStack WITHER_SKELETON_SKULL = new SlimefunItemStack("WITHER_SKELETON_SKULL",
        Material.WITHER_SKELETON_SKULL, null);

}
