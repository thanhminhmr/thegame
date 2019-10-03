package mrmathami.thegame.drawer.tile;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import mrmathami.thegame.drawer.EntityDrawer;
import mrmathami.thegame.field.GameEntity;

import javax.annotation.Nonnull;

public final class FlareDrawer implements EntityDrawer {
	@Override
	public void draw(int tickCount, @Nonnull GraphicsContext graphicsContext, @Nonnull GameEntity entity, float screenPosX, float screenPosY, float screenWidth, float screenHeight, float zoom) {
		graphicsContext.setFill(Color.ORANGERED);
		graphicsContext.fillRoundRect(screenPosX, screenPosY, screenWidth, screenHeight, 1, 1);
	}
}
