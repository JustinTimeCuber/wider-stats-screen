package barker.justin.wss.mixin;

import barker.justin.wss.Main;
import net.minecraft.client.gui.widget.AlwaysSelectedEntryListWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.gui.screen.StatsScreen.class)
public class StatsScreenMixin {
	@ModifyConstant(method = "getColumnX(I)I", constant = @Constant(intValue = 115))
	private int injected1(int value) {
		return (int)(75 + 40*Main.scale);
	}
	@ModifyConstant(method = "getColumnX(I)I", constant = @Constant(intValue = 40))
	private int injected2(int value) {
		return (int)(40*Main.scale);
	}
	@Inject(method = "selectStatList(Lnet/minecraft/client/gui/widget/AlwaysSelectedEntryListWidget;)V", at = @At("HEAD"))
	private void injected3(AlwaysSelectedEntryListWidget<?> list, CallbackInfo ci) {
		Main.loadConfig();
	}
}
