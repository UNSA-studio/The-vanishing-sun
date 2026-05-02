package cn.autoforged.the_vanishing_sun_1777640964.block;

import cn.autoforged.the_vanishing_sun_1777640964.TheVanishingSun;
import cn.autoforged.the_vanishing_sun_1777640964.block.custom.BiologicalChamberBlock;
import cn.autoforged.the_vanishing_sun_1777640964.block.custom.BlueprintParsingStationBlock;
import cn.autoforged.the_vanishing_sun_1777640964.item.ModItems;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TheVanishingSun.MODID);

    public static final DeferredBlock<Block> BLUEPRINT_PARSING_STATION = registerBlock("blueprint_parsing_station",
            () -> new BlueprintParsingStationBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)
                    .strength(3.0f, 6.0f)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> BIOLOGICAL_CHAMBER = registerBlock("biological_chamber",
            () -> new BiologicalChamberBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)
                    .strength(5.0f, 10.0f)
                    .requiresCorrectToolForDrops()));

    private static DeferredBlock<Block> registerBlock(String name, java.util.function.Supplier<Block> blockSupplier) {
        DeferredBlock<Block> deferredBlock = BLOCKS.register(name, blockSupplier);
        ModItems.ITEMS.registerSimpleBlockItem(deferredBlock);
        return deferredBlock;
    }
}
