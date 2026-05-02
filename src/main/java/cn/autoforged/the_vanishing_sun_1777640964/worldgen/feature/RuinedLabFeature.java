package cn.autoforged.the_vanishing_sun_1777640964.worldgen.feature;

import cn.autoforged.the_vanishing_sun_1777640964.TheVanishingSun;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.storage.loot.LootTable;

public class RuinedLabFeature extends Feature<NoneFeatureConfiguration> {

    private static final BlockState[] FLOOR_BLOCKS = {
        Blocks.STONE_BRICKS.defaultBlockState(),
        Blocks.CRACKED_STONE_BRICKS.defaultBlockState(),
        Blocks.MOSSY_STONE_BRICKS.defaultBlockState(),
        Blocks.STONE_BRICKS.defaultBlockState()
    };

    private static final BlockState[] WALL_BLOCKS = {
        Blocks.STONE_BRICKS.defaultBlockState(),
        Blocks.STONE_BRICK_STAIRS.defaultBlockState(),
        Blocks.CRACKED_STONE_BRICKS.defaultBlockState()
    };

    public RuinedLabFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        BlockPos surfacePos = findSurface(level, origin);
        if (surfacePos == null) return false;

        BlockPos startPos = surfacePos.offset(-2, -1, -2);

        // Place floor (5x5 platform)
        for (int x = 0; x < 5; x++) {
            for (int z = 0; z < 5; z++) {
                BlockPos pos = startPos.offset(x, 0, z);
                if (level.getBlockState(pos).isAir() || level.getBlockState(pos).canBeReplaced()) {
                    level.setBlock(pos, FLOOR_BLOCKS[random.nextInt(FLOOR_BLOCKS.length)], 3);
                }
            }
        }

        // Place walls on 3 sides (north, east, west) - 2 blocks high
        for (int h = 1; h <= 2; h++) {
            // North wall (z = 0)
            for (int x = 0; x < 5; x++) {
                if (random.nextFloat() < 0.85f) {
                    BlockPos pos = startPos.offset(x, h, 0);
                    if (level.getBlockState(pos).isAir()) {
                        level.setBlock(pos, WALL_BLOCKS[random.nextInt(WALL_BLOCKS.length)], 3);
                    }
                }
            }
            // West wall (x = 0)
            for (int z = 1; z < 5; z++) {
                if (random.nextFloat() < 0.85f) {
                    BlockPos pos = startPos.offset(0, h, z);
                    if (level.getBlockState(pos).isAir()) {
                        level.setBlock(pos, WALL_BLOCKS[random.nextInt(WALL_BLOCKS.length)], 3);
                    }
                }
            }
            // East wall (x = 4)
            for (int z = 1; z < 5; z++) {
                if (random.nextFloat() < 0.85f) {
                    BlockPos pos = startPos.offset(4, h, z);
                    if (level.getBlockState(pos).isAir()) {
                        level.setBlock(pos, WALL_BLOCKS[random.nextInt(WALL_BLOCKS.length)], 3);
                    }
                }
            }
        }

        // Place chest in center
        BlockPos chestPos = startPos.offset(2, 1, 2);
        if (level.getBlockState(chestPos).isAir()) {
            level.setBlock(chestPos, Blocks.CHEST.defaultBlockState(), 3);
            if (level.getBlockEntity(chestPos) instanceof ChestBlockEntity chest) {
                chest.setLootTable(ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(TheVanishingSun.MODID, "chests/ruined_lab")));
                chest.setLootTableSeed(random.nextLong());
            }
        }

        // Place some cobwebs
        for (int i = 0; i < 3; i++) {
            BlockPos webPos = startPos.offset(random.nextInt(5), random.nextInt(3) + 1, random.nextInt(5));
            if (level.getBlockState(webPos).isAir()) {
                level.setBlock(webPos, Blocks.COBWEB.defaultBlockState(), 3);
            }
        }

        return true;
    }

    private BlockPos findSurface(WorldGenLevel level, BlockPos start) {
        BlockPos.MutableBlockPos pos = start.mutable();
        for (int y = start.getY(); y > level.getMinBuildHeight(); y--) {
            pos.setY(y);
            BlockState state = level.getBlockState(pos);
            if (state.isSolid() && level.getBlockState(pos.above()).isAir()) {
                return pos.immutable();
            }
        }
        return null;
    }
}
