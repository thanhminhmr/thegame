package mrmathami.thegame.entity.tile.tower;

import mrmathami.thegame.GameEntities;
import mrmathami.thegame.GameField;
import mrmathami.thegame.entity.UpdatableEntity;
import mrmathami.thegame.entity.bullet.AbstractBullet;
import mrmathami.thegame.entity.enemy.AbstractEnemy;
import mrmathami.thegame.entity.tile.AbstractTile;

import javax.annotation.Nonnull;

public abstract class AbstractTower<E extends AbstractBullet> extends AbstractTile implements UpdatableEntity {
	private final double range;
	private final long speed;
	private double rotation;

	private long tickDown;

	AbstractTower(long createdTick, long posX, long posY, double range, long speed) {
		super(createdTick, posX, posY, 1L, 1L);
		this.range = range;
		this.speed = speed;
		this.tickDown = 0;
	}

	@Override
	public final void onUpdate(@Nonnull GameField field) {
		this.tickDown -= 1;
		if (tickDown <= 0) {
			// TODO: Find a target and spawn a bullet to that direction ... done!
			// Use GameEntities.getFilteredOverlappedEntities to find target in range
			// Remember to set this.tickDown back to this.speed after shooting something.
			// this.tickDown = speed;
			double x = GameEntities.getMidX(this);
			double y = GameEntities.getMidY(this);
			for (AbstractEnemy enemy : GameEntities.entitiesFilter(field.getEntities(), AbstractEnemy.class)) {
				double dx = GameEntities.getMidX(enemy) - x;
				double dy = GameEntities.getMidY(enemy) - y;
				if (dx * dx + dy * dy < range * range) {
					field.doSpawn(doSpawn(field.getTickCount(), x, y, dx, dy));
					tickDown = speed;
					rotation = Math.PI / 2 + Math.atan(dy / dx) + Math.PI * (dx < 0 ? 1 : 0);
					break;
				}
			}
		}
	}

	/**
	 * Create a new bullet. Each tower spawn different type of bullet.
	 * Override this method and return the type of bullet that your tower shot out.
	 * See NormalTower for example.
	 *
	 * @param createdTick createdTick
	 * @param posX posX
	 * @param posY posY
	 * @param deltaX deltaX
	 * @param deltaY deltaY
	 * @return the bullet entity
	 */
	@Nonnull
	protected abstract E doSpawn(long createdTick, double posX, double posY, double deltaX, double deltaY);

	public double getRotation() {
		return Math.toDegrees(rotation);
	}
}
