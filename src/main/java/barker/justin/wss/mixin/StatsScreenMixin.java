package barker.justin.wss.mixin;

import barker.justin.wss.Main;
import net.minecraft.client.gui.widget.AlwaysSelectedEntryListWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.gui.screen.StatsScreen.class)
public class StatsScreenMixin {
	@Inject(method = "selectStatList(Lnet/minecraft/client/gui/widget/AlwaysSelectedEntryListWidget;)V", at = @At("HEAD"))
	private void injectSelectStatList(AlwaysSelectedEntryListWidget<?> list, CallbackInfo ci) {
		String listClassName = list.getClass().getSimpleName();
		// This is not a great way of doing this, but it works.
		// Two separate checks for Yarn and intermediary mappings, so it works in dev environment as well as normal use.
		if(listClassName.equals("ItemStatsListWidget") || listClassName.equals("class_4200")) {
			Main.loadConfig();
			Main.injectClick = true;
			((ItemStatsListWidgetAccessor)list).callSelect(0, 0);
			Main.injectClick = false;
		}
	}
}
