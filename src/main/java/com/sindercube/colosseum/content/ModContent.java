package com.sindercube.colosseum.content;

import com.sindercube.colosseum.content.blocks.ModBlocks;
import com.sindercube.colosseum.content.effects.ModStatusEffects;
import com.sindercube.colosseum.content.items.ModItems;

public class ModContent {

    public static void init() {
        ModBlocks.init();
        ModItems.init();
        ModStatusEffects.init();
    }

}
