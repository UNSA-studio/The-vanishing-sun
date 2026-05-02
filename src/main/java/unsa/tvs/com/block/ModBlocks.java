package unsa.tvs.com.block;

import unsa.tvs.com.TheVanishingSun;
import unsa.tvs.com.block.custom.BiologicalChamberBlock;
import unsa.tvs.com.block.custom.BlueprintParsingStationBlock;
import unsa.tvs.com.item.ModItems;
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
