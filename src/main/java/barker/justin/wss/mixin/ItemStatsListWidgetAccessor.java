package barker.justin.wss.mixin;

import net.minecraft.stat.StatType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(targets = "net.minecraft.client.gui.screen.StatsScreen$ItemStatsListWidget")
public interface ItemStatsListWidgetAccessor {
    @Invoker("select")
    boolean callSelect(int x, int y);
    @Accessor("listOrder")
    public void setListOrder(int order);
    @Accessor("selectedStatType")
    public void setSelectedStatType(StatType<?> type);
}
