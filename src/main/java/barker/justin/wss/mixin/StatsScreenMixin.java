package barker.justin.wss.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(targets = "net.minecraft.client.gui.screen.StatsScreen")
public class StatsScreenMixin {
	@ModifyConstant(method = "getColumnX()I", constant = @Constant(intValue = 115))
	private int injected1(int value) {
		return 155;
	}
	@ModifyConstant(method = "getColumnX()I", constant = @Constant(intValue = 40))
	private int injected2(int value) {
		return 80;
	}
}
