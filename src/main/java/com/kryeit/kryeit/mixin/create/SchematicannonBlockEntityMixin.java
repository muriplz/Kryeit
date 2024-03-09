package com.kryeit.kryeit.mixin.create;

import com.kryeit.kryeit.event.SchematicannonPlaceEvent;
import com.simibubi.create.content.schematics.SchematicPrinter;
import com.simibubi.create.content.schematics.cannon.SchematicannonBlockEntity;

import net.minecraft.block.entity.BlockEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = SchematicannonBlockEntity.class, priority = 1000001)
public class SchematicannonBlockEntityMixin {

	@Shadow(remap = false)
	public SchematicPrinter printer;
	@Shadow(remap = false)
	public String statusMsg;
	@Shadow(remap = false)
	private boolean blockSkipped;

	@Inject(method = "tickPrinter", at = @At(value = "INVOKE", shift = At.Shift.AFTER, target = "Lcom/simibubi/create/content/schematics/SchematicPrinter;shouldPlaceCurrent(Lnet/minecraft/world/World;Lcom/simibubi/create/content/schematics/SchematicPrinter$PlacementPredicate;)Z"), cancellable = true)
	public void onTickPrinter(CallbackInfo ci){
		BlockEntity blockEntity = (BlockEntity) (Object) this;
		if(!SchematicannonPlaceEvent.EVENT.invoker().onBlockPlaced(null, blockEntity, printer.getCurrentTarget())) {
			statusMsg = "searching";
			blockSkipped = true;
			ci.cancel();
		}
	}

}
