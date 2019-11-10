package mrmathami.thegame.drawer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import mrmathami.thegame.Config;
import mrmathami.thegame.GameEntities;
import mrmathami.thegame.entity.GameEntity;
import mrmathami.thegame.entity.tile.tower.NormalTower;

import javax.annotation.Nonnull;

public final class NormalTowerDrawer implements EntityDrawer {
	@Override
	public void draw(long tickCount, @Nonnull GraphicsContext graphicsContext, @Nonnull GameEntity entity, double screenPosX, double screenPosY, double screenWidth, double screenHeight, double zoom) {
		graphicsContext.setFill(Color.RED);
		graphicsContext.fillOval(screenPosX, screenPosY, screenWidth, screenHeight);
		if (entity.getClass().equals(NormalTower.class))
		{
			NormalTower tower = (NormalTower) entity;
			graphicsContext.setFill(Color.rgb(0, 0, 0, 0.1));
			double range = Config.NORMAL_TOWER_RANGE;
			final double ts = Config.TILE_SIZE;
			double x = GameEntities.getMidX(tower) - range;
			double y = GameEntities.getMidY(tower) - range;
			graphicsContext.fillOval(x * ts, y * ts, range * 2 * ts, range * 2 * ts);
		}
	}
}
