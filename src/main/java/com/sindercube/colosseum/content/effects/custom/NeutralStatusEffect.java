package com.sindercube.colosseum.content.effects.custom;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

import java.awt.*;

public class NeutralStatusEffect extends StatusEffect {

    public NeutralStatusEffect(String hex) {
        super(StatusEffectCategory.NEUTRAL, Color.decode(hex).getRGB());
    }

}
