package com.sindercube.colosseum.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Blocks.class)
public class BlocksMixin {

    @ModifyArg(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecra" +
                            "ft/block/FlowerBlock;<init>(Lnet/minecraft/entity/effect/StatusEffect;ILnet/minecraft/block/AbstractBlock$Settings;)V"),
            slice = @Slice(
                    from = @At(value = "CONSTANT", args = "stringValue=torchflower"),
                    to = @At(value = "CONSTANT", args = "stringValue=poppy")
            ),
            index = 2
    )
    private static AbstractBlock.Settings torchflowerGlow(AbstractBlock.Settings settings) {
        return settings.luminance(n -> 14);
    }

}
