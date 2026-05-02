package unsa.tvs.com.world;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

/**
 * 处理太阳熄灭的倒计时和效果
 */
public class SunExtinctionHandler {

    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post event) {
        if (event.getLevel() instanceof ServerLevel serverLevel 
            && serverLevel.dimension() == Level.OVERWORLD) {
            
            SunTimerData data = SunTimerData.get(serverLevel);
            
            // 每天黎明时检查一次 (time % 24000 == 0)
            if (serverLevel.getDayTime() % 24000 == 0) {
                data.tick();
                int remainingDays = data.getRemainingDays();
                
                if (!data.isSunExtinguished()) {
                    // 关键天数提示
                    if (remainingDays == 280) {
                        broadcastToAllPlayers(serverLevel, 
                            "§c[警告] §f检测到太阳异常，剩余时间: §e" + remainingDays + " 天");
                    } else if (remainingDays == 100) {
                        broadcastToAllPlayers(serverLevel, 
                            "§c[紧急] §f太阳能量正在快速衰减，剩余时间: §e" + remainingDays + " 天");
                    } else if (remainingDays == 50) {
                        broadcastToAllPlayers(serverLevel, 
                            "§4[危急] §f太阳即将熄灭，剩余时间: §c" + remainingDays + " 天");
                    } else if (remainingDays == 10) {
                        broadcastToAllPlayers(serverLevel, 
                            "§4[最后阶段] §f太阳已出现明显萎缩，剩余时间: §c" + remainingDays + " 天");
                    }

                    // 最后一天
                    if (remainingDays == 0) {
                        triggerSunExtinction(serverLevel, data);
                    }
                }
            }
            
            // 太阳熄灭后的持续效果
            if (data.isSunExtinguished()) {
                applySunExtinctionEffects(serverLevel);
            }
        }
    }

    private static void triggerSunExtinction(ServerLevel level, SunTimerData data) {
        data.setSunExtinguished();
        broadcastToAllPlayers(level, 
            "§4§l太阳已经彻底熄灭，世界陷入永恒的黑暗...");
        
        // 设置永久夜晚
        level.setDayTime(18000); // 午夜
        
        // 可以触发暴雨、暴雪等天气效果
        level.setWeatherParameters(24000, 0, true, true);
    }

    private static void applySunExtinctionEffects(ServerLevel level) {
        // 对玩家施加缓慢和虚弱效果
        for (Player player : level.players()) {
            if (player.isAlive() && !player.isCreative() && !player.isSpectator()) {
                // 在地表会受到伤害
                if (player.level().canSeeSky(player.blockPosition())) {
                    player.addEffect(new MobEffectInstance(
                        MobEffects.WITHER, 200, 0, false, false));
                    player.addEffect(new MobEffectInstance(
                        MobEffects.MOVEMENT_SLOWDOWN, 200, 1, false, false));
                }
            }
        }
        
        // 可以在这里添加怪物生成逻辑
    }

    private static void broadcastToAllPlayers(ServerLevel level, String message) {
        for (Player player : level.players()) {
            player.sendSystemMessage(Component.literal(message));
        }
    }
}
