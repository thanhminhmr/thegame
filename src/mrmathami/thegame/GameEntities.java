package mrmathami.thegame;

import mrmathami.thegame.entity.EffectEntity;
import mrmathami.thegame.entity.GameEntity;
import mrmathami.thegame.entity.LivingEntity;
import mrmathami.thegame.entity.bullet.NormalBullet;
import mrmathami.thegame.entity.enemy.NormalEnemy;
import mrmathami.thegame.entity.tile.Mountain;
import mrmathami.thegame.entity.tile.Target;
import mrmathami.utilities.Pair;
import mrmathami.utilities.UnorderedPair;

import javax.annotation.Nonnull;
import java.util.*;

public final class GameEntities {
	/**
	 * TODO: This is a list contains Pair of Entities that can collide with each other.
	 * Remember, if an entity can collide with itself, you should put that here too.
	 */
	private static final Set<UnorderedPair<Class<? extends GameEntity>, Class<? extends GameEntity>>> COLLISION_PAIR_SET
			= new HashSet<>(Set.of(
			UnorderedPair.immutableOf(Mountain.class, NormalEnemy.class),
//			UnorderedPair.immutableOf(Mountain.class, SmallerEnemy.class),
//			UnorderedPair.immutableOf(Mountain.class, TankerEnemy.class),
//			UnorderedPair.immutableOf(Mountain.class, BossEnemy.class),
			UnorderedPair.immutableOf(NormalEnemy.class, NormalEnemy.class)//,
//			UnorderedPair.immutableOf(SmallerEnemy.class, SmallerEnemy.class),
//			UnorderedPair.immutableOf(TankerEnemy.class, TankerEnemy.class),
//			UnorderedPair.immutableOf(BossEnemy.class, BossEnemy.class)
	));

	private static final Set<Pair<Class<? extends EffectEntity>, Class<? extends LivingEntity>>> EFFECT_LIVING_SET
			= new HashSet<>(Set.of(
			Pair.immutableOf(NormalBullet.class, NormalEnemy.class),
//			Pair.immutableOf(MachineGunBullet.class, NormalEnemy.class),
//			Pair.immutableOf(SniperBullet.class, NormalEnemy.class),
//			Pair.immutableOf(NormalBullet.class, SmallerEnemy.class),
//			Pair.immutableOf(MachineGunBullet.class, SmallerEnemy.class),
//			Pair.immutableOf(SniperBullet.class, SmallerEnemy.class),
//			Pair.immutableOf(NormalBullet.class, TankerEnemy.class),
//			Pair.immutableOf(MachineGunBullet.class, TankerEnemy.class),
//			Pair.immutableOf(SniperBullet.class, TankerEnemy.class),
//			Pair.immutableOf(NormalBullet.class, BossEnemy.class),
//			Pair.immutableOf(MachineGunBullet.class, BossEnemy.class),
//			Pair.immutableOf(SniperBullet.class, BossEnemy.class),
			Pair.immutableOf(NormalEnemy.class, Target.class)//,
//			Pair.immutableOf(SmallerEnemy.class, Target.class),
//			Pair.immutableOf(TankerEnemy.class, Target.class),
//			Pair.immutableOf(BossEnemy.class, Target.class)
	));

	private GameEntities() {
	}

	/**
	 * An useful method to find entities that are containing this specific rectangle region.
	 * Do not touch, just use me.
	 *
	 * @param entities input entities
	 * @param posX     rectangle pos x
	 * @param posY     rectangle pos y
	 * @param width    rectangle width
	 * @param height   rectangle height
	 * @return containing entities
	 */
	public static Collection<GameEntity> getContainingEntities(Collection<GameEntity> entities,
			double posX, double posY, double width, double height) {
		final List<GameEntity> outputEntities = new ArrayList<>(Config._TILE_MAP_COUNT);
		for (final GameEntity entity : entities) {
			if (entity.isContaining(posX, posY, width, height)) outputEntities.add(entity);
		}
		return outputEntities;
	}

	/**
	 * An useful method to find entities that are being contained in a specific rectangle region.
	 * Do not touch, just use me.
	 *
	 * @param entities input entities
	 * @param posX     rectangle pos x
	 * @param posY     rectangle pos y
	 * @param width    rectangle width
	 * @param height   rectangle height
	 * @return contained entities
	 */
	public static Collection<GameEntity> getContainedEntities(Collection<GameEntity> entities,
			double posX, double posY, double width, double height) {
		final List<GameEntity> outputEntities = new ArrayList<>(Config._TILE_MAP_COUNT);
		for (final GameEntity entity : entities) {
			if (entity.isBeingContained(posX, posY, width, height)) outputEntities.add(entity);
		}
		return outputEntities;
	}

	/**
	 * An useful method to find entities that are being overlapped in a specific rectangle region.
	 * Do not touch, just use me.
	 *
	 * @param entities input entities
	 * @param posX     rectangle pos x
	 * @param posY     rectangle pos y
	 * @param width    rectangle width
	 * @param height   rectangle height
	 * @return overlapped entities
	 */
	public static Collection<GameEntity> getOverlappedEntities(Collection<GameEntity> entities,
			double posX, double posY, double width, double height) {
		final List<GameEntity> outputEntities = new ArrayList<>(Config._TILE_MAP_COUNT);
		for (final GameEntity entity : entities) {
			if (entity.isBeingOverlapped(posX, posY, width, height)) outputEntities.add(entity);
		}
		return outputEntities;
	}

	/**
	 * An useful method to find entities that are being overlapped in a specific rectangle region.
	 * Do not touch, just use me.
	 *
	 * @param entities    input entities
	 * @param entityClass filter entity class
	 * @param posX        rectangle pos x
	 * @param posY        rectangle pos y
	 * @param width       rectangle width
	 * @param height      rectangle height
	 * @return overlapped entities
	 */
	public static <E extends GameEntity> Collection<E> getFilteredContainingEntities(Collection<GameEntity> entities,
			Class<E> entityClass, double posX, double posY, double width, double height) {
		final List<E> outputEntities = new ArrayList<>(Config._TILE_MAP_COUNT);
		for (final GameEntity entity : entities) {
			if (entityClass.isInstance(entity) && entity.isContaining(posX, posY, width, height)) {
				outputEntities.add(entityClass.cast(entity));
			}
		}
		return outputEntities;
	}

	/**
	 * An useful method to find entities that are being overlapped in a specific rectangle region.
	 * Do not touch, just use me.
	 *
	 * @param entities    input entities
	 * @param entityClass filter entity class
	 * @param posX        rectangle pos x
	 * @param posY        rectangle pos y
	 * @param width       rectangle width
	 * @param height      rectangle height
	 * @return overlapped entities
	 */
	public static <E extends GameEntity> Collection<E> getFilteredContainedEntities(Collection<GameEntity> entities,
			Class<E> entityClass, double posX, double posY, double width, double height) {
		final List<E> outputEntities = new ArrayList<>(Config._TILE_MAP_COUNT);
		for (final GameEntity entity : entities) {
			if (entityClass.isInstance(entity) && entity.isBeingContained(posX, posY, width, height)) {
				outputEntities.add(entityClass.cast(entity));
			}
		}
		return outputEntities;
	}

	/**
	 * An useful method to find entities that are being overlapped in a specific rectangle region.
	 * Do not touch, just use me.
	 *
	 * @param entities    input entities
	 * @param entityClass filter entity class
	 * @param posX        rectangle pos x
	 * @param posY        rectangle pos y
	 * @param width       rectangle width
	 * @param height      rectangle height
	 * @return overlapped entities
	 */
	public static <E extends GameEntity> Collection<E> getFilteredOverlappedEntities(Collection<GameEntity> entities,
			Class<E> entityClass, double posX, double posY, double width, double height) {
		final List<E> outputEntities = new ArrayList<>(Config._TILE_MAP_COUNT);
		for (final GameEntity entity : entities) {
			if (entityClass.isInstance(entity) && entity.isBeingOverlapped(posX, posY, width, height)) {
				outputEntities.add(entityClass.cast(entity));
			}
		}
		return outputEntities;
	}

	/**
	 * Check whether two entity class can collide. If you have an entity, use entity.getClass() to get its class.
	 *
	 * @param entityClassA entity class
	 * @param entityClassB entity class
	 * @return if two entity can collide
	 */
	public static boolean isCollidable(@Nonnull Class<? extends GameEntity> entityClassA, @Nonnull Class<? extends GameEntity> entityClassB) {
		return COLLISION_PAIR_SET.contains(UnorderedPair.immutableOf(entityClassA, entityClassB));
	}

	/**
	 * An useful method to find entities that are being collided with a specific rectangle region.
	 * Do not touch, just use me.
	 *
	 * @param entities input entities
	 * @param posX     rectangle pos x
	 * @param posY     rectangle pos y
	 * @param width    rectangle width
	 * @param height   rectangle height
	 * @return collided entities
	 */
	public static Collection<GameEntity> getCollidedEntities(Collection<GameEntity> entities,
			Class<? extends GameEntity> entityClass, double posX, double posY, double width, double height) {
		final List<GameEntity> outputEntities = new ArrayList<>(Config._TILE_MAP_COUNT);
		for (final GameEntity entity : entities) {
			if (entity.isBeingOverlapped(posX, posY, width, height) && isCollidable(entityClass, entity.getClass())) {
				outputEntities.add(entity);
			}
		}
		return outputEntities;
	}

	/**
	 * Check whether effect entity can affect living entity. If you have an entity,
	 * use entity.getClass() to get its class.
	 *
	 * @param effectClass effect entity class
	 * @param livingClass living entity class
	 * @return if effect entity can affect living entity
	 */
	public static boolean isAffectable(@Nonnull Class<? extends EffectEntity> effectClass, @Nonnull Class<? extends LivingEntity> livingClass) {
		return EFFECT_LIVING_SET.contains(Pair.immutableOf(effectClass, livingClass));
	}

	/**
	 * An useful method to find entities that are being affected by a specific rectangle region.
	 * Do not touch, just use me.
	 *
	 * @param entities input entities
	 * @param posX     rectangle pos x
	 * @param posY     rectangle pos y
	 * @param width    rectangle width
	 * @param height   rectangle height
	 * @return affected entities
	 */
	public static Collection<LivingEntity> getAffectedEntities(Collection<GameEntity> entities,
			Class<? extends EffectEntity> entityClass, double posX, double posY, double width, double height) {
		final List<LivingEntity> outputEntities = new ArrayList<>(Config._TILE_MAP_COUNT);
		for (final GameEntity entity : entities) {
			if (entity instanceof LivingEntity && entity.isBeingOverlapped(posX, posY, width, height)) {
				final LivingEntity livingEntity = (LivingEntity) entity;
				if (isAffectable(entityClass, livingEntity.getClass())) outputEntities.add(livingEntity);
			}
		}
		return outputEntities;
	}

	public static <E extends GameEntity, V extends E> Collection<V> entitiesFilter(Collection<E> entities, Class<V> entityClass) {
		final List<V> outputEntities = new ArrayList<>(Config._TILE_MAP_COUNT);
		for (final E entity : entities)
			if (entityClass.isInstance(entity)) outputEntities.add(entityClass.cast(entity));
		return outputEntities;
	}
}
