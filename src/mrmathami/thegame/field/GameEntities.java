package mrmathami.thegame.field;

import mrmathami.thegame.Config;
import mrmathami.thegame.field.entity.Player;
import mrmathami.thegame.field.tile.Bomb;
import mrmathami.thegame.field.tile.Flare;
import mrmathami.thegame.field.tile.Wall;

import javax.annotation.Nonnull;
import java.util.*;

public final class GameEntities {
	/**
	 * CHANGE ME: This is a list contains Pair of Entities that can collide with each other.
	 * Remember, if an entity can collide with itself, you should put that here too.
	 */
	private static final Set<Pair> COLLISION_PAIR_SET = new HashSet<>(Set.of(
			new Pair(Wall.class, Bomb.class),
			new Pair(Wall.class, Player.class),
			new Pair(Wall.class, Flare.class),
			new Pair(Bomb.class, Bomb.class)
	));

	private GameEntities() {
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
	 * @return overlapped entities
	 */
	public static Collection<GameEntity> getContainedEntities(Collection<GameEntity> entities, float posX, float posY, float width, float height) {
		final List<GameEntity> outputEntities = new ArrayList<>(Config.TILE_MAP_COUNT);
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
	public static Collection<GameEntity> getOverlappedEntities(Collection<GameEntity> entities, float posX, float posY, float width, float height) {
		final List<GameEntity> outputEntities = new ArrayList<>(Config.TILE_MAP_COUNT);
		for (final GameEntity entity : entities) {
			if (entity.isBeingOverlapped(posX, posY, width, height)) outputEntities.add(entity);
		}
		return outputEntities;
	}

	/**
	 * Check whether two entity class can collide. If you have an entity,
	 * use entity.getClass() to get its class.
	 *
	 * @param entityClassA entity class
	 * @param entityClassB entity class
	 * @param <TypeA> entity class
	 * @param <TypeB> entity class
	 * @return if two entity can collide
	 */
	public static <TypeA extends GameEntity, TypeB extends GameEntity>
	boolean isCollidable(@Nonnull Class<TypeA> entityClassA, @Nonnull Class<TypeB> entityClassB) {
		return COLLISION_PAIR_SET.contains(new Pair(entityClassA, entityClassB));
	}

	/**
	 * The special pair that order doesn't matter. Do not touch.
	 */
	private static final class Pair {
		@Nonnull private final Class<? extends GameEntity> classA;
		@Nonnull private final Class<? extends GameEntity> classB;

		private Pair(@Nonnull Class<? extends GameEntity> classA, @Nonnull Class<? extends GameEntity> classB) {
			this.classA = classA;
			this.classB = classB;
		}

		@Override
		public boolean equals(Object object) {
			if (this == object) return true;
			if (!(object instanceof Pair)) return false;
			final Pair pair = (Pair) object;
			return classA == pair.classA && classB == pair.classB || classA == pair.classB && classB == pair.classA;
		}

		@Override
		public int hashCode() {
			return classA.hashCode() ^ classB.hashCode();
		}
	}
}
