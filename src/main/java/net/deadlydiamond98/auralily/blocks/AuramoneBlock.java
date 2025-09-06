package net.deadlydiamond98.auralily.blocks;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.block.entity.BlockEntityType;
import vazkii.botania.api.block_entity.SpecialFlowerBlockEntity;
import vazkii.botania.forge.block.ForgeSpecialFlowerBlock;

import java.util.function.Supplier;

public class AuramoneBlock extends ForgeSpecialFlowerBlock {
    public AuramoneBlock(MobEffect stewEffect, int stewDuration, Properties props, Supplier<BlockEntityType<? extends SpecialFlowerBlockEntity>> blockEntityType) {
        super(stewEffect, stewDuration, props, blockEntityType);
    }
}
