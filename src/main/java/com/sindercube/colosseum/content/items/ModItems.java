package com.sindercube.colosseum.content.items;

import com.sindercube.colosseum.Colosseum;
import com.sindercube.colosseum.content.blocks.ModBlocks;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModItems {

    public static final Item RUBY = register("ruby", new Item(new Item.Settings()));

    public static final Item MINEAPPLE_SLICE = register("mineapple_slice", new Item(new Item.Settings().food(new FoodComponent.Builder().alwaysEdible().statusEffect(new StatusEffectInstance(StatusEffects.POISON, 200), 100).build())));

    public static final Item MINEAPPLE_SEEDS = register("mineapple_seeds", new AliasedBlockItem(ModBlocks.MINEAPPLE_PLANT, new Item.Settings()));


    public static void init() {}


    public static Item register(String id, Item item) {
        return Registry.register(Registries.ITEM, Colosseum.of(id), item);
    }

}
