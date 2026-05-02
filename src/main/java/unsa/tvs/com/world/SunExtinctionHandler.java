package unsa.tvs.com.world;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

public class SunExtinctionHandler {

    private static int lastWarnedDay = -1;

    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post event) {
        if (!(event.getLevel() instanceof ServerLevel serverLevel)
            || serverLevel.dimension() != Level.OVERWORLD) {
            return;
        }

        SunTimerData data = SunTimerData.get(serverLevel);

        // 太阳尚未熄灭，调整白天长度
        if (!data.isSunExtinguished()) {
            data.tick();

            double factor = data.getBrightnessFactor(); // 1.0 → 0.0
            int maxDayLength = (int) (12000 * factor);  // 正常白天 12000 tick

            long dayTime = serverLevel.getDayTime();
            long timeInDay = dayTime % 24000;

            // 如果处于白天 (0-11999) 且已超过应有时长，则强制进入黄昏
            if (timeInDay < 12000 && timeInDay > maxDayLength) {
                long baseDay = (dayTime / 24000L) * 24000L;
                serverLevel.setDayTime(baseDay + 12001L); // 黄昏开始
            }

            // 每天黎明时发送一次警告（或关键节点）
            if (timeInDay == 0) {
                int remaining = data.getRemainingDays();
                if (remaining != lastWarnedDay) {
                    lastWarnedDay = remaining;
                    if (remaining == 280 || remaining == 100 || remaining == 50 || remaining == 10) {
                        broadcastToAllPlayers(serverLevel, getWarningMessage(remaining));
                    }
                }

                // 最后一天白天长度为0，太阳实质熄灭
                if (remaining == 0) {
                    data.setSunExtinguished();
                    broadcastToAllPlayers(serverLevel, "§4§l太阳已经彻底熄灭，世界陷入永恒的黑暗...");
                    serverLevel.setWeatherParameters(24000, 0, true, true);
                }
            }
        } else {
            // 太阳熄灭后的持续效果
            applySunExtinctionEffects(serverLevel);
        }
    }

    private static String getWarningMessage(int remaining) {
        return switch (remaining) {
            case 280 -> "§c[警告] §f检测到太阳异常，剩余时间: §e280 天";
            case 100 -> "§c[紧急] §f太阳能量正在快速衰减，剩余时间: §e100 天";
            case 50  -> "§4[危急] §f太阳即将熄灭，剩余时间: §c50 天";
            case 10  -> "§4[最后阶段] §f太阳已明显萎缩，剩余时间: §c10 天";
            default  -> "§e太阳剩余时间: " + remaining + " 天";
        };
    }

    private static void applySunExtinctionEffects(ServerLevel level) {
        for (Player player : level.players()) {
            if (player.isAlive() && !player.isCreative() && !player.isSpectator()) {
                if (player.level().canSeeSky(player.blockPosition())) {
                    player.addEffect(new MobEffectInstance(MobEffects.WITHER, 200, 0, false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 1, false, false));
                }
            }
        }
    }

    private static void broadcastToAllPlayers(ServerLevel level, String message) {
        for (Player player : level.players()) {
            player.sendSystemMessage(Component.literal(message));
        }
    }
}
