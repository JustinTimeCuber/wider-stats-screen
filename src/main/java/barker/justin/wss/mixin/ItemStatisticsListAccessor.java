package barker.justin.wss.mixin;

import net.minecraft.stats.StatType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(targets = "net.minecraft.client.gui.screens.achievement.StatsScreen$ItemStatisticsList")
public interface ItemStatisticsListAccessor {
    @Invoker("getColumn")
    StatType<?> callGetColumn(int headerColumn);
    @Invoker("sortByColumn")
    void callSortByColumn(StatType<?> statType);
}
