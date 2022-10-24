package io.ncbpfluffybear.supserv;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.multiblocks.MultiBlockMachine;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.WitherProofBlock;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.ncbpfluffybear.supserv.items.EndermanTether;
import io.ncbpfluffybear.supserv.items.FarmersHoe;
import io.ncbpfluffybear.supserv.items.LavaSponge;
import io.ncbpfluffybear.supserv.items.Voltmeter;
import io.ncbpfluffybear.supserv.items.WateringCan;
import io.ncbpfluffybear.supserv.machines.AdvancedElectricComposter;
import io.ncbpfluffybear.supserv.machines.Baler;
import io.ncbpfluffybear.supserv.machines.ExpGenerator;
import io.ncbpfluffybear.supserv.machines.HeadGrinder;
import io.ncbpfluffybear.supserv.objects.NonInteractableItem;
import io.ncbpfluffybear.supserv.utils.Utils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class SupServItemSetup {

    private SupServItemSetup() {}

    public static void setup(@Nonnull SupServPlugin plugin) {

        new LavaSponge(SupServItems.SUPSERV_CATEGORY, SupServItems.LAVA_SPONGE, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        null, new ItemStack(Material.BROWN_WOOL), null,
                        new ItemStack(Material.BROWN_WOOL), new ItemStack(Material.OBSIDIAN),
                        new ItemStack(Material.BROWN_WOOL),
                        null, new ItemStack(Material.BROWN_WOOL), null
                }, new SlimefunItemStack(SupServItems.LAVA_SPONGE, 8)
        ).register(plugin);

        new NonInteractableItem(SupServItems.SUPSERV_CATEGORY, SupServItems.CHIPPED_WITHER_SKELETON_SKULL,
                RecipeType.MAGIC_WORKBENCH,
                new ItemStack[] {
                        SlimefunItems.COMPRESSED_CARBON, SlimefunItems.COMPRESSED_CARBON, SlimefunItems.COMPRESSED_CARBON,
                        SlimefunItems.COMPRESSED_CARBON, new ItemStack(Material.SKELETON_SKULL),
                        SlimefunItems.COMPRESSED_CARBON,
                        SlimefunItems.COMPRESSED_CARBON, SlimefunItems.COMPRESSED_CARBON, SlimefunItems.COMPRESSED_CARBON
                }).register(plugin);

        new NonInteractableItem(SupServItems.SUPSERV_CATEGORY, SupServItems.FRAGMENTED_WITHER_SKELETON_SKULL,
                RecipeType.MAGIC_WORKBENCH,
                Utils.build3x3Recipe(SupServItems.CHIPPED_WITHER_SKELETON_SKULL)
        ).register(plugin);

        new NonInteractableItem(SupServItems.SUPSERV_CATEGORY, SupServItems.WITHER_SKELETON_SKULL,
                RecipeType.MAGIC_WORKBENCH,
                new ItemStack[] {
                        SupServItems.FRAGMENTED_WITHER_SKELETON_SKULL,
                        SupServItems.FRAGMENTED_WITHER_SKELETON_SKULL, null,
                        SupServItems.FRAGMENTED_WITHER_SKELETON_SKULL, SupServItems.FRAGMENTED_WITHER_SKELETON_SKULL, null,
                        null, null, null
                }, new ItemStack(Material.WITHER_SKELETON_SKULL)
        ).register(plugin);

        new NonInteractableItem(SupServItems.SUPSERV_CATEGORY, SupServItems.CONDENSED_NETHER_STAR_BLOCK,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                Utils.build3x3Recipe(new ItemStack(Material.NETHER_STAR))
        ).register(plugin);

        new NonInteractableItem(SupServItems.SUPSERV_CATEGORY, SupServItems.INGOT_OF_AFTERLIFE,
                RecipeType.MAGIC_WORKBENCH,
                Utils.build3x3Recipe(SlimefunItems.ESSENCE_OF_AFTERLIFE)
        ).register(plugin);

        new NonInteractableItem(SupServItems.SUPSERV_CATEGORY, SupServItems.DRAGON_ESSENCE, RecipeType.ANCIENT_ALTAR,
                new ItemStack[] {
                        SlimefunItems.ENDER_LUMP_3, new ItemStack(Material.DRAGON_BREATH), SlimefunItems.ENDER_LUMP_3,
                        new ItemStack(Material.DRAGON_BREATH), new ItemStack(Material.NETHER_STAR), new ItemStack(Material.DRAGON_BREATH),
                        SlimefunItems.ENDER_LUMP_3, new ItemStack(Material.DRAGON_BREATH), SlimefunItems.ENDER_LUMP_3}
        ).register(plugin);

        new NonInteractableItem(SupServItems.SUPSERV_CATEGORY, SupServItems.DRAGON_EGG,
                RecipeType.ANCIENT_ALTAR,
                new ItemStack[] {
                        SupServItems.INGOT_OF_AFTERLIFE, SupServItems.DRAGON_ESSENCE, SupServItems.INGOT_OF_AFTERLIFE,
                        SupServItems.DRAGON_ESSENCE, SupServItems.CONDENSED_NETHER_STAR_BLOCK, SupServItems.DRAGON_ESSENCE,
                        SupServItems.INGOT_OF_AFTERLIFE, SupServItems.DRAGON_ESSENCE, SupServItems.INGOT_OF_AFTERLIFE
                }, new ItemStack(Material.DRAGON_EGG)
        ).register(plugin);
        // Tools
        new WateringCan(SupServItems.SUPSERV_CATEGORY, SupServItems.WATERING_CAN,
                RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                new ItemStack(Material.STONE), null, new ItemStack(Material.STONE),
                new ItemStack(Material.STONE), new ItemStack(Material.WATER_BUCKET), new ItemStack(Material.STONE),
                null, new ItemStack(Material.STONE), null
        }, WateringCan.canType.STONE).register(plugin);

        new WateringCan(SupServItems.SUPSERV_CATEGORY, SupServItems.WATERING_CAN_IRON,
                RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                new ItemStack(Material.IRON_INGOT), null, new ItemStack(Material.IRON_INGOT),
                new ItemStack(Material.IRON_INGOT), SupServItems.WATERING_CAN, new ItemStack(Material.IRON_INGOT),
                null, new ItemStack(Material.IRON_INGOT), null
        }, WateringCan.canType.IRON).register(plugin);

        new WateringCan(SupServItems.SUPSERV_CATEGORY, SupServItems.WATERING_CAN_GOLD,
                RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                SlimefunItems.GOLD_16K, null, SlimefunItems.GOLD_16K,
                SlimefunItems.GOLD_16K, SupServItems.WATERING_CAN_IRON, SlimefunItems.GOLD_16K,
                null, SlimefunItems.GOLD_16K, null
        }, WateringCan.canType.GOLD).register(plugin);

        new WateringCan(SupServItems.SUPSERV_CATEGORY, SupServItems.WATERING_CAN_DIAMOND,
                RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                SlimefunItems.SYNTHETIC_DIAMOND, null, SlimefunItems.SYNTHETIC_DIAMOND,
                SlimefunItems.SYNTHETIC_DIAMOND, SupServItems.WATERING_CAN_GOLD, SlimefunItems.SYNTHETIC_DIAMOND,
                null, SlimefunItems.SYNTHETIC_DIAMOND, null
        }, WateringCan.canType.DIAMOND).register(plugin);

        new WateringCan(SupServItems.SUPSERV_CATEGORY, SupServItems.WATERING_CAN_EMERALD,
                RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                SlimefunItems.SYNTHETIC_EMERALD, null, SlimefunItems.SYNTHETIC_EMERALD,
                SlimefunItems.SYNTHETIC_EMERALD, SupServItems.WATERING_CAN_DIAMOND, SlimefunItems.SYNTHETIC_EMERALD,
                null, SlimefunItems.SYNTHETIC_EMERALD, null
        }, WateringCan.canType.EMERALD).register(plugin);

        new WateringCan(SupServItems.SUPSERV_CATEGORY, SupServItems.WATERING_CAN_NETHERITE,
                RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                null, null, null,
                null, SupServItems.WATERING_CAN_EMERALD, new ItemStack(Material.NETHERITE_INGOT),
                null, null, null
        }, WateringCan.canType.NETHERITE).register(plugin);

        // Extra recipe watering can to accept watering can Fluffymachines
        if (plugin.getServer().getPluginManager().isPluginEnabled("FluffyMachines")) {
            new WateringCan(SupServItems.SUPSERV_CATEGORY, SupServItems.WATERING_CAN_IRON_FM,
                    RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                    new ItemStack(Material.IRON_INGOT), null, new ItemStack(Material.IRON_INGOT),
                    new ItemStack(Material.IRON_INGOT), SlimefunItem.getById("WATERING_CAN").getItem(), new ItemStack(Material.IRON_INGOT),
                    null, new ItemStack(Material.IRON_INGOT), null
            }, WateringCan.canType.IRON, SupServItems.WATERING_CAN_IRON.clone()).register(plugin);
        }

        new FarmersHoe(SupServItems.SUPSERV_CATEGORY, SupServItems.FARMERS_HOE, RecipeType.ANCIENT_ALTAR,
                new ItemStack[] {
                        SlimefunItems.EARTH_RUNE, SlimefunItems.WATER_RUNE, SlimefunItems.EARTH_RUNE,
                        new ItemStack(Material.BONE_BLOCK), new ItemStack(Material.IRON_HOE), new ItemStack(Material.BONE_BLOCK),
                        SlimefunItems.EARTH_RUNE, SlimefunItems.WATER_RUNE, SlimefunItems.EARTH_RUNE
                }
        ).register(plugin);

        new AdvancedElectricComposter(SupServItems.SUPSERV_CATEGORY, SupServItems.ADVANCED_ELECTRIC_COMPOSTER, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        new ItemStack(Material.NETHER_BRICK_SLAB), SlimefunItems.ELECTRIC_MOTOR, new ItemStack(Material.NETHER_BRICK_SLAB),
                        new ItemStack(Material.NETHER_BRICK_SLAB), SlimefunItem.getById("ELECTRIC_COMPOSTER_2").getItem(), new ItemStack(Material.NETHER_BRICK_SLAB),
                        new ItemStack(Material.NETHER_BRICK_SLAB), SlimefunItems.ADVANCED_CIRCUIT_BOARD, new ItemStack(Material.NETHER_BRICK_SLAB)
                }
        ).register(plugin);

        new ExpGenerator(SupServItems.SUPSERV_CATEGORY, SupServItems.EXP_GENERATOR, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        new ItemStack(Material.OBSIDIAN), SlimefunItems.ELECTRO_MAGNET, new ItemStack(Material.OBSIDIAN),
                        new ItemStack(Material.OBSIDIAN), SlimefunItems.EXP_COLLECTOR, new ItemStack(Material.OBSIDIAN),
                        new ItemStack(Material.OBSIDIAN), SlimefunItems.ELECTRIC_MOTOR, new ItemStack(Material.OBSIDIAN)
                }
        ).register(plugin);

        new Baler(SupServItems.SUPSERV_CATEGORY, SupServItems.BALER, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        new ItemStack(Material.OAK_PLANKS), SlimefunItems.ELECTRO_MAGNET, new ItemStack(Material.OAK_PLANKS),
                        new ItemStack(Material.PISTON), SlimefunItems.CRAFTING_MOTOR, new ItemStack(Material.PISTON),
                        new ItemStack(Material.OAK_PLANKS), SlimefunItems.ELECTRIC_MOTOR, new ItemStack(Material.OAK_PLANKS)
                }
        ).register(plugin);

        new EndermanTether(SupServItems.SUPSERV_CATEGORY, SupServItems.ENDERMAN_TETHER, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        SlimefunItems.ENDER_RUNE, new ItemStack(Material.CRYING_OBSIDIAN), SlimefunItems.ENDER_RUNE,
                        SlimefunItems.ENDER_LUMP_3, SlimefunItems.INFUSED_MAGNET, SlimefunItems.ENDER_LUMP_3,
                        SlimefunItems.ENDER_RUNE, new ItemStack(Material.CRYING_OBSIDIAN), SlimefunItems.ENDER_RUNE
                }
        ).register(plugin);

        new HeadGrinder(SupServItems.SUPSERV_CATEGORY, SupServItems.HEAD_GRINDER, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        new ItemStack(Material.SMOOTH_STONE_SLAB), new ItemStack(Material.STONECUTTER), new ItemStack(Material.SMOOTH_STONE_SLAB),
                        new ItemStack(Material.PISTON), SlimefunItems.ELECTRIC_MOTOR, new ItemStack(Material.PISTON),
                        new ItemStack(Material.IRON_BLOCK), SlimefunItems.MEDIUM_CAPACITOR, new ItemStack(Material.IRON_BLOCK)
                }
        ).register(plugin);

        new Voltmeter(SupServItems.SUPSERV_CATEGORY, SupServItems.VOLTMETER, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        SlimefunItems.COPPER_WIRE, SlimefunItems.COPPER_WIRE, SlimefunItems.COPPER_WIRE,
                        SlimefunItems.COPPER_WIRE, new ItemStack(Material.CLOCK), SlimefunItems.COPPER_WIRE,
                        SlimefunItems.COPPER_WIRE, SlimefunItems.COPPER_WIRE, SlimefunItems.COPPER_WIRE
                }
        ).register(plugin);

        new SlimefunItem(SupServItems.SUPSERV_CATEGORY, SupServItems.COMPRESSED_COBBLESTONE,
                RecipeType.COMPRESSOR, new ItemStack[] {
                new ItemStack(Material.COBBLESTONE, 9)
        }).register(plugin);

        new SlimefunItem(SupServItems.SUPSERV_CATEGORY, SupServItems.DOUBLE_COMPRESSED_COBBLESTONE,
                RecipeType.COMPRESSOR, new ItemStack[] {
                new CustomItemStack(SupServItems.COMPRESSED_COBBLESTONE, 9)
        }).register(plugin);

        new SlimefunItem(SupServItems.SUPSERV_CATEGORY, SupServItems.TRIPLE_COMPRESSED_COBBLESTONE,
                RecipeType.COMPRESSOR, new ItemStack[] {
                new CustomItemStack(SupServItems.DOUBLE_COMPRESSED_COBBLESTONE, 9)
        }).register(plugin);

        new SlimefunItem(SupServItems.SUPSERV_CATEGORY, SupServItems.QUADRUPLE_COMPRESSED_COBBLESTONE,
                RecipeType.COMPRESSOR, new ItemStack[] {
                new CustomItemStack(SupServItems.TRIPLE_COMPRESSED_COBBLESTONE, 9)
        }).register(plugin);

        new SlimefunItem(SupServItems.SUPSERV_CATEGORY, SupServItems.QUINTUPLE_COMPRESSED_COBBLESTONE,
                RecipeType.COMPRESSOR, new ItemStack[] {
                new CustomItemStack(SupServItems.QUADRUPLE_COMPRESSED_COBBLESTONE, 9)
        }).register(plugin);

        new SlimefunItem(SupServItems.SUPSERV_CATEGORY, SupServItems.SEXTUPLE_COMPRESSED_COBBLESTONE,
                RecipeType.COMPRESSOR, new ItemStack[] {
                new CustomItemStack(SupServItems.QUINTUPLE_COMPRESSED_COBBLESTONE, 9)
        }).register(plugin);

        new SlimefunItem(SupServItems.SUPSERV_CATEGORY, SupServItems.SEPTUPLE_COMPRESSED_COBBLESTONE,
                RecipeType.COMPRESSOR, new ItemStack[] {
                new CustomItemStack(SupServItems.SEXTUPLE_COMPRESSED_COBBLESTONE, 9)
        }).register(plugin);

        new SlimefunItem(SupServItems.SUPSERV_CATEGORY, SupServItems.OCTUPLE_COMPRESSED_COBBLESTONE,
                RecipeType.COMPRESSOR, new ItemStack[] {
                new CustomItemStack(SupServItems.SEPTUPLE_COMPRESSED_COBBLESTONE, 9)
        }).register(plugin);

        // Grind Stone
        ((MultiBlockMachine) SlimefunItems.GRIND_STONE.getItem()).addRecipe(new ItemStack[] {
                        new SlimefunItemStack(SupServItems.COMPRESSED_COBBLESTONE, 1)},
                new ItemStack(Material.COBBLESTONE, 9)
        );

        ((MultiBlockMachine) SlimefunItems.GRIND_STONE.getItem()).addRecipe(new ItemStack[] {
                        new SlimefunItemStack(SupServItems.DOUBLE_COMPRESSED_COBBLESTONE, 1)},
                new SlimefunItemStack(SupServItems.COMPRESSED_COBBLESTONE, 9)
        );

        ((MultiBlockMachine) SlimefunItems.GRIND_STONE.getItem()).addRecipe(new ItemStack[] {
                        new SlimefunItemStack(SupServItems.TRIPLE_COMPRESSED_COBBLESTONE, 1)},
                new SlimefunItemStack(SupServItems.DOUBLE_COMPRESSED_COBBLESTONE, 9)
        );

        ((MultiBlockMachine) SlimefunItems.GRIND_STONE.getItem()).addRecipe(new ItemStack[] {
                        new SlimefunItemStack(SupServItems.QUADRUPLE_COMPRESSED_COBBLESTONE, 1)},
                new SlimefunItemStack(SupServItems.TRIPLE_COMPRESSED_COBBLESTONE, 9)
        );

        ((MultiBlockMachine) SlimefunItems.GRIND_STONE.getItem()).addRecipe(new ItemStack[] {
                        new SlimefunItemStack(SupServItems.QUINTUPLE_COMPRESSED_COBBLESTONE, 1)},
                new SlimefunItemStack(SupServItems.QUADRUPLE_COMPRESSED_COBBLESTONE, 9)
        );

        ((MultiBlockMachine) SlimefunItems.GRIND_STONE.getItem()).addRecipe(new ItemStack[] {
                        new SlimefunItemStack(SupServItems.SEXTUPLE_COMPRESSED_COBBLESTONE, 1)},
                new SlimefunItemStack(SupServItems.QUINTUPLE_COMPRESSED_COBBLESTONE, 9)
        );

        ((MultiBlockMachine) SlimefunItems.GRIND_STONE.getItem()).addRecipe(new ItemStack[] {
                        new SlimefunItemStack(SupServItems.SEPTUPLE_COMPRESSED_COBBLESTONE, 1)},
                new SlimefunItemStack(SupServItems.SEXTUPLE_COMPRESSED_COBBLESTONE, 9)
        );

        ((MultiBlockMachine) SlimefunItems.GRIND_STONE.getItem()).addRecipe(new ItemStack[] {
                        new SlimefunItemStack(SupServItems.OCTUPLE_COMPRESSED_COBBLESTONE, 1)},
                new SlimefunItemStack(SupServItems.SEPTUPLE_COMPRESSED_COBBLESTONE, 9)
        );

        // Compressor
        ((MultiBlockMachine) SlimefunItems.COMPRESSOR.getItem()).addRecipe(new ItemStack[] {
                        new ItemStack(Material.COBBLESTONE, 9)},
                new SlimefunItemStack(SupServItems.COMPRESSED_COBBLESTONE, 1)
        );

        ((MultiBlockMachine) SlimefunItems.COMPRESSOR.getItem()).addRecipe(new ItemStack[] {
                        new SlimefunItemStack(SupServItems.COMPRESSED_COBBLESTONE, 9)},
                new SlimefunItemStack(SupServItems.DOUBLE_COMPRESSED_COBBLESTONE, 1)
        );

        ((MultiBlockMachine) SlimefunItems.COMPRESSOR.getItem()).addRecipe(new ItemStack[] {
                        new SlimefunItemStack(SupServItems.DOUBLE_COMPRESSED_COBBLESTONE, 9)},
                new SlimefunItemStack(SupServItems.TRIPLE_COMPRESSED_COBBLESTONE, 1)
        );

        ((MultiBlockMachine) SlimefunItems.COMPRESSOR.getItem()).addRecipe(new ItemStack[] {
                        new SlimefunItemStack(SupServItems.TRIPLE_COMPRESSED_COBBLESTONE, 9)},
                new SlimefunItemStack(SupServItems.QUADRUPLE_COMPRESSED_COBBLESTONE, 1)
        );

        ((MultiBlockMachine) SlimefunItems.COMPRESSOR.getItem()).addRecipe(new ItemStack[] {
                        new SlimefunItemStack(SupServItems.QUADRUPLE_COMPRESSED_COBBLESTONE, 9)},
                new SlimefunItemStack(SupServItems.QUINTUPLE_COMPRESSED_COBBLESTONE, 1)
        );

        ((MultiBlockMachine) SlimefunItems.COMPRESSOR.getItem()).addRecipe(new ItemStack[] {
                        new SlimefunItemStack(SupServItems.QUINTUPLE_COMPRESSED_COBBLESTONE, 9)},
                new SlimefunItemStack(SupServItems.SEXTUPLE_COMPRESSED_COBBLESTONE, 1)
        );

        ((MultiBlockMachine) SlimefunItems.COMPRESSOR.getItem()).addRecipe(new ItemStack[] {
                        new SlimefunItemStack(SupServItems.SEXTUPLE_COMPRESSED_COBBLESTONE, 9)},
                new SlimefunItemStack(SupServItems.SEPTUPLE_COMPRESSED_COBBLESTONE, 1)
        );

        ((MultiBlockMachine) SlimefunItems.COMPRESSOR.getItem()).addRecipe(new ItemStack[] {
                        new SlimefunItemStack(SupServItems.SEPTUPLE_COMPRESSED_COBBLESTONE, 9)},
                new SlimefunItemStack(SupServItems.OCTUPLE_COMPRESSED_COBBLESTONE, 1)
        );

        new WitherProofBlock(SupServItems.SUPSERV_CATEGORY, SupServItems.WITHER_PROOF_SEA_LANTERN,
                RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.WITHER_PROOF_GLASS, SlimefunItems.WITHER_PROOF_OBSIDIAN,
                SlimefunItems.WITHER_PROOF_GLASS, new ItemStack(Material.SEA_LANTERN), SlimefunItems.WITHER_PROOF_GLASS,
                SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.WITHER_PROOF_GLASS, SlimefunItems.WITHER_PROOF_OBSIDIAN,
        },  new SlimefunItemStack(SupServItems.WITHER_PROOF_SEA_LANTERN, 16)
        ).register(plugin);

        new WitherProofBlock(SupServItems.SUPSERV_CATEGORY, SupServItems.WITHER_PROOF_GLOWSTONE,
                RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.WITHER_PROOF_GLASS, SlimefunItems.WITHER_PROOF_OBSIDIAN,
                SlimefunItems.WITHER_PROOF_GLASS, new ItemStack(Material.GLOWSTONE), SlimefunItems.WITHER_PROOF_GLASS,
                SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.WITHER_PROOF_GLASS, SlimefunItems.WITHER_PROOF_OBSIDIAN,
        },  new SlimefunItemStack(SupServItems.WITHER_PROOF_GLOWSTONE, 16)
        ).register(plugin);

        new WitherProofBlock(SupServItems.SUPSERV_CATEGORY, SupServItems.WITHER_PROOF_SHROOMLIGHT,
                RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.WITHER_PROOF_GLASS, SlimefunItems.WITHER_PROOF_OBSIDIAN,
                SlimefunItems.WITHER_PROOF_GLASS, new ItemStack(Material.SHROOMLIGHT), SlimefunItems.WITHER_PROOF_GLASS,
                SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.WITHER_PROOF_GLASS, SlimefunItems.WITHER_PROOF_OBSIDIAN,
        },  new SlimefunItemStack(SupServItems.WITHER_PROOF_SHROOMLIGHT, 16)
        ).register(plugin);

        new WitherProofBlock(SupServItems.SUPSERV_CATEGORY, SupServItems.WITHER_PROOF_PEARLESCENT_FROGLIGHT,
                RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.WITHER_PROOF_GLASS, SlimefunItems.WITHER_PROOF_OBSIDIAN,
                SlimefunItems.WITHER_PROOF_GLASS, new ItemStack(Material.PEARLESCENT_FROGLIGHT), SlimefunItems.WITHER_PROOF_GLASS,
                SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.WITHER_PROOF_GLASS, SlimefunItems.WITHER_PROOF_OBSIDIAN,
        },  new SlimefunItemStack(SupServItems.WITHER_PROOF_PEARLESCENT_FROGLIGHT, 16)
        ).register(plugin);

        new WitherProofBlock(SupServItems.SUPSERV_CATEGORY, SupServItems.WITHER_PROOF_VERDANT_FROGLIGHT,
                RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.WITHER_PROOF_GLASS, SlimefunItems.WITHER_PROOF_OBSIDIAN,
                SlimefunItems.WITHER_PROOF_GLASS, new ItemStack(Material.VERDANT_FROGLIGHT), SlimefunItems.WITHER_PROOF_GLASS,
                SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.WITHER_PROOF_GLASS, SlimefunItems.WITHER_PROOF_OBSIDIAN,
        },  new SlimefunItemStack(SupServItems.WITHER_PROOF_VERDANT_FROGLIGHT, 16)
        ).register(plugin);

        new WitherProofBlock(SupServItems.SUPSERV_CATEGORY, SupServItems.WITHER_PROOF_OCHRE_FROGLIGHT,
                RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.WITHER_PROOF_GLASS, SlimefunItems.WITHER_PROOF_OBSIDIAN,
                SlimefunItems.WITHER_PROOF_GLASS, new ItemStack(Material.OCHRE_FROGLIGHT), SlimefunItems.WITHER_PROOF_GLASS,
                SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.WITHER_PROOF_GLASS, SlimefunItems.WITHER_PROOF_OBSIDIAN,
        },  new SlimefunItemStack(SupServItems.WITHER_PROOF_OCHRE_FROGLIGHT, 16)
        ).register(plugin);
    }

}
