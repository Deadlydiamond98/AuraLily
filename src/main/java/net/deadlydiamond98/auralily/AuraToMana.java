package net.deadlydiamond98.auralily;

import com.mojang.logging.LogUtils;
import net.deadlydiamond98.auralily.blocks.AuraToManaBlocks;
import net.deadlydiamond98.auralily.blocks.entity.AuraToManaBlockEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import vazkii.botania.api.BotaniaRegistries;
import vazkii.botania.client.render.block_entity.SpecialFlowerBlockEntityRenderer;

@Mod(AuraToMana.MODID)
public class AuraToMana {

    public static final String MODID = "auralily";
    public static final Logger LOGGER = LogUtils.getLogger();

    public AuraToMana(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        AuraToManaBlocks.register(modEventBus);
        AuraToManaBlockEntities.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);

        context.registerConfig(ModConfig.Type.COMMON, AuraToManaConfig.SPEC);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == BotaniaRegistries.BOTANIA_TAB_KEY) {
            event.accept(AuraToManaBlocks.AURA_LILY);
            event.accept(AuraToManaBlocks.FLOATING_AURA_LILY);
        }
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
//        @SubscribeEvent
//        public static void onClientSetup(FMLClientSetupEvent event) {
//            event.enqueueWork(() -> {
//                ItemBlockRenderTypes.setRenderLayer(AuraToManaBlocks.AURA_LILY.get(), RenderType.cutout());
//                ItemBlockRenderTypes.setRenderLayer(AuraToManaBlocks.FLOATING_AURA_LILY.get(), RenderType.cutout());
//                ItemBlockRenderTypes.setRenderLayer(AuraToManaBlocks.POTTED_AURA_LILY.get(), RenderType.cutout());
//            });
//        }

        @SubscribeEvent
        public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(AuraToManaBlockEntities.AURA_LILY_BLOCK_ENTITY.get(), SpecialFlowerBlockEntityRenderer::new);
        }
    }
}
