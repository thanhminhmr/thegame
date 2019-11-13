package mrmathami.thegame.drawer;

import javafx.scene.canvas.GraphicsContext;
import mrmathami.thegame.Config;
import mrmathami.thegame.LoadedImage;
import mrmathami.thegame.entity.GameEntity;
import mrmathami.thegame.entity.tile.tower.SniperTower;

import javax.annotation.Nonnull;

public final class SniperTowerDrawer implements EntityDrawer {
	@Override
	public void draw(long tickCount, @Nonnull GraphicsContext graphicsContext, @Nonnull GameEntity entity, double screenPosX, double screenPosY, double screenWidth, double screenHeight, double zoom) {
//		graphicsContext.setFill(Color.MEDIUMVIOLETRED);
//		graphicsContext.fillOval(screenPosX, screenPosY, screenWidth, screenHeight);
		if (entity.getClass().equals(SniperTower.class)) {
			SniperTower tower = (SniperTower) entity;
			graphicsContext.save();
			graphicsContext.translate((tower.getPosX() + tower.getWidth() / 2) * Config.TILE_SIZE, (tower.getPosY() + tower.getHeight() / 2) * Config.TILE_SIZE);
			graphicsContext.rotate(tower.getRotation());
			graphicsContext.drawImage(LoadedImage.SNIPER_TOWER, -screenWidth / 2, -screenHeight / 2, screenWidth, screenHeight);
			graphicsContext.restore();
		}
	}
}
