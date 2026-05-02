package cn.autoforged.the_vanishing_sun_1777640964.entity.custom;

import cn.autoforged.the_vanishing_sun_1777640964.TheVanishingSun;
import cn.autoforged.the_vanishing_sun_1777640964.screen.custom.LifePodMenu;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class LifePodEntity extends Entity implements MenuProvider {
    private static final int INVENTORY_SIZE = 9;
    private final SimpleContainer inventory = new SimpleContainer(INVENTORY_SIZE);
    private boolean landed = false;

    public LifePodEntity(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.landed && this.onGround()) {
            this.landed = true;
            this.setNoGravity(true);
            if (!this.level().isClientSide) {
                this.level().playSound(null, this.blockPosition(), SoundEvents.ANVIL_LAND, SoundSource.BLOCKS, 1.0f, 1.0f);
            }
        }
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        if (!this.level().isClientSide && player instanceof ServerPlayer serverPlayer) {
            serverPlayer.openMenu(this);
        }
        return InteractionResult.sidedSuccess(this.level().isClientSide);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("entity." + TheVanishingSun.MODID + ".life_pod");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new LifePodMenu(containerId, playerInventory, this.inventory, ContainerLevelAccess.create(this.level(), this.blockPosition()));
    }

    public void addStartingItems(ItemStack... stacks) {
        for (int i = 0; i < stacks.length && i < INVENTORY_SIZE; i++) {
            this.inventory.setItem(i, stacks[i].copy());
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        if (tag.contains("Inventory")) {
            this.inventory.fromTag(tag.getList("Inventory", ListTag.TAG_COMPOUND), this.level().registryAccess());
        }
        this.landed = tag.getBoolean("Landed");
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.put("Inventory", this.inventory.createTag(this.level().registryAccess()));
        tag.putBoolean("Landed", this.landed);
    }
}
