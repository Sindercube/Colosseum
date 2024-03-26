package com.sindercube.colosseum.content.blocks.custom;

import com.sindercube.colosseum.content.blocks.ModBlocks;
import com.sindercube.colosseum.content.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class MineapplePlantBlock extends CropBlock {

    public static final IntProperty AGE = IntProperty.of("age", 0, 2);

    public MineapplePlantBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public int getMaxAge() {
        return 2;
    }

    @Override
    protected IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextInt(3) == 0) return;
        int age = this.getAge(state) + this.getGrowthAmount(world);
        int max = this.getMaxAge();
        if (age == max) growMineapple(world, pos);
        super.randomTick(state, world, pos, random);
    }

    @Override
    protected int getGrowthAmount(World world) {
        return 1;
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.MINEAPPLE_SEEDS;
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        entity.damage(world.getDamageSources().cactus(), 1.0F);
    }

    @Override
    public boolean isFertilizable(WorldView w, BlockPos p, BlockState state) {
        return state.get(AGE) == 0;
    };

    public static void growMineapple(World world, BlockPos pos) {
        BlockPos fruitPos = pos.up();
        BlockPos crownPos = fruitPos.up();

        if (fruitPos.getY() > world.getTopY()) return;
        if (!world.getBlockState(fruitPos).isReplaceable()) return;
        world.setBlockState(fruitPos, ModBlocks.MINEAPPLE.getDefaultState());

        if (crownPos.getY() < world.getTopY() && world.getBlockState(crownPos).isReplaceable()) world.setBlockState(crownPos, ModBlocks.MINEAPPLE_CROWN.getDefaultState());
    }

    public static void fruitHarvested(World world, BlockPos pos) {
        if (world.isClient) return;
        BlockPos plantPos = pos.down();
        BlockState plantState = world.getBlockState(plantPos);
        if (plantState.getBlock() instanceof MineapplePlantBlock) world.setBlockState(plantPos, plantState.with(AGE, plantState.get(AGE)-1));
    }

}
