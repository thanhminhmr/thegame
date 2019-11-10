package mrmathami.thegame.drawer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import mrmathami.thegame.entity.GameEntity;

import javax.annotation.Nonnull;

public final class MountainDrawer implements EntityDrawer {
	private static Image image;
	@Override
	public void draw(long tickCount, @Nonnull GraphicsContext graphicsContext, @Nonnull GameEntity entity, double screenPosX, double screenPosY, double screenWidth, double screenHeight, double zoom) {
		graphicsContext.setFill(Color.DARKGREEN);
		graphicsContext.fillRect(screenPosX, screenPosY, screenWidth, screenHeight);
		if (image == null) {
			image = new Image(getClass().getResourceAsStream("/graphic/BrickTile.png"));
		}
		if (image != null) graphicsContext.drawImage(image, screenPosX, screenPosY, screenWidth, screenHeight);
	}
}
