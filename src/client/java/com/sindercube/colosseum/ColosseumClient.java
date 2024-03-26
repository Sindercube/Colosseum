package com.sindercube.colosseum;

import com.sindercube.colosseum.content.blocks.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class ColosseumClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
				ModBlocks.MINEAPPLE_PLANT,
				ModBlocks.MINEAPPLE,
				ModBlocks.MINEAPPLE_CROWN
		);
	}
}