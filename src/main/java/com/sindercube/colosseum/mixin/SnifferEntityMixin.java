package com.sindercube.colosseum.mixin;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.passive.SnifferEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Iterator;
import java.util.List;

@Mixin(SnifferEntity.class)
public class SnifferEntityMixin {

    @Shadow @Final private static TrackedData<Integer> FINISH_DIG_TIME;

    /**
     * @author Sindercube
     * @reason Biome based sniffer loot tables
     */
//    @Overwrite
//    private void dropSeeds() {
//        SnifferEntity self = (SnifferEntity)(Object)this;
//        if (self.getWorld().isClient) return;
//        if (self.getDataTracker().get(FINISH_DIG_TIME) != self.age) return;
//
//        ServerWorld serverWorld = (ServerWorld)self.getWorld();
//        LootTable lootTable = serverWorld.getServer().getLootManager().getLootTable(LootTables.SNIFFER_DIGGING_GAMEPLAY);
//        LootContextParameterSet lootContextParameterSet = (new LootContextParameterSet.Builder(serverWorld)).add(LootContextParameters.ORIGIN, self.getDigLocation()).add(LootContextParameters.self_ENTITY, self).build(LootContextTypes.GIFT);
//        List<ItemStack> list = lootTable.generateLoot(lootContextParameterSet);
//        BlockPos blockPos = self.getDigPos();
//        Iterator var6 = list.iterator();
//
//        while(var6.hasNext()) {
//            ItemStack itemStack = (ItemStack)var6.next();
//            ItemEntity itemEntity = new ItemEntity(serverWorld, (double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ(), itemStack);
//            itemEntity.setToDefaultPickupDelay();
//            serverWorld.spawnEntity(itemEntity);
//        }
//
//        self.playSound(SoundEvents.ENTITY_SNIFFER_DROP_SEED, 1.0F, 1.0F);
//    }

}
