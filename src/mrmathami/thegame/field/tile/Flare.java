package mrmathami.thegame.field.tile;

import mrmathami.thegame.Config;
import mrmathami.thegame.field.AbstractEntity;
import mrmathami.thegame.field.GameEntities;
import mrmathami.thegame.field.GameEntity;
import mrmathami.thegame.field.GameField;
import mrmathami.thegame.field.characteristic.DestroyableEntity;
import mrmathami.thegame.field.characteristic.EffectEntity;
import mrmathami.thegame.field.characteristic.LivingEntity;
import mrmathami.thegame.field.characteristic.UpdatableEntity;

import javax.annotation.Nonnull;
import java.util.Collection;

public final class Flare extends AbstractEntity implements UpdatableEntity, EffectEntity, DestroyableEntity, GameEntity {
	public static final int DIRECTION_UP = 1;
	public static final int DIRECTION_DOWN = 1 << 1;
	public static final int DIRECTION_LEFT = 1 << 2;
	public static final int DIRECTION_RIGHT = 1 << 3;
	public static final int DIRECTION_UP_LEFT = DIRECTION_UP | DIRECTION_LEFT;
	public static final int DIRECTION_UP_RIGHT = DIRECTION_UP | DIRECTION_RIGHT;
	public static final int DIRECTION_DOWN_LEFT = DIRECTION_DOWN | DIRECTION_LEFT;
	public static final int DIRECTION_DOWN_RIGHT = DIRECTION_DOWN | DIRECTION_RIGHT;
	public static final int DIRECTION_ALL = DIRECTION_UP | DIRECTION_DOWN | DIRECTION_LEFT | DIRECTION_RIGHT;

	private final float strength;
	private final int length;
	private final int direction;

	private int tickDown = Config.FLARE_TICK_DOWN;

	public Flare(int createdTick, int posX, int posY, float strength, int length, int direction) {
		super(createdTick, posX, posY, 1, 1);
		this.strength = strength;
		this.length = length;
		this.direction = direction;
	}

	private boolean spawnFlare(@Nonnull GameField field, int tickCount, int posX, int posY, int direction) {
		final Collection<GameEntity> entities = GameEntities.getOverlappedEntities(field.getEntities(), posX, posY, 1.0f, 1.0f);
		for (final GameEntity entity : entities) {
			if (GameEntities.isCollidable(Flare.class, entity.getClass())) return false;
		}
		field.doSpawn(new Flare(tickCount, posX, posY, strength, length - 1, direction));
		return true;
	}

	@Override
	public void doUpdate(@Nonnull GameField field, int tickCount) {
		this.tickDown -= 1;
		if (length > 0 && tickDown == Config.FLARE_TICK_SPREAD) {
			final int posX = Math.round(getPosX());
			final int posY = Math.round(getPosY());
			
			int combineDirection = 0;
			if ((direction & DIRECTION_UP) != 0 && spawnFlare(field, tickCount, posX, posY - 1, DIRECTION_UP))
				combineDirection |= DIRECTION_UP;
			if ((direction & DIRECTION_DOWN) != 0 && spawnFlare(field, tickCount, posX, posY + 1, DIRECTION_DOWN))
				combineDirection |= DIRECTION_DOWN;
			if ((direction & DIRECTION_LEFT) != 0 && spawnFlare(field, tickCount, posX - 1, posY, DIRECTION_LEFT))
				combineDirection |= DIRECTION_LEFT;
			if ((direction & DIRECTION_RIGHT) != 0 && spawnFlare(field, tickCount, posX + 1, posY, DIRECTION_RIGHT))
				combineDirection |= DIRECTION_RIGHT;

			if ((combineDirection & DIRECTION_UP_LEFT) == DIRECTION_UP_LEFT)
				spawnFlare(field, tickCount, posX - 1, posY - 1, DIRECTION_UP_LEFT);
			if ((combineDirection & DIRECTION_UP_RIGHT) == DIRECTION_UP_RIGHT)
				spawnFlare(field, tickCount, posX + 1, posY - 1, DIRECTION_UP_RIGHT);
			if ((combineDirection & DIRECTION_DOWN_LEFT) == DIRECTION_DOWN_LEFT)
				spawnFlare(field, tickCount, posX - 1, posY + 1, DIRECTION_DOWN_LEFT);
			if ((combineDirection & DIRECTION_DOWN_RIGHT) == DIRECTION_DOWN_RIGHT)
				spawnFlare(field, tickCount, posX + 1, posY + 1, DIRECTION_DOWN_RIGHT);
		}
	}

	@Override
	public void doEffect(@Nonnull GameField field, int tickCount, @Nonnull LivingEntity livingEntity) {
		livingEntity.doEffect(-strength);
	}

	@Override
	public void doDestroy() {
		this.tickDown = 0;
	}

	@Override
	public boolean isDestroyed() {
		return tickDown <= 0;
	}
}
