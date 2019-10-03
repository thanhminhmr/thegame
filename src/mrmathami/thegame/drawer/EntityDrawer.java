package mrmathami.thegame.drawer;

import javafx.scene.canvas.GraphicsContext;
import mrmathami.thegame.field.GameEntity;

import javax.annotation.Nonnull;

public interface EntityDrawer {
	void draw(int tickCount, @Nonnull GraphicsContext graphicsContext, @Nonnull GameEntity entity, float screenPosX, float screenPosY, float screenWidth, float screenHeight, float zoom);
}
