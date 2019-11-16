package mrmathami.thegame.drawer;

import javafx.scene.canvas.GraphicsContext;
import mrmathami.thegame.LoadedImage;
import mrmathami.thegame.entity.GameEntity;

import javax.annotation.Nonnull;

public class TreeDrawer implements EntityDrawer  {
    @Override
    public void draw(long tickCount, @Nonnull GraphicsContext graphicsContext, @Nonnull GameEntity entity, double screenPosX, double screenPosY, double screenWidth, double screenHeight, double zoom) {
        graphicsContext.drawImage(LoadedImage.TREE, screenPosX, screenPosY, screenWidth, screenHeight);
    }
}
