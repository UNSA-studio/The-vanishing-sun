package cn.autoforged.the_vanishing_sun_1777640964.datagen;

import cn.autoforged.the_vanishing_sun_1777640964.TheVanishingSun;
import cn.autoforged.the_vanishing_sun_1777640964.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TheVanishingSun.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithItem(ModBlocks.BLUEPRINT_PARSING_STATION.get(),
                models().cubeBottomTop(
                        name(ModBlocks.BLUEPRINT_PARSING_STATION.get()),
                        modLoc("block/blueprint_parsing_station_side"),
                        modLoc("block/blueprint_parsing_station_bottom"),
                        modLoc("block/blueprint_parsing_station_top")
                ));

        simpleBlockWithItem(ModBlocks.BIOLOGICAL_CHAMBER.get(),
                models().cubeBottomTop(
                        name(ModBlocks.BIOLOGICAL_CHAMBER.get()),
                        modLoc("block/biological_chamber_side"),
                        modLoc("block/biological_chamber_bottom"),
                        modLoc("block/biological_chamber_top")
                ));
    }

    private String name(Block block) {
        return block.builtInRegistryHolder().key().location().getPath();
    }
}
