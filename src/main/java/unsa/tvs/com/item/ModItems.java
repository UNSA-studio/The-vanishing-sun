package unsa.tvs.com.item;

import unsa.tvs.com.TheVanishingSun;
import unsa.tvs.com.item.custom.BiologicalJarItem;
import unsa.tvs.com.item.custom.LocatorItem;
import unsa.tvs.com.item.custom.RadarItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TheVanishingSun.MODID);

    public static final DeferredItem<Item> BLUEPRINT = ITEMS.register("blueprint",
            () -> new Item(new Item.Properties()
                    .stacksTo(64)
                    .rarity(Rarity.EPIC)));

    public static final DeferredItem<Item> RADAR = ITEMS.register("radar",
            () -> new RadarItem(new Item.Properties()
                    .stacksTo(64)
                    .rarity(Rarity.UNCOMMON)));

    public static final DeferredItem<Item> LOCATOR = ITEMS.register("locator",
            () -> new LocatorItem(new Item.Properties()
                    .stacksTo(1)
                    .durability(16)
                    .rarity(Rarity.RARE)));

    public static final DeferredItem<Item> BIOLOGICAL_JAR = ITEMS.register("biological_jar",
            () -> new BiologicalJarItem(new Item.Properties()
                    .stacksTo(64)
                    .rarity(Rarity.EPIC)));
}
