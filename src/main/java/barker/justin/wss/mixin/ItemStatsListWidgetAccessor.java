package barker.justin.wss.mixin;

import net.minecraft.stat.StatType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(targets = "net.minecraft.client.gui.screen.StatsScreen$ItemStatsListWidget")
public interface ItemStatsListWidgetAccessor {
    @Invoker("getStatType")
    StatType<?> callGetStatType(int headerColumn);
    @Invoker("selectStatType")
    void callSelectStatType(StatType<?> statType);
}
