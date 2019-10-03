package mrmathami.thegame.field.characteristic;

public interface LivingEntity extends DestroyableEntity {
	float getHealth();

	void doEffect(float value);
}
