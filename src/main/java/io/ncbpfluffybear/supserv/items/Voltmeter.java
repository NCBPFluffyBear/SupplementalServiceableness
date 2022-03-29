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
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AGenerator;
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

                if(generator instanceof SolarGenerator) {
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

                    int generatedOutput =  aGenerator.getEnergyProduction();

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

            double averageGeneration = (double) joulesPerSecGenerated / (numberOfGenerators == 0 ? 1 : numberOfGenerators);
            double averageConsumption = (double) joulesPerSecConsumed / (numberOfConsumers == 0 ? 1 : numberOfConsumers);
            double averageCapacitySize = (double) fullCapacity / (numberOfCapacitors + numberOfConsumers + numberOfGenerators);
            double averageChargeSize = (double) currentCharge / (numberOfCapacitors + numberOfConsumers + numberOfGenerators);
            double averageCapacitySizeOnlyCapacitors = (double) fullCapacityOnlyCapacitors / (numberOfCapacitors == 0 ? 1 : numberOfCapacitors);
            double averageChargeSizeOnlyCapacitors = (double) currentChargeOnlyCapacitors / (numberOfCapacitors == 0 ? 1 : numberOfCapacitors);

            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&5Consumption"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&a[Min] &f" + minConsumption + " J &e[Avg] &f"
                    + averageConsumption + " J &c[Max] &f" + maxConsumption + " J"));

            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&5Capacity"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&c[Min] &f" + minCapacity + " J &e[Avg] &f" + averageCapacitySize
                    + " J &a[Max] &f" + maxCapacity + " J"));

            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&5Capacity (capacitors only) "));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&c[Min] &f" + minCapacityOnlyCapacitors + " J &e[Avg] &f"
                            + averageCapacitySizeOnlyCapacitors + " J &a[Max] &f" + maxCapacityOnlyCapacitors + " J"));

            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&5Generation"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&c[Min] &f" + minGeneration + " J &e[Avg] &f" + averageGeneration
                            + " J &a[Max] &f" + maxGeneration + " J"));
        };
    }
}
