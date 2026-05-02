package unsa.tvs.com.world;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;

public class SunTimerData extends SavedData {
    private static final String DATA_NAME = "the_vanishing_sun_timer";
    private static final int TOTAL_DAYS = 280;
    private long elapsedTicks = 0;
    private boolean sunExtinguished = false;

    public static SunTimerData load(CompoundTag tag) {
        SunTimerData data = new SunTimerData();
        data.elapsedTicks = tag.getLong("ElapsedTicks");
        data.sunExtinguished = tag.getBoolean("SunExtinguished");
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.putLong("ElapsedTicks", this.elapsedTicks);
        tag.putBoolean("SunExtinguished", this.sunExtinguished);
        return tag;
    }

    public static SunTimerData get(Level level) {
        if (level instanceof ServerLevel serverLevel) {
            return serverLevel.getDataStorage()
                .computeIfAbsent(SunTimerData::load, SunTimerData::new, DATA_NAME);
        }
        throw new RuntimeException("Level is not a ServerLevel");
    }

    public void tick() {
        if (!sunExtinguished) {
            elapsedTicks++;
            setDirty();
        }
    }

    public int getRemainingDays() {
        long remainingTicks = (TOTAL_DAYS * 24000L) - elapsedTicks;
        return (int) Math.max(0, remainingTicks / 24000L);
    }

    public int getTotalDays() { return TOTAL_DAYS; }
    public long getElapsedTicks() { return elapsedTicks; }
    public boolean isSunExtinguished() { return sunExtinguished; }

    public void setSunExtinguished() {
        this.sunExtinguished = true;
        setDirty();
    }

    public double getBrightnessFactor() {
        // 从 1.0 (正常) 线性降到 0.0 (全黑)
        long totalTicks = TOTAL_DAYS * 24000L;
        return Math.max(0.0, 1.0 - (double) elapsedTicks / totalTicks);
    }
}
