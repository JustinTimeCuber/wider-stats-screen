package barker.justin.wss.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(targets = "net.minecraft.client.gui.screen.StatsScreen$ItemStatsListWidget")
public class ItemStatsListWidgetMixin {
	@ModifyConstant(method = "getRowWidth()I", constant = @Constant(intValue = 375))
	private int injected1(int value) {
		return 675;
	}
	@ModifyConstant(method = "getScrollbarPositionX()I", constant = @Constant(intValue = 140))
	private int injected2(int value) {
		return 280;
	}
}
