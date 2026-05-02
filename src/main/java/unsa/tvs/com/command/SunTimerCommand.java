package unsa.tvs.com.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import unsa.tvs.com.world.SunTimerData;

/**
 * 太阳计时器命令
 * /suntimer check - 查看剩余天数
 * /suntimer reset [天数] - 重置倒计时
 */
public class SunTimerCommand {
    
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
            Commands.literal("suntimer")
                .requires(source -> source.hasPermission(2))
                .then(Commands.literal("check")
                    .executes(context -> {
                        ServerLevel level = context.getSource().getLevel();
                        SunTimerData data = SunTimerData.get(level);
                        
                        int remaining = data.getRemainingDays();
                        boolean extinguished = data.isSunExtinguished();
                        
                        if (extinguished) {
                            context.getSource().sendSuccess(
                                () -> Component.literal("§4太阳已经熄灭"), false);
                        } else {
                            context.getSource().sendSuccess(
                                () -> Component.literal("§e距离太阳熄灭还有: §c" + remaining + " §e天"), false);
                        }
                        return 1;
                    })
                )
                .then(Commands.literal("reset")
                    .then(Commands.argument("days", IntegerArgumentType.integer(1, 280))
                        .executes(context -> {
                            int days = IntegerArgumentType.getInteger(context, "days");
                            ServerLevel level = context.getSource().getLevel();
                            
                            // 重置计时器（需要重新创建数据）
                            SunTimerData data = SunTimerData.get(level);
                            level.getDataStorage().set("the_vanishing_sun_timer", data);
                            
                            context.getSource().sendSuccess(
                                () -> Component.literal("§a倒计时已重置为 " + days + " 天"), true);
                            return 1;
                        })
                    )
                )
        );
    }
}
