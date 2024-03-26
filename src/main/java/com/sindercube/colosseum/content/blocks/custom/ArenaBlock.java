package com.sindercube.colosseum.content.blocks.custom;

import com.sindercube.colosseum.Colosseum;
import com.sindercube.colosseum.content.effects.ModStatusEffects;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.List;

public class ArenaBlock extends Block {

    public ArenaBlock(Settings settings) {
        super(settings);
    }


    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity p, ItemStack s) {
        world.scheduleBlockTick(pos, state.getBlock(), 0);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random r) {
        if (world.isClient) return;
        getAffectedPlayers(world, pos).forEach(ArenaBlock::applyEffect);
        world.scheduleBlockTick(pos, state.getBlock(), 200);
    }

    public static void applyEffect(PlayerEntity player) {
        StatusEffect effect = ModStatusEffects.FIGHTING_SPIRIT;

        boolean defeated = player.hasStatusEffect(ModStatusEffects.DEFEAT);
        if (defeated) effect = ModStatusEffects.DEFEAT;

        player.addStatusEffect(
                new StatusEffectInstance(effect, 400, 0, true, true)
        );
    }

    public static final TagKey<Block> SIZE_UPGRADE_BLOCKS = TagKey.of(RegistryKeys.BLOCK, Colosseum.of("arena_block_size_upgrade"));

    public static Box getBoundingBox(World world, BlockPos pos) {
        int range = 32;

        BlockState above = world.getBlockState(pos.up());
        while (above.isIn(SIZE_UPGRADE_BLOCKS)) {
            range += 8;
            above = world.getBlockState(pos.up());
        }

        return new Box(pos).expand(range, 32, range);
    }

    public static List<PlayerEntity> getAffectedPlayers(World world, BlockPos pos) {
        Box box = getBoundingBox(world, pos);
        return world.getNonSpectatingEntities(PlayerEntity.class, box)
                .stream()
                .filter(player -> !player.isCreative())
                .toList();
    }

}
