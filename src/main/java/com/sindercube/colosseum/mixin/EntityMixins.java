package com.sindercube.colosseum.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalFloatRef;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import com.sindercube.colosseum.content.effects.ModStatusEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.DamageTypeTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class EntityMixins {

    @Mixin(MobEntity.class)
    public static class MobEntityMixin {

        @Redirect(method = "tryAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"))
        private boolean knockbackInvincible(Entity target, DamageSource source, float damage, @Local(ordinal=1) LocalFloatRef knockback) {
            target.damage(source, damage);
            knockback.set(knockback.get()+1);
            if (target instanceof PlayerEntity player) player.velocityModified = true;
            return true;
        }

    }

    @Mixin(PlayerEntity.class)
    public static class PlayerEntityMixin {

        @Redirect(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"))
        private boolean knockbackInvincible(Entity target, DamageSource source, float damage, @Local(ordinal = 0) LocalIntRef knockback, @Local(ordinal = 0) boolean knocksBack) {
            boolean damaged = target.damage(source, damage);
            if (!damaged && knocksBack) knockback.set(knockback.get()+1);
            return true;
        }

    }

    @Mixin(LivingEntity.class)
    public static class LivingEntityMixin {

        @Shadow
        private boolean tryUseTotem(DamageSource source) { return true; }

        @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
        private void protectDefeated(DamageSource source, float damage, CallbackInfoReturnable<Boolean> cir) {
            LivingEntity self = (LivingEntity)(Object)this;
            LivingEntity attacker = (LivingEntity)source.getSource();
            if (attacker == null) return;

            boolean result = protectDefeated(self, attacker) || protectDefeated(attacker, self);
            cir.setReturnValue(!result);
        }

        @Unique
        private static boolean protectDefeated(LivingEntity entity, LivingEntity attacker) {
            if (!entity.hasStatusEffect(ModStatusEffects.DEFEAT)) return false;
            return attacker.hasStatusEffect(ModStatusEffects.FIGHTING_SPIRIT);
        }

        @Redirect(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;tryUseTotem(Lnet/minecraft/entity/damage/DamageSource;)Z"))
        private boolean defeatedStatus(LivingEntity entity, DamageSource source) {
            boolean defeat = tryDefeat(entity, source);
            boolean usedTotem = this.tryUseTotem(source);
            return !usedTotem || !defeat;
        }

        @Unique
        private static boolean tryDefeat(LivingEntity entity, DamageSource source) {
            if (source.isIn(DamageTypeTags.BYPASSES_INVULNERABILITY)) return false;
            LivingEntity attacker = (LivingEntity)source.getSource();
            if (attacker == null) return false;
            if (!entity.hasStatusEffect(ModStatusEffects.FIGHTING_SPIRIT)) return false;
            if (!attacker.hasStatusEffect(ModStatusEffects.FIGHTING_SPIRIT)) return false;

            entity.setHealth(1.0F);
            entity.extinguish();
            entity.removeStatusEffect(ModStatusEffects.FIGHTING_SPIRIT);
            entity.addStatusEffect(
                    new StatusEffectInstance(ModStatusEffects.DEFEAT, 400, 0, true, true)
            );
            return true;
        }

    }

}
