package mrmathami.thegame.field.characteristic;

import mrmathami.thegame.field.GameField;

import javax.annotation.Nonnull;

public interface EffectEntity extends DestroyableEntity {
	void doEffect(@Nonnull GameField field, int tickCount, @Nonnull LivingEntity livingEntity);
}
