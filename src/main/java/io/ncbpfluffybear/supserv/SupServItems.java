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

    public static final SlimefunItemStack COMPRESSED_COBBLESTONE = new SlimefunItemStack("COMPRESSED_COBBLESTONE",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTdmOTk5NzE2MDFlZTc3NjY2YzA1OTIzZjM0MWE4OWNlYmExMzU3NTMyMjc5ZTM5MjZhZWE3OWY1NWQyNjNhMCJ9fX0=",
            "&f&lCompressed Cobblestone",
            "",
            "&7Contains 9 Cobblestone",
            "",
            "&9Suggested by Skizzles"
    );

    public static final SlimefunItemStack DOUBLE_COMPRESSED_COBBLESTONE = new SlimefunItemStack("DOUBLE_COMPRESSED_COBBLESTONE",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGM5NjIyMGRjN2I4NWI5MDlhNTc1YWNmYWZmYjA2YzhjODc4YTJkNTE1ZGJlYzI4YmYyNjgwMzQ2YWNmMTczZiJ9fX0=",
            "&f&lDouble Compressed Cobblestone",
            "",
            "&7Contains 81 Cobblestone",
            "",
            "&9Suggested by Skizzles"
    );

    public static final SlimefunItemStack TRIPLE_COMPRESSED_COBBLESTONE = new SlimefunItemStack("TRIPLE_COMPRESSED_COBBLESTONE",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWUyYjA5MjRhYTJiNDI0ZTBmZjY2MTZhOTNjOGVmNDg3MDU3NzQ1YWYxYWE1Y2QyMjNjNTQxZWJkM2E2ODhhMyJ9fX0=",
            "&7&lTriple Compressed Cobblestone",
            "",
            "&7Contains 729 Cobblestone",
            "",
            "&9Suggested by Skizzles"
    );

    public static final SlimefunItemStack QUADRUPLE_COMPRESSED_COBBLESTONE = new SlimefunItemStack("QUADRUPLE_COMPRESSED_COBBLESTONE",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzdmYmE2YmEwZTE3MDA3YWUxN2NiMWI0OGY0OWMyNmEwMjU2YTdkMmU4ODg0YzNjZjA3YWFlZGUwMjVlYmI3MiJ9fX0=",
            "&7&lQuadruple Compressed Cobblestone",
            "",
            "&7Contains 6,561 Cobblestone",
            "",
            "&9Suggested by Skizzles"
    );

    public static final SlimefunItemStack QUINTUPLE_COMPRESSED_COBBLESTONE = new SlimefunItemStack("QUINTUPLE_COMPRESSED_COBBLESTONE",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjVhODY1NDFlZDljZDI5ZmNhYzBhODAxY2VjZTljMjdhMDA1NDllY2Y0MWM0NmRlZDMwMGJiMDEyZGE1OTM5MCJ9fX0=",
            "&7&lQuintuple Compressed Cobblestone",
            "",
            "&7Contains 59,049 Cobblestone",
            "",
            "&9Suggested by Skizzles"
    );

    public static final SlimefunItemStack SEXTUPLE_COMPRESSED_COBBLESTONE = new SlimefunItemStack("SEXTUPLE_COMPRESSED_COBBLESTONE",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWVjNjlhNzM0NTA4MjBiYjk3YjUxNTA5YjMzNGViMGQ5ZjZjMWY4YTlkNTE1ZmFkNTdmM2I3NjE5YWEzYWY5YSJ9fX0=",
            "&8&lSextuple Compressed Cobblestone",
            "",
            "&7Contains 531,441 Cobblestone",
            "",
            "&9Suggested by Skizzles"
    );

    public static final SlimefunItemStack SEPTUPLE_COMPRESSED_COBBLESTONE = new SlimefunItemStack("SEPTUPLE_COMPRESSED_COBBLESTONE",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjZlYmMzMGFhMmVkZmExOTkxYTViYTc3ZTJmMmNiOWQ3Mzk4ZDM3NWJlOTliMWMxZWZmN2FlZjJkZGRmNzM5OSJ9fX0=",
            "&8&lSeptuple Compressed Cobblestone",
            "",
            "&7Contains 4,782,969 Cobblestone",
            "",
            "&9Suggested by Skizzles"
    );

    public static final SlimefunItemStack OCTUPLE_COMPRESSED_COBBLESTONE = new SlimefunItemStack("OCTUPLE_COMPRESSED_COBBLESTONE",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODExYjE5YjA2ODEzZDAzODhlYWUwM2JiMmM5NzYyMWM0OGE3OGIzNGY3MzVhOTI1Nzg3OTM0YTZjMzA0MTk5YSJ9fX0=",
            "&8&lOctuple Compressed Cobblestone",
            "",
            "&7Contains 43,046,721 Cobblestone",
            "",
            "&9Suggested by Skizzles"
    );

    public static final SlimefunItemStack CARGO_WAND = new SlimefunItemStack("CARGO_WAND",
        Material.SEA_PICKLE,
        "&9Cargo Wand",
        "",
        "&eRight Click &7to copy cargo node settings",
        "&eLeft Click &7to paste cargo node settings",
        "&eSneak and Left Click &7to wipe settings"
    );

}
