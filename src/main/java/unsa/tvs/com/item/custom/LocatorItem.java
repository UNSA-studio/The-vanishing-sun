package unsa.tvs.com.item.custom;

import unsa.tvs.com.entity.ModEntities;
import unsa.tvs.com.entity.custom.LifePodEntity;
import unsa.tvs.com.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class LocatorItem extends Item {
    private static final int COOLDOWN_TICKS = 100;

    public LocatorItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide()) {
            if (player.getCooldowns().isOnCooldown(this)) {
                return InteractionResultHolder.fail(stack);
            }

            player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);
            stack.hurtAndBreak(1, player, player.getEquipmentSlotForItem(stack));

            LifePodEntity pod = new LifePodEntity(ModEntities.LIFE_POD.get(), level);
            double x = player.getX() + (level.random.nextDouble() - 0.5) * 2.0;
            double y = Math.min(player.getY() + 50, level.getMaxBuildHeight() - 10);
            double z = player.getZ() + (level.random.nextDouble() - 0.5) * 2.0;
            pod.setPos(x, y, z);
            pod.addStartingItems(new ItemStack(ModItems.BIOLOGICAL_JAR.get(), 3));
            level.addFreshEntity(pod);

            player.sendSystemMessage(Component.translatable("item.the_vanishing_sun.locator.use"));
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }
}
