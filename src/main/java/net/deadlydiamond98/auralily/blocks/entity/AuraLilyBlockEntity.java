package net.deadlydiamond98.auralily.blocks.entity;

import de.ellpeck.naturesaura.api.aura.chunk.IAuraChunk;
import de.ellpeck.naturesaura.api.aura.type.IAuraType;
import de.ellpeck.naturesaura.packet.PacketHandler;
import de.ellpeck.naturesaura.packet.PacketParticleStream;
import net.deadlydiamond98.auralily.AuraToManaConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.block_entity.GeneratingFlowerBlockEntity;
import vazkii.botania.api.block_entity.RadiusDescriptor;

public class AuraLilyBlockEntity extends GeneratingFlowerBlockEntity {
    public AuraLilyBlockEntity(BlockPos pos, BlockState state) {
        super(AuraToManaBlockEntities.AURA_LILY_BLOCK_ENTITY.get(), pos, state);
    }


    // Rafflowsia -> 2100 per flower no diminish
    // Herbivorous Absorber -> 2000 per flower no diminish
    // 1 mana = 1.05 aura
    // 20 mana = 21 aura

    @Override
    public void tickFlower() {
        super.tickFlower();

        RandomSource rand = this.getLevel().getRandom();

        if (!this.getLevel().isClientSide) {

            if (this.ticksExisted % 10 == 0 && this.getMana() < this.getMaxMana()) {
                int auraInArea = IAuraChunk.getAuraInArea(this.level, this.worldPosition, 20);

                int absorbableAura = Math.min(auraInArea, AuraToManaConfig.auraPerMana);
                if (absorbableAura > 0) {
                    BlockPos spot = IAuraChunk.getHighestSpot(this.level, this.worldPosition, 20, this.worldPosition);
                    IAuraChunk chunk = IAuraChunk.getAuraChunk(this.level, spot);
                    chunk.drainAura(spot, absorbableAura);
                    this.addMana(20);
                    createAbsorbtionParticles(rand);
                }
            }
//            AuraToMana.LOGGER.info("Current Mana Level is: {} / {}", this.getMana(), this.getMaxMana());
//            AuraToMana.LOGGER.info("The Surrounding Aura is: {}", IAuraChunk.getAuraInArea(this.level, this.worldPosition, 20));
        }
    }

    private void createAbsorbtionParticles(RandomSource rand) {
        if (this.getLevel().getGameTime() % 3L == 0L) {
            PacketHandler.sendToAllAround(this.getLevel(), this.worldPosition, 32, new PacketParticleStream(
                    (float)this.worldPosition.getX() + (float)rand.nextGaussian() * 10.0F,
                    (float)this.worldPosition.getY() + rand.nextFloat() * 10.0F,
                    (float)this.worldPosition.getZ() + (float)rand.nextGaussian() * 10.0F,
                    (float)this.worldPosition.getX() + 0.5F,
                    (float)this.worldPosition.getY() + 0.5F,
                    (float)this.worldPosition.getZ() + 0.5F,
                    rand.nextFloat() * 0.1F + 0.1F,
                    this.getColor(),
                    rand.nextFloat() + 1.0F)
            );
        }
    }

    @Override
    public int getMaxMana() {
        return 300;
    }

    @Override
    public int getColor() {
        return IAuraType.forLevel(this.getLevel()).getColor();
    }

    @Override
    public @Nullable RadiusDescriptor getRadius() {
        return RadiusDescriptor.Rectangle.square(this.getEffectivePos(), 3);
    }
}
