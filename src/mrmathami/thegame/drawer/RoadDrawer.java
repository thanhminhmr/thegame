package mrmathami.thegame.drawer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import mrmathami.thegame.entity.GameEntity;
import mrmathami.thegame.entity.tile.Road;

import javax.annotation.Nonnull;

public final class RoadDrawer implements EntityDrawer {
	@Override
	public void draw(long tickCount, @Nonnull GraphicsContext graphicsContext, @Nonnull GameEntity entity, double screenPosX, double screenPosY, double screenWidth, double screenHeight, double zoom) {
		graphicsContext.setFill(Color.LIGHTGREEN);
		graphicsContext.fillRect(screenPosX, screenPosY, screenWidth, screenHeight);
//		if (entity instanceof Road) {
//			graphicsContext.setFill(Color.BLACK);
//			graphicsContext.fillText(String.format("%2.2f", ((Road) entity).getDistance()), screenPosX, screenPosY + screenHeight / 2);
//		}
	}
}
