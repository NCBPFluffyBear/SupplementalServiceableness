package io.ncbpfluffybear.supserv.items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNet;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.AbstractEnergyProvider;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.Capacitor;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.generators.SolarGenerator;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings({"unchecked", "unsafe"})
public class Voltmeter extends SimpleSlimefunItem<ItemUseHandler> {

    public Voltmeter(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            ItemStack item = e.getItem();
            Optional<Block> block = e.getClickedBlock();

            if (!isItem(item) || !block.isPresent()) {
                return;
            }

            Player player = e.getPlayer();
            EnergyNet energyNet = EnergyNet.getNetworkFromLocation(block.get().getLocation());

            if (Objects.isNull(energyNet)) {
                return;
            }

            Map<Location, EnergyNetProvider> generators = null;
            Map<Location, EnergyNetComponent> consumers = null;
            Map<Location, EnergyNetComponent> capacitors = null;

            try {
                Field generatorsField = energyNet.getClass().getDeclaredField("generators");
                Field consumersField = energyNet.getClass().getDeclaredField("consumers");
                Field capacitorsField = energyNet.getClass().getDeclaredField("capacitors");

                generatorsField.setAccessible(true);
                consumersField.setAccessible(true);
                capacitorsField.setAccessible(true);

                generators = (Map<Location, EnergyNetProvider>) generatorsField.get(energyNet);
                consumers = (Map<Location, EnergyNetComponent>) consumersField.get(energyNet);
                capacitors = (Map<Location, EnergyNetComponent>) capacitorsField.get(energyNet);

            } catch (NoSuchFieldException | IllegalAccessException ex) {
                ex.printStackTrace();
            }

            if (Objects.isNull(capacitors) || Objects.isNull(consumers) || Objects.isNull(generators)) {
                return;
            }

            int numberOfCapacitors = capacitors.size();
            int numberOfConsumers = consumers.size();
            int numberOfGenerators = generators.size();

            int fullCapacity = 0;
            int currentCharge = 0;
            int fullCapacityOnlyCapacitors = 0;
            int currentChargeOnlyCapacitors = 0;

            int maxCapacity = 0;
            int maxGeneration = 0;
            int maxConsumption = 0;

            EnergyNetComponent largestCapacitor = null;
            EnergyNetComponent largestConsumer = null;
            EnergyNetComponent largestGenerator = null;

            int minCapacity = Integer.MAX_VALUE;
            int minGeneration = Integer.MAX_VALUE;
            int minConsumption = Integer.MAX_VALUE;

            EnergyNetComponent smallestCapacitor = null;
            EnergyNetComponent smallestConsumer = null;
            EnergyNetComponent smallestGenerator = null;

            int joulesPerSecGenerated = 0;
            int joulesPerSecConsumed = 0;

            for (Location l : capacitors.keySet()) {
                Capacitor capacitor = (Capacitor) capacitors.get(l);

                int capacity = capacitor.getCapacity();
                int charge = capacitor.getCharge(l);

                fullCapacity += capacity;
                currentCharge += charge;
                fullCapacityOnlyCapacitors += capacity;
                currentChargeOnlyCapacitors += charge;

                if (capacity > maxCapacity) {
                    maxCapacity = capacity;
                    largestCapacitor = capacitor;
                }

                if (capacity < minCapacity) {
                    minCapacity = capacity;
                    smallestCapacitor = capacitor;
                }

            }

            int minCapacityOnlyCapacitors = minCapacity;
            int maxCapacityOnlyCapacitors = maxCapacity;

            for (Location l : consumers.keySet()) {
                AContainer consumer = (AContainer) consumers.get(l);

                int consumption = consumer.getEnergyConsumption();

                fullCapacity += consumer.getCapacity();
                currentCharge += consumer.getCharge(l);

                joulesPerSecConsumed += consumer.getEnergyConsumption();

                if (consumer.getCapacity() > maxCapacity) {
                    maxCapacity = consumer.getCapacity();
                }

                if (consumer.getCapacity() < minCapacity) {
                    minCapacity = consumer.getCapacity();
                }

                if (consumption > maxConsumption) {
                    maxConsumption = consumption;
                    largestConsumer = consumer;
                }

                if (consumption < minConsumption) {
                    minConsumption = consumption;
                    smallestConsumer = consumer;
                }
            }

            for (Location l : generators.keySet()) {

                EnergyNetProvider generator = generators.get(l);

                if (generator instanceof SolarGenerator) {
                    SolarGenerator sGenerator = (SolarGenerator) generator;

                    int generatedOutput = (sGenerator.getDayEnergy() + sGenerator.getNightEnergy()) / 2;

                    fullCapacity += sGenerator.getCapacity();
                    currentCharge += sGenerator.getCharge(l);

                    joulesPerSecGenerated += generatedOutput;

                    if (generatedOutput > maxGeneration) {
                        maxGeneration = generatedOutput;
                        largestGenerator = sGenerator;
                    }

                    if (generatedOutput < minGeneration) {
                        minGeneration = generatedOutput;
                        smallestGenerator = sGenerator;
                    }

                } else {
                    AbstractEnergyProvider aGenerator = (AbstractEnergyProvider) generator;

                    int generatedOutput = aGenerator.getEnergyProduction();

                    fullCapacity += aGenerator.getCapacity();
                    currentCharge += aGenerator.getCharge(l);

                    joulesPerSecGenerated += generatedOutput;

                    if (generatedOutput > maxGeneration) {
                        maxGeneration = generatedOutput;
                        largestGenerator = aGenerator;
                    }

                    if (generatedOutput < minGeneration) {
                        minGeneration = generatedOutput;
                        smallestGenerator = aGenerator;
                    }
                }
            }

            minGeneration = Objects.isNull(smallestGenerator) ? 0 : minGeneration;
            minConsumption = Objects.isNull(smallestConsumer) ? 0 : minConsumption;
            minCapacity = Objects.isNull(smallestCapacitor) && Objects.isNull(smallestConsumer) ? 0 : minCapacity;

            double averageGeneration = (double) joulesPerSecGenerated / (numberOfGenerators == 0 ? 1 : numberOfGenerators);
            double averageConsumption = (double) joulesPerSecConsumed / (numberOfConsumers == 0 ? 1 : numberOfConsumers);
            double averageCapacitySize = (double) fullCapacity / (numberOfCapacitors + numberOfConsumers + numberOfGenerators);
            double averageChargeSize = (double) currentCharge / (numberOfCapacitors + numberOfConsumers + numberOfGenerators);
            double averageCapacitySizeOnlyCapacitors = (double) fullCapacityOnlyCapacitors / (numberOfCapacitors == 0 ? 1 : numberOfCapacitors);
            double averageChargeSizeOnlyCapacitors = (double) currentChargeOnlyCapacitors / (numberOfCapacitors == 0 ? 1 : numberOfCapacitors);

            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&7============= Your Network Report ============="));

            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "\n&5&lConsumption"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&l--------------------"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&a[Min] &7" + minConsumption + " J/s &e[Avg] &7" + averageConsumption + " J/s &c[Max] &7" + maxConsumption + " J/s"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "\n&5Total consumption: &7" + joulesPerSecConsumed + " J/s"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&5Number of consumers: &7" + numberOfConsumers));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&5Your largest consumer: &7" + (Objects.isNull(largestConsumer) ? "None" : pretifyId(largestConsumer.getId()))));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&5Your smallest consumer: &7" + (Objects.isNull(smallestConsumer) ? "None" : pretifyId(smallestConsumer.getId()))));

            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "\n&5&lCapacity"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&l--------------------"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&c[Min] &7" + minCapacity + " J &e[Avg] &7" + averageCapacitySize + " J &a[Max] &7" + maxCapacity + " J"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "\n&5Total capacity: &7" + fullCapacity + " J"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&5Number of capacitors: &7" + (numberOfCapacitors + numberOfConsumers)));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&5Your largest capacitor: &7" + (Objects.isNull(largestCapacitor) ? "None" : pretifyId(largestCapacitor.getId()))));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&5Your smallest capacitor: &7" + (Objects.isNull(smallestCapacitor) ? "None" : pretifyId(smallestCapacitor.getId()))));

            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "\n&5&lCapacity &7&o(capacitors only) "));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&l--------------------"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&c[Min] &7" + minCapacityOnlyCapacitors + " J &e[Avg] &7" + averageCapacitySizeOnlyCapacitors + " J &a[Max] &7" + maxCapacityOnlyCapacitors + " J"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "\n&5Total capacity: &7" + fullCapacityOnlyCapacitors + " J"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&5Number of capacitors: &7" + numberOfCapacitors));

            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "\n&5&lCharge"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&l--------------------"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&e[Avg] &7" + averageChargeSize + " J"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&e[Avg] &7" + averageChargeSizeOnlyCapacitors + "J &7&o(capacitors only)"));

            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "\n&5&lGeneration"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&l--------------------"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&c[Min] &7" + minGeneration + " J/s &e[Avg] &7" + averageGeneration + " J/s &a[Max] &7" + maxGeneration + " J/s"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "\n&5Total generation: &7" + joulesPerSecGenerated + " J/s"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&5Number of generators: &7" + numberOfGenerators));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&5Your largest generator: &7" + (Objects.isNull(largestGenerator) ? "None" : pretifyId(largestGenerator.getId()))));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&5Your smallest generator: &7" + (Objects.isNull(smallestGenerator) ? "None" : pretifyId(smallestGenerator.getId()))));

            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "\n&7============================================="));
        };
    }

    private String pretifyId(String id) {
        char[] arr = id.replaceAll("_", " ").toLowerCase().toCharArray();
        boolean foundSpace = true;

        for (int i = 0; i < arr.length; i++) {
            if (Character.isLetter(arr[i])) {
                if (foundSpace) {
                    arr[i] = Character.toUpperCase(arr[i]);
                    foundSpace = false;
                }
            } else {
                foundSpace = true;
            }
        }

        return String.valueOf(arr);
    }
}
