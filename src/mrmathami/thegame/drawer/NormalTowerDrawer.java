package mrmathami.thegame.drawer;

import javafx.scene.canvas.GraphicsContext;
import mrmathami.thegame.Config;
import mrmathami.thegame.LoadedImage;
import mrmathami.thegame.entity.GameEntity;
import mrmathami.thegame.entity.tile.tower.NormalTower;

import javax.annotation.Nonnull;

public final class NormalTowerDrawer implements EntityDrawer {
	@Override
	public void draw(long tickCount, @Nonnull GraphicsContext graphicsContext, @Nonnull GameEntity entity, double screenPosX, double screenPosY, double screenWidth, double screenHeight, double zoom) {
//		graphicsContext.setFill(Color.RED);
//		graphicsContext.fillOval(screenPosX, screenPosY, screenWidth, screenHeight);
//		if (entity.getClass().equals(NormalTower.class))
//		{
//			NormalTower tower = (NormalTower) entity;
//			graphicsContext.setFill(Color.rgb(0, 0, 0, 0.1));
//			double range = Config.NORMAL_TOWER_RANGE;
//			final double ts = Config.TILE_SIZE;
//			double x = GameEntities.getMidX(tower) - range;
//			double y = GameEntities.getMidY(tower) - range;
//			graphicsContext.fillOval(x * ts, y * ts, range * 2 * ts, range * 2 * ts);
//		}
		if (entity.getClass().equals(NormalTower.class)) {
			NormalTower tower = (NormalTower) entity;
			graphicsContext.save();
			graphicsContext.translate((tower.getPosX() + tower.getWidth() / 2) * Config.TILE_SIZE, (tower.getPosY() + tower.getHeight() / 2) * Config.TILE_SIZE);
			graphicsContext.rotate(tower.getRotation());
			graphicsContext.drawImage(LoadedImage.NORMAL_TOWER, -screenWidth / 2, -screenHeight / 2, screenWidth, screenHeight);
			graphicsContext.restore();
		}
	}
}
