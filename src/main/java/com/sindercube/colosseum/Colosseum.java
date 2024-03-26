package com.sindercube.colosseum;

import com.sindercube.colosseum.content.ModContent;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Colosseum implements ModInitializer {

	public static final String MOD_ID = "colosseum";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier of(String path) {
		return new Identifier(MOD_ID, path);
	}

	@Override
	public void onInitialize() {
		ModContent.init();
		LOGGER.info("Colosseum Initialized!");
	}

}