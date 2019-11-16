package mrmathami.thegame.drawer;

import javafx.scene.canvas.GraphicsContext;
import mrmathami.thegame.LoadedImage;
import mrmathami.thegame.entity.GameEntity;
import mrmathami.thegame.entity.tile.Obstacle;

import javax.annotation.Nonnull;

public class ObstacleDrawer implements EntityDrawer  {
    @Override
    public void draw(long tickCount, @Nonnull GraphicsContext graphicsContext, @Nonnull GameEntity entity, double screenPosX, double screenPosY, double screenWidth, double screenHeight, double zoom) {
        if (entity.getClass().equals(Obstacle.class))
            graphicsContext.drawImage(LoadedImage.OBSTACLES[((Obstacle) entity).index], screenPosX, screenPosY, screenWidth, screenHeight);
    }
}
