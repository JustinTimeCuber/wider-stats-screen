package barker.justin.wss.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(targets = "net.minecraft.client.gui.screen.StatsScreen$ItemStatsListWidget")
public interface ItemStatsListInvokerMixin {
    @Invoker
    void callClickedHeader(int x, int y);
}
