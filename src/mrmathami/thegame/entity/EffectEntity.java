package mrmathami.thegame.entity;

import mrmathami.thegame.GameField;

import javax.annotation.Nonnull;

public interface EffectEntity extends DestroyableEntity {
	boolean onEffect(@Nonnull GameField field, @Nonnull LivingEntity livingEntity);
}
