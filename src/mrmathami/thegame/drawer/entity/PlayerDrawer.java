package mrmathami.thegame.drawer.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import mrmathami.thegame.drawer.EntityDrawer;
import mrmathami.thegame.field.GameEntity;

import javax.annotation.Nonnull;

public final class PlayerDrawer implements EntityDrawer {
	@Override
	public void draw(int tickCount, @Nonnull GraphicsContext graphicsContext, @Nonnull GameEntity entity, float screenPosX, float screenPosY, float screenWidth, float screenHeight, float zoom) {
		graphicsContext.setFill(Color.GREEN);
		graphicsContext.fillRoundRect(screenPosX, screenPosY, screenWidth, screenHeight, zoom / 10.0f, zoom / 10.0f);
	}
}
