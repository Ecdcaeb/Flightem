package com.Hileb.flightem.proxy;

import net.minecraft.client.settings.KeyBinding;

import java.util.ArrayList;
import java.util.List;

public class FlightemClientProxy extends FlightemProxyBase {
    public static final List<KeyBinding> KEY_BINDINGS = new ArrayList<KeyBinding>();
	//public static final KeyBinding CAST_MAINHAND = new ModKeyBinding("activate_skill_mainhand", KeyConflictContext.IN_GAME, KeyModifier.NONE, Keyboard.KEY_R, "key.category.moremomostories");
	//public static final KeyBinding CAST_OFFHAND = new ModKeyBinding("activate_skill_offhand", KeyConflictContext.IN_GAME, KeyModifier.NONE, Keyboard.KEY_GRAVE, "key.category.moremomostories");

	public boolean isServer()
	{
		return false;
	}

}
