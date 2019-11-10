package mrmathami.thegame.drawer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import mrmathami.thegame.entity.GameEntity;
import mrmathami.thegame.entity.enemy.NormalEnemy;

import javax.annotation.Nonnull;

public final class NormalEnemyDrawer implements EntityDrawer {
	@Override
	public void draw(long tickCount, @Nonnull GraphicsContext graphicsContext, @Nonnull GameEntity entity, double screenPosX, double screenPosY, double screenWidth, double screenHeight, double zoom) {
		graphicsContext.setFill(Color.DARKMAGENTA);
		graphicsContext.fillRoundRect(screenPosX, screenPosY, screenWidth, screenHeight, 4, 4);
		if (entity instanceof NormalEnemy) {
			NormalEnemy enemy = ((NormalEnemy) entity);
			graphicsContext.setFill(Color.RED);
			graphicsContext.fillText(String.format("%d", enemy.getHealth()), screenPosX, screenPosY, 20);
		}
	}
}
