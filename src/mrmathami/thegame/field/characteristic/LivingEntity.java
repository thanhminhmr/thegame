package mrmathami.thegame.field.characteristic;

/**
 * Marker interface.
 * Mark living objects.
 */
public interface LivingEntity extends DestroyableEntity {
	float getHealth();

	void doEffect(float value);
}
