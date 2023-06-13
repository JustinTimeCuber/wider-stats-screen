package barker.justin.wss.mixin;

import barker.justin.wss.Main;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraft.client.gui.screen.StatsScreen$ItemStatsListWidget")
public class ItemStatsListWidgetMixin {
	@ModifyConstant(method = "getRowWidth()I", constant = @Constant(intValue = 375))
	private int injectRowWidth(int value) {
		return (int)(75 + 300*Main.scale);
	}
	@ModifyConstant(method = "getScrollbarPositionX()I", constant = @Constant(intValue = 140))
	private int injectScrollbarPos(int value) {
		return (int)(140*Main.scale);
	}
	@ModifyConstant(method = "clickedHeader(II)V", constant = @Constant(intValue = -1))
	private int injectDefaultStatType(int value) {
		return Main.injectClick ? Main.defaultCategory : value;
	}
	@ModifyConstant(method = "clickedHeader(II)V", constant = @Constant(intValue = 0, ordinal = 0))
	private int injectSkipForLoop(int value) {
		return Main.injectClick ? Integer.MAX_VALUE : value;
	}
	@Inject(method = "clickedHeader(II)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/sound/SoundManager;play(Lnet/minecraft/client/sound/SoundInstance;)V"), cancellable = true)
	private void injectCancelSound(CallbackInfo ci) {
		if(Main.injectClick) {
			ci.cancel();
		}
	}
}
