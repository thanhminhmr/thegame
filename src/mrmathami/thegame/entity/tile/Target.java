package mrmathami.thegame.entity.tile;

import mrmathami.thegame.GameField;
import mrmathami.thegame.entity.DestroyListener;
import mrmathami.thegame.entity.LivingEntity;

import javax.annotation.Nonnull;

public final class Target extends AbstractTile implements LivingEntity, DestroyListener{
	private long health;
	public final long MAX_HP;

	public Target(long createdTick, long posX, long posY, long width, long height, long health) {
		super(createdTick, posX, posY, width, height);
		this.health = health;
		MAX_HP = health;
	}

	@Override
	public long getHealth() {
		return health;
	}

	@Override
	public void doEffect(long value) {
		if (health != Long.MIN_VALUE) this.health += value;
	}

	@Override
	public void doDestroy() {
		this.health = Long.MIN_VALUE;
	}

	@Override
	public boolean isDestroyed() {
		return health <= 0L;
	}

	@Override
	public void onDestroy(@Nonnull GameField field) {
	}
}
