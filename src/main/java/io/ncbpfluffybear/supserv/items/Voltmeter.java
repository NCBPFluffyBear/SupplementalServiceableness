package io.ncbpfluffybear.supserv.items;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNet;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.Capacitor;
import io.ncbpfluffybear.supserv.utils.Utils;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
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
        return new ItemUseHandler() {
            @Override
            public void onRightClick(PlayerRightClickEvent e) {
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

                } catch (NoSuchFieldException ex) {
                    ex.printStackTrace();
                } catch (IllegalAccessException ex) {
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

                int minCapacity = Integer.MAX_VALUE;
                int minGeneration = Integer.MAX_VALUE;
                int minConsumption = Integer.MAX_VALUE;

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
                    }

                    if (capacity < minCapacity) {
                        minCapacity = capacity;
                    }

                }

                for (Location l : consumers.keySet()) {
                    AContainer consumer = (AContainer) consumers.get(l);

                    int consumption = consumer.getEnergyConsumption();

                    fullCapacity += consumer.getCapacity();
                    currentCharge += consumer.getCharge(l);

                    joulesPerSecConsumed += consumer.getEnergyConsumption();

                    if (consumption > maxConsumption) {
                        maxConsumption = consumption;
                    }

                    if (consumption < minConsumption) {
                        minConsumption = consumption;
                    }
                }

                for (Location l : generators.keySet()) {
                    EnergyNetProvider generator = generators.get(l);

                    //int generatedOutput = generator.getGeneratedOutput(l, (Config) l.getBlock().getBlockData());

                    fullCapacity += generator.getCapacity();
                    currentCharge += generator.getCharge(l);

                    /*joulesPerSecGenerated += generatedOutput;

                    if (generatedOutput > maxGeneration) {
                        maxGeneration = generatedOutput;
                    }

                    if (generatedOutput < minGeneration) {
                        minGeneration = generatedOutput;
                    }*/
                }

                double averageGeneration = joulesPerSecGenerated / numberOfGenerators == 0 ? 1 : numberOfGenerators;
                double averageConsumption = joulesPerSecConsumed / numberOfConsumers == 0 ? 1 : numberOfConsumers;
                double averageCapacitySize = fullCapacity / ((numberOfCapacitors + numberOfConsumers + numberOfGenerators));
                double averageChargeSize = currentCharge / ((numberOfCapacitors + numberOfConsumers + numberOfGenerators));
                double averageCapacitySizeOnlyCapacitors = fullCapacityOnlyCapacitors / numberOfCapacitors == 0 ? 1 : numberOfCapacitors;
                double averageChargeSizeOnlyCapacitors = currentChargeOnlyCapacitors / numberOfCapacitors == 0 ? 1 : numberOfCapacitors;


                StringBuilder min = new StringBuilder();
                StringBuilder max = new StringBuilder();
                StringBuilder average = new StringBuilder();

                int tmp = fullCapacity;

                int offset = 0;

                while (tmp > 100) {
                    tmp /= 10;
                    offset++;
                }

                player.sendMessage("&5&lEnergy Network Statistics");

                offset = (int) Math.pow(10, offset);

                for (int i = 0; i < minConsumption / offset; i++) {
                    min.append("#");
                }
                for (int i = 0; i < averageConsumption / offset; i++) {
                    average.append("#");
                }

                for (int i = 0; i < maxConsumption / offset; i++) {
                    max.append("#");
                }

                player.sendMessage("+-------------+-----+ ");
                player.sendMessage("|             | MIN | " + min);
                player.sendMessage("| Consumption | AVG | " + average);
                player.sendMessage("|             | MAX | " + max);
                player.sendMessage("+-------------+-----+ ");
            }
        };
    }
}
