package mrmathami.thegame.field;


import mrmathami.thegame.Config;
import mrmathami.thegame.field.characteristic.DestroyableEntity;
import mrmathami.thegame.field.characteristic.EffectEntity;
import mrmathami.thegame.field.characteristic.LivingEntity;
import mrmathami.thegame.field.characteristic.UpdatableEntity;

import javax.annotation.Nonnull;
import java.util.*;

/**
 * Game Field. Created from GameMap for each new stage. Represent the currently playing game.
 */
public final class GameField {
	@Nonnull private final Set<GameEntity> entities = new LinkedHashSet<>(Config.TILE_MAP_COUNT);
	@Nonnull private final Collection<GameEntity> unmodifiableEntities = Collections.unmodifiableCollection(entities);
	@Nonnull private final List<GameEntity> spawnEntities = new ArrayList<>(Config.TILE_MAP_COUNT);

	/**
	 * Field width
	 */
	private final float width;
	/**
	 * Field height
	 */
	private final float height;
	/**
	 * Field tick count
	 */
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

	/**
	 * @return entities on the field. Read-only list.
	 */
	@Nonnull
	public final Collection<GameEntity> getEntities() {
		return unmodifiableEntities;
	}

	/**
	 * Add an Entity to spawn list. Entity will be spawned at the end of this tick.
	 * @param entity Entity to spawn
	 */
	public final void doSpawn(@Nonnull GameEntity entity) {
		if (entity.isBeingOverlapped(0.0f, 0.0f, width, height)) spawnEntities.add(entity);
	}

	/**
	 * Do a tick, in other words, update the field after a fixed period of time.
	 * Current update sequence:
	 * 1. Update Entity:
	 * 1.1. UpdatableEntity update itself, including moving.
	 * 1.2. EffectEntity check collision to affect LivingEntity.
	 * 1.3. DestroyableEntity check and react if it is going to be destroyed.
	 * 2. Destroy Entity:
	 * 2.1. Destroy entities that are marked to be destroyed.
	 * 2.2. Destroy entities that are outside the field.
	 * 3. Spawn Entity: Add entities that are marked to be spawned.
	 */
	public final void tick() {
		this.tickCount += 1;

		// 1.1. Update UpdatableEntity
		for (final GameEntity entity : entities) {
			if (entity instanceof UpdatableEntity) ((UpdatableEntity) entity).doUpdate(this, tickCount);
		}

		// 1.2. Update EffectEntity & LivingEntity
		for (final GameEntity entity : entities) {
			if (entity instanceof EffectEntity) {
				final EffectEntity effectEntity = (EffectEntity) entity;
				for (final GameEntity overlappedEntity : GameEntities.getOverlappedEntities(entities, entity.getPosX(), entity.getPosY(), entity.getWidth(), entity.getHeight())) {
					if (overlappedEntity instanceof LivingEntity) {
						effectEntity.doEffect(this, tickCount, (LivingEntity) overlappedEntity);
						if (effectEntity.isDestroyed()) break;
					}
				}
			}
		}

		// 1.3. Update DestroyableEntity
		final List<GameEntity> destroyedEntities = new ArrayList<>(Config.TILE_MAP_COUNT);
		for (final GameEntity entity : entities) {
			if (entity instanceof DestroyableEntity && ((DestroyableEntity) entity).isDestroyed()) {
				if (entity instanceof DestroyableEntity.DestroyListener) ((DestroyableEntity.DestroyListener) entity).onDestroy(this, tickCount);
				destroyedEntities.add(entity);
			}
		}

		// 2.1. Destroy entities
		entities.removeAll(destroyedEntities);

		// 2.2. Destroy entities
		entities.removeIf(entity -> !entity.isBeingOverlapped(0.0f, 0.0f, width, height));

		// 3. Spawn entities
		entities.addAll(spawnEntities);
		spawnEntities.clear();
	}
}
