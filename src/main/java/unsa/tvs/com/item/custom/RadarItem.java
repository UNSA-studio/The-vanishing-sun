package unsa.tvs.com.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class RadarItem extends Item {
    private static final double DETECTION_RANGE = 50.0;

    public RadarItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide()) {
            var entities = level.getEntitiesOfClass(LivingEntity.class,
                    player.getBoundingBox().inflate(DETECTION_RANGE),
                    entity -> entity != player && entity.isAlive());

            player.sendSystemMessage(Component.translatable(
                    "item.the_vanishing_sun_1777640964.radar.detected",
                    entities.size(), (int) DETECTION_RANGE));
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }
}
