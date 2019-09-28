package mrmathami.thegame.field;


import mrmathami.thegame.Config;
import mrmathami.thegame.field.GameEntity;
import mrmathami.thegame.field.characteristic.CollidableEntity;
import mrmathami.thegame.field.characteristic.DestroyableEntity;
import mrmathami.thegame.field.characteristic.UpdatableEntity;
import mrmathami.thegame.field.listener.CollisionListener;
import mrmathami.thegame.field.listener.DestroyListener;

import javax.annotation.Nonnull;
import java.util.*;

/**
 * Game Field. Create from map. Represent the currently playing game.
 */
public final class GameField {
	@Nonnull private final Set<GameEntity> entities =
			new LinkedHashSet<>(Config.TILE_HORIZONTAL * Config.TILE_VERTICAL);
	@Nonnull private final List<GameEntity> spawnEntities =
			new ArrayList<>(Config.TILE_HORIZONTAL * Config.TILE_VERTICAL);
	@Nonnull private final Map<FloatRect, List<GameEntity>> overlappedCache =
			new HashMap<>(Config.TILE_HORIZONTAL * Config.TILE_VERTICAL);

	private final float width;
	private final float height;
	private int tickCount;

	public GameField(float width, float height) {
		this.width = width;
		this.height = height;
		this.tickCount = 0;
	}

	public final float getWidth() {
		return width;
	}

	public final float getHeight() {
		return height;
	}

	public final int getTickCount() {
		return tickCount;
	}

	@Nonnull
	public final Collection<GameEntity> getEntities() {
		return Collections.unmodifiableCollection(entities);
	}

	public final List<GameEntity> getOverlappedEntities(float posX, float posY, float width, float height) {
		final FloatRect rect = new FloatRect(posX, posY, width, height);
		final List<GameEntity> gameEntities = overlappedCache.get(rect);
		if (gameEntities != null) return gameEntities;

		final List<GameEntity> entities = new ArrayList<>(Config.TILE_HORIZONTAL * Config.TILE_VERTICAL);
		for (final GameEntity entity : this.entities) {
			if (entity.isBeingOverlapped(posX, posY, width, height)) entities.add(entity);
		}
		final List<GameEntity> readOnlyEntities = List.copyOf(entities);
		overlappedCache.put(rect, readOnlyEntities);
		return readOnlyEntities;
	}

	public final void doSpawn(@Nonnull GameEntity entity) {
		if (entity.isBeingOverlapped(0.0f, 0.0f, width, height)) spawnEntities.add(entity);
	}

	public final void tick() {
		this.tickCount += 1;

		for (final GameEntity entity : entities) {
			if (entity instanceof UpdatableEntity) ((UpdatableEntity) entity).doUpdate(this, tickCount);
		}
		for (final GameEntity entity : entities) {
			if (entity instanceof CollisionListener) {
				final CollisionListener collisionListener = (CollisionListener) entity;
				final List<GameEntity> overlappedEntities = getOverlappedEntities(entity.getPosX(), entity.getPosY(), entity.getWidth(), entity.getHeight());
				for (final GameEntity overlappedEntity : overlappedEntities) {
					if (overlappedEntity instanceof CollidableEntity && collisionListener.canCollide(((CollidableEntity) overlappedEntity).getClass())) {
						collisionListener.onCollision(this, tickCount, (CollidableEntity) overlappedEntity);
					}
				}
			}
		}
		final List<GameEntity> destroyedEntities = new ArrayList<>(Config.TILE_HORIZONTAL * Config.TILE_VERTICAL);
		for (final GameEntity entity : entities) {
			if (entity instanceof DestroyableEntity) {
				final DestroyableEntity destroyableEntity = (DestroyableEntity) entity;
				if (destroyableEntity.isDestroyed()) {
					if (entity instanceof DestroyListener) ((DestroyListener) entity).onDestroy(this, tickCount);
					destroyedEntities.add(destroyableEntity);
				}
			}
		}
		entities.removeAll(destroyedEntities);

		entities.removeIf(this::testEntityInsideField);

		entities.addAll(spawnEntities);
		spawnEntities.clear();
		overlappedCache.clear();
	}

	private boolean testEntityInsideField(GameEntity entity) {
		return !entity.isBeingOverlapped(0.0f, 0.0f, width, height);
	}

	private final static class FloatRect {
		private final float posX;
		private final float posY;
		private final float width;
		private final float height;

		private FloatRect(float posX, float posY, float width, float height) {
			this.posX = posX;
			this.posY = posY;
			this.width = width;
			this.height = height;
		}

		@Override
		public final boolean equals(Object object) {
			if (this == object) return true;
			if (object == null || getClass() != object.getClass()) return false;
			final FloatRect floatRect = (FloatRect) object;
			return floatRect.posX == posX && floatRect.posY == posY
					&& floatRect.width == width && floatRect.height == height;
		}

		@Override
		public final int hashCode() {
			return Integer.rotateLeft(Float.floatToIntBits(posX), 13)
					^ Integer.rotateLeft(Float.floatToIntBits(posY), 7)
					^ Integer.rotateLeft(Float.floatToIntBits(width), 3)
					^ Float.floatToIntBits(height);
		}
	}
}
