package unsa.tvs.com.screen.custom;

import unsa.tvs.com.screen.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class LifePodMenu extends AbstractContainerMenu {
    private static final int POD_SLOTS = 9;
    private final Container podInventory;
    private final ContainerLevelAccess access;

    public LifePodMenu(int containerId, Inventory playerInventory) {
        this(containerId, playerInventory, new SimpleContainer(POD_SLOTS), ContainerLevelAccess.NULL);
    }

    public LifePodMenu(int containerId, Inventory playerInventory, FriendlyByteBuf extraData) {
        this(containerId, playerInventory, new SimpleContainer(POD_SLOTS), ContainerLevelAccess.NULL);
    }

    public LifePodMenu(int containerId, Inventory playerInventory, Container podInventory, ContainerLevelAccess access) {
        super(ModMenuTypes.LIFE_POD_MENU.get(), containerId);
        this.podInventory = podInventory;
        this.access = access;

        for (int i = 0; i < POD_SLOTS; i++) {
            int x = 44 + (i % 3) * 18;
            int y = 18 + (i / 3) * 18;
            this.addSlot(new Slot(podInventory, i, x, y) {
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return false;
                }
            });
        }

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }

        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
        Slot slot = this.slots.get(slotIndex);
        if (!slot.hasItem()) {
            return ItemStack.EMPTY;
        }
        ItemStack stack = slot.getItem();
        ItemStack result = stack.copy();

        if (slotIndex < POD_SLOTS) {
            if (!this.moveItemStackTo(stack, POD_SLOTS, POD_SLOTS + 36, true)) {
                return ItemStack.EMPTY;
            }
        } else {
            return ItemStack.EMPTY;
        }

        if (stack.isEmpty()) {
            slot.set(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }
        return result;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.access.evaluate((level, pos) -> player.distanceToSqr(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5) <= 64.0, true);
    }
}
