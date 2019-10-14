package mrmathami.thegame.entity.bullet;

import mrmathami.thegame.GameField;
import mrmathami.thegame.entity.*;

import javax.annotation.Nonnull;

public abstract class AbstractBullet extends AbstractEntity implements UpdatableEntity, EffectEntity, DestroyableEntity {
	private final double deltaX;
	private final double deltaY;
	private final long strength;
	private long tickDown;

	protected AbstractBullet(long createdTick, double posX, double posY, double deltaX, double deltaY, double speed, long strength, long timeToLive) {
		super(createdTick, posX, posY, 0.2, 0.2);
		final double normalize = speed / Math.sqrt(deltaX * deltaX + deltaY * deltaY);
		this.deltaX = deltaX * normalize;
		this.deltaY = deltaY * normalize;
		this.strength = strength;
		this.tickDown = timeToLive;
	}

	@Override
	public final void onUpdate(@Nonnull GameField field) {
		this.tickDown -= 1;
		setPosX(getPosX() + deltaX);
		setPosY(getPosY() + deltaY);
	}

	@Override
	public final boolean onEffect(@Nonnull GameField field, @Nonnull LivingEntity livingEntity) {
		livingEntity.doEffect(-strength);
		this.tickDown = 0;
		return false;
	}

	@Override
	public final void doDestroy() {
		this.tickDown = 0;
	}

	@Override
	public final boolean isDestroyed() {
		return tickDown <= -0;
	}
}
