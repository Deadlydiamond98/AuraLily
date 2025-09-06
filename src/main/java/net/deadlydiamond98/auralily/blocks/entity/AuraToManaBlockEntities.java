package net.deadlydiamond98.auralily.blocks.entity;

import net.deadlydiamond98.auralily.AuraToMana;
import net.deadlydiamond98.auralily.blocks.AuraToManaBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AuraToManaBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, AuraToMana.MODID);

    public static final RegistryObject<BlockEntityType<AuraLilyBlockEntity>> AURA_LILY_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("auramone_block_entity", () -> BlockEntityType.Builder.of(
                    AuraLilyBlockEntity::new,
                    AuraToManaBlocks.AURA_LILY.get(),
                    AuraToManaBlocks.FLOATING_AURA_LILY.get()
            ).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
