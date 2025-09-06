package net.deadlydiamond98.auralily;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = AuraToMana.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AuraToManaConfig {

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.IntValue AURA_COUNT = BUILDER
            .comment("The amount of Aura that is consumed per 20 Botania Mana")
            .defineInRange("auraPerMana", 21, 1, Integer.MAX_VALUE);

    static final ForgeConfigSpec SPEC = BUILDER.build();
    public static int auraPerMana;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        auraPerMana = AURA_COUNT.get();
    }
}
