package mrmathami.thegame.field;


import mrmathami.thegame.Config;
import mrmathami.thegame.field.characteristic.DestroyableEntity;
import mrmathami.thegame.field.characteristic.EffectEntity;
import mrmathami.thegame.field.characteristic.LivingEntity;
import mrmathami.thegame.field.characteristic.UpdatableEntity;

import javax.annotation.Nonnull;
import java.util.*;

/**
 * Game Field. Create from map. Represent the currently playing game.
 */
public final class GameField {
	@Nonnull private final Set<GameEntity> entities = new LinkedHashSet<>(Config.TILE_MAP_COUNT);
	@Nonnull private final Collection<GameEntity> unmodifiableEntities = Collections.unmodifiableCollection(entities);
	@Nonnull private final List<GameEntity> spawnEntities = new ArrayList<>(Config.TILE_MAP_COUNT);

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
		return unmodifiableEntities;
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

		final List<GameEntity> destroyedEntities = new ArrayList<>(Config.TILE_MAP_COUNT);
		for (final GameEntity entity : entities) {
			if (entity instanceof DestroyableEntity && ((DestroyableEntity) entity).isDestroyed()) {
				if (entity instanceof DestroyableEntity.DestroyListener) ((DestroyableEntity.DestroyListener) entity).onDestroy(this, tickCount);
				destroyedEntities.add(entity);
			}
		}
		entities.removeAll(destroyedEntities);

		entities.removeIf(entity -> !entity.isBeingOverlapped(0.0f, 0.0f, width, height));

		entities.addAll(spawnEntities);
		spawnEntities.clear();
	}
}
