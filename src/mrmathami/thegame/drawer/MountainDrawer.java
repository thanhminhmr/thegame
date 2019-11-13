package mrmathami.thegame.drawer;

import javafx.scene.canvas.GraphicsContext;
import mrmathami.thegame.LoadedImage;
import mrmathami.thegame.entity.GameEntity;

import javax.annotation.Nonnull;

public final class MountainDrawer implements EntityDrawer {
	@Override
	public void draw(long tickCount, @Nonnull GraphicsContext graphicsContext, @Nonnull GameEntity entity, double screenPosX, double screenPosY, double screenWidth, double screenHeight, double zoom) {
//		graphicsContext.setFill(Color.DARKGREEN);
//		graphicsContext.fillRect(screenPosX, screenPosY, screenWidth, screenHeight);
		graphicsContext.drawImage(LoadedImage.MOUNTAIN, screenPosX, screenPosY, screenWidth, screenHeight);
	}
}
