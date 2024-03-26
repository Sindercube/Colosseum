package com.sindercube.colosseum.content.effects;

import com.sindercube.colosseum.Colosseum;
import com.sindercube.colosseum.content.effects.custom.NeutralStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModStatusEffects {

    public static final StatusEffect FIGHTING_SPIRIT = register(
            "fighting_spirit",
            new NeutralStatusEffect("#344933")
    );

    public static final StatusEffect DEFEAT = register(
            "defeat",
            new NeutralStatusEffect("#344333")
    );


    public static void init() {}


    private static StatusEffect register(String id, StatusEffect statusEffect) {
        return Registry.register(
                Registries.STATUS_EFFECT,
                Colosseum.of(id),
                statusEffect
        );
    }

}
