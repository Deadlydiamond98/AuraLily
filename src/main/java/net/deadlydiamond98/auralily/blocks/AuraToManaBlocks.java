package net.deadlydiamond98.auralily.blocks;

import net.deadlydiamond98.auralily.AuraToMana;
import net.deadlydiamond98.auralily.blocks.entity.AuraToManaBlockEntities;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vazkii.botania.common.block.BotaniaBlocks;
import vazkii.botania.common.block.FloatingSpecialFlowerBlock;
import vazkii.botania.forge.block.ForgeSpecialFlowerBlock;

import java.util.function.Supplier;

public class AuraToManaBlocks {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AuraToMana.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, AuraToMana.MODID);

    public static final RegistryObject<Block> AURA_LILY = register("aura_lily", () -> new ForgeSpecialFlowerBlock(
            MobEffects.GLOWING, 10, BlockBehaviour.Properties.copy(Blocks.POPPY),
            AuraToManaBlockEntities.AURA_LILY_BLOCK_ENTITY::get, true
    ));

    public static final RegistryObject<Block> FLOATING_AURA_LILY = register("floating_aura_lily", () -> new FloatingSpecialFlowerBlock(
            BotaniaBlocks.FLOATING_PROPS, AuraToManaBlockEntities.AURA_LILY_BLOCK_ENTITY::get
    ));

    public static final RegistryObject<Block> POTTED_AURA_LILY = register("potted_aura_lily", () -> flowerPot(AURA_LILY.get()));

    static FlowerPotBlock flowerPot(Block block) {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY);
        return new FlowerPotBlock(block, properties);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        ITEMS.register(name, () -> new BlockItem(toReturn.get(), new Item.Properties()));
        return toReturn;
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        BLOCKS.register(eventBus);
    }
}
