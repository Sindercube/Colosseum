package com.sindercube.colosseum.content.blocks.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MineappleBlock extends PillarBlock {

    public MineappleBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!(newState.getBlock() instanceof MineappleBlock)) MineapplePlantBlock.fruitHarvested(world, pos);
        super.onStateReplaced(state, world, pos, newState, moved);
    }

}
