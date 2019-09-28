package mrmathami.thegame.field.tile;

import mrmathami.thegame.Config;
import mrmathami.thegame.field.GameField;
import mrmathami.thegame.field.AbstractEntity;
import mrmathami.thegame.field.GameEntity;
import mrmathami.thegame.field.characteristic.CollidableEntity;
import mrmathami.thegame.field.characteristic.DestroyableEntity;
import mrmathami.thegame.field.characteristic.LivingEntity;
import mrmathami.thegame.field.characteristic.UpdatableEntity;
import mrmathami.thegame.field.listener.CollisionListener;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Set;

public final class Flare extends AbstractEntity implements UpdatableEntity, CollidableEntity, CollisionListener, DestroyableEntity {
	public static final int DIRECTION_UP = 1;
	public static final int DIRECTION_DOWN = 1 << 1;
	public static final int DIRECTION_LEFT = 1 << 2;
	public static final int DIRECTION_RIGHT = 1 << 3;
	public static final int DIRECTION_UP_LEFT = DIRECTION_UP | DIRECTION_LEFT;
	public static final int DIRECTION_UP_RIGHT = DIRECTION_UP | DIRECTION_RIGHT;
	public static final int DIRECTION_DOWN_LEFT = DIRECTION_DOWN | DIRECTION_LEFT;
	public static final int DIRECTION_DOWN_RIGHT = DIRECTION_DOWN | DIRECTION_RIGHT;
	public static final int DIRECTION_ALL = DIRECTION_UP | DIRECTION_DOWN | DIRECTION_LEFT | DIRECTION_RIGHT;

	private static final Set<Class<?>> COLLISION_SET = Set.of(Bomb.class, Wall.class);

	private final float strength;
	private final int length;
	private final int direction;

	private int tickDown = Config.FLARE_TICK_DOWN;

	public Flare(int createdTick, float posX, float posY, float strength, int length, int direction) {
		super(createdTick, posX, posY, 1.0f, 1.0f);
		this.strength = strength;
		this.length = length;
		this.direction = direction;
	}

	private boolean spawnFlare(@Nonnull GameField field, int tickCount, float posX, float posY, int direction) {
		final List<GameEntity> entities = field.getOverlappedEntities(posX, posY, 1.0f, 1.0f);
		for (final GameEntity entity : entities) {
			if (entity instanceof CollidableEntity && ((CollidableEntity) entity).canCollide(Flare.class)) return false;
		}
		field.doSpawn(new Flare(tickCount, posX, posY, strength, length - 1, direction));
		return true;
	}

	@Override
	public void doUpdate(@Nonnull GameField field, int tickCount) {
		this.tickDown -= 1;
		if (length > 0 && tickDown == Config.FLARE_TICK_SPREAD) {
			int combineDirection = 0;
			if ((direction & DIRECTION_UP) != 0 && spawnFlare(field, tickCount, getPosX(), getPosY() - 1.0f, DIRECTION_UP))
				combineDirection |= DIRECTION_UP;
			if ((direction & DIRECTION_DOWN) != 0 && spawnFlare(field, tickCount, getPosX(), getPosY() + 1.0f, DIRECTION_DOWN))
				combineDirection |= DIRECTION_DOWN;
			if ((direction & DIRECTION_LEFT) != 0 && spawnFlare(field, tickCount, getPosX() - 1.0f, getPosY(), DIRECTION_LEFT))
				combineDirection |= DIRECTION_LEFT;
			if ((direction & DIRECTION_RIGHT) != 0 && spawnFlare(field, tickCount, getPosX() + 1.0f, getPosY(), DIRECTION_RIGHT))
				combineDirection |= DIRECTION_RIGHT;

			if ((combineDirection & DIRECTION_UP_LEFT) == DIRECTION_UP_LEFT)
				spawnFlare(field, tickCount, getPosX() - 1.0f, getPosY() - 1.0f, DIRECTION_UP_LEFT);
			if ((combineDirection & DIRECTION_UP_RIGHT) == DIRECTION_UP_RIGHT)
				spawnFlare(field, tickCount, getPosX() + 1.0f, getPosY() - 1.0f, DIRECTION_UP_RIGHT);
			if ((combineDirection & DIRECTION_DOWN_LEFT) == DIRECTION_DOWN_LEFT)
				spawnFlare(field, tickCount, getPosX() - 1.0f, getPosY() + 1.0f, DIRECTION_DOWN_LEFT);
			if ((combineDirection & DIRECTION_DOWN_RIGHT) == DIRECTION_DOWN_RIGHT)
				spawnFlare(field, tickCount, getPosX() + 1.0f, getPosY() + 1.0f, DIRECTION_DOWN_RIGHT);
		}
	}

	@Override
	public <E extends CollidableEntity> boolean canCollide(Class<E> entityClass) {
		return COLLISION_SET.contains(entityClass);
	}

	@Override
	public void onCollision(@Nonnull GameField field, int tickCount, CollidableEntity collidableEntity) {
		if (collidableEntity instanceof LivingEntity) {
			((LivingEntity) collidableEntity).doEffect(-strength);
		}
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
