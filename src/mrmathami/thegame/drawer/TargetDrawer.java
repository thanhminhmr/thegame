package mrmathami.thegame.drawer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import mrmathami.thegame.LoadedImage;
import mrmathami.thegame.entity.GameEntity;
import mrmathami.thegame.entity.tile.Target;

import javax.annotation.Nonnull;

public final class TargetDrawer implements EntityDrawer {
	@Override
	public void draw(long tickCount, @Nonnull GraphicsContext graphicsContext, @Nonnull GameEntity entity, double screenPosX, double screenPosY, double screenWidth, double screenHeight, double zoom) {
//		graphicsContext.setStroke(Color.DARKRED);
//		graphicsContext.setLineWidth(4);
//		graphicsContext.strokeRect(screenPosX, screenPosY, screenWidth, screenHeight);
		graphicsContext.drawImage(LoadedImage.TARGET, screenPosX, screenPosY, screenWidth, screenHeight);
		if (entity instanceof Target) {
			Target target = ((Target) entity);
			graphicsContext.setFill(Color.BLUE);
			double blue = target.getHealth() * screenWidth /target.MAX_HP;
			graphicsContext.fillRect(screenPosX + screenWidth / 6, screenPosY + screenHeight / 2, blue * 2 / 3, 3);
		}
	}
}
