package cn.autoforged.the_vanishing_sun_1777640964.datagen;

import cn.autoforged.the_vanishing_sun_1777640964.TheVanishingSun;
import cn.autoforged.the_vanishing_sun_1777640964.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TheVanishingSun.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.BLUEPRINT.get());
        basicItem(ModItems.RADAR.get());
        basicItem(ModItems.LOCATOR.get());
        basicItem(ModItems.BIOLOGICAL_JAR.get());
    }
}
