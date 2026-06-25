package barker.justin.wss.mixin;

import barker.justin.wss.Main;
import net.minecraft.stats.StatType;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.NoSuchElementException;

@Mixin(targets = "net.minecraft.client.gui.screens.achievement.StatsScreen$ItemStatisticsList")
public class ItemStatisticsListMixin {
	@ModifyConstant(method = "getColumnX(I)I", constant = @Constant(intValue = 40))
	private int injectColumnSpacing(int value) {
		return (int)(40*Main.scale);
	}
	@ModifyConstant(method = "getRowWidth()I", constant = @Constant(intValue = 280))
	private int injectRowWidth(int value) {
		return (int)(280*Main.scale);
	}
	@Inject(method = "<init>", at = @At("TAIL"))
	private void injectAfterConstructor(CallbackInfo ci) {
		Main.loadConfig();
		if(Main.defaultCategory >= 0) {
			try {
				StatType<?> st = ((ItemStatisticsListAccessor) this).callGetColumn(Main.defaultCategory);
				((ItemStatisticsListAccessor) this).callSortByColumn(st);
			} catch (NoSuchElementException ignored) {
				// If there are no stats yet, then sorting will fail, but we can just ignore the error
			}
		}
	}
}
