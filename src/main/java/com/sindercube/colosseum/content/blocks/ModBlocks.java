package com.sindercube.colosseum.content.blocks;

import com.sindercube.colosseum.Colosseum;
import com.sindercube.colosseum.content.blocks.custom.ArenaBlock;
import com.sindercube.colosseum.content.blocks.custom.MineappleBlock;
import com.sindercube.colosseum.content.blocks.custom.MineapplePlantBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;

public class ModBlocks {

    public static final Block ARENA_BLOCK = register("arena_block",
            new ArenaBlock(FabricBlockSettings.create())
    );

    public static final Block MINEAPPLE_PLANT = register("mineapple_plant",
            new MineapplePlantBlock(FabricBlockSettings.create().mapColor(MapColor.DARK_GREEN).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).pistonBehavior(PistonBehavior.DESTROY))
    );
    public static final Block MINEAPPLE_CROWN = register("mineapple_crown",
            new ShortPlantBlock(FabricBlockSettings.create().mapColor(MapColor.DARK_GREEN).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offset(AbstractBlock.OffsetType.XZ).pistonBehavior(PistonBehavior.DESTROY))
    );

    public static final Block MINEAPPLE = register("mineapple",
            new MineappleBlock(FabricBlockSettings.create().strength(2).sounds(BlockSoundGroup.WOOD))
    );
    public static final Block PEELED_MINEAPPLE = register("peeled_mineapple",
            new MineappleBlock(FabricBlockSettings.create().strength(2).sounds(BlockSoundGroup.SLIME))
    );


    public static void init() {
        StrippableBlockRegistry.register(ModBlocks.MINEAPPLE, ModBlocks.PEELED_MINEAPPLE);
    }


    public static Block register(String id, Block block) {
        Block result = Registry.register(Registries.BLOCK, Colosseum.of(id), block);
        Registry.register(Registries.ITEM, Colosseum.of(id), new BlockItem(result, new Item.Settings()));
        return result;
    }

}
