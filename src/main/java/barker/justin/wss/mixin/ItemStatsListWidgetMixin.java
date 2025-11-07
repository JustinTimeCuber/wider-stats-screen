package barker.justin.wss.mixin;

import barker.justin.wss.Main;
import net.minecraft.stat.StatType;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.NoSuchElementException;

@Mixin(targets = "net.minecraft.client.gui.screen.StatsScreen$ItemStatsListWidget")
public class ItemStatsListWidgetMixin {
	@ModifyConstant(method = "getIconX(I)I", constant = @Constant(intValue = 40))
	private int injectColumnSpacing(int value) {
		return (int)(40*Main.scale);
	}
	@ModifyConstant(method = "getRowWidth()I", constant = @Constant(intValue = 280))
	private int injectRowWidth(int value) {
		return (int)(280*Main.scale);
	}
	@Inject(method = "<init>", at = @At("TAIL"))
	private void injectAfterConstructor(CallbackInfo ci) {
		if(Main.defaultCategory >= 0) {
			try {
				StatType<?> st = ((ItemStatsListWidgetAccessor) this).callGetStatType(Main.defaultCategory);
				((ItemStatsListWidgetAccessor) this).callSelectStatType(st);
			} catch (NoSuchElementException ignored) {
				// If there are no stats yet, then sorting will fail, but we can just ignore the error
			}
		}
	}
}
