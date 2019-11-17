package mrmathami.thegame.drawer;

import javafx.scene.canvas.GraphicsContext;
import mrmathami.thegame.Config;
import mrmathami.thegame.LoadedImage;
import mrmathami.thegame.entity.GameEntity;
import mrmathami.thegame.entity.enemy.BossEnemy;
import mrmathami.thegame.entity.enemy.NormalEnemy;
import mrmathami.thegame.entity.enemy.TankerEnemy;
import mrmathami.thegame.entity.Explosion;

import javax.annotation.Nonnull;

public class ExplosionDrawer implements EntityDrawer {
    @Override
    public void draw(long tickCount, @Nonnull GraphicsContext graphicsContext, @Nonnull GameEntity entity, double screenPosX, double screenPosY, double screenWidth, double screenHeight, double zoom) {
        int index = 0;
        Class<?> c = ((Explosion) entity).enemyClass;
        if (c.equals(BossEnemy.class)) index = 3;
        else if (c.equals(TankerEnemy.class)) index = 2;
        else if (c.equals(NormalEnemy.class)) index = 1;
        graphicsContext.setGlobalAlpha(((Explosion) entity).tickDown / (2.0 * Config.GAME_TPS));
        graphicsContext.drawImage(LoadedImage.EXPLOSIONS[index], screenPosX - entity.getWidth() / 2 * Config.TILE_SIZE, screenPosY - entity.getHeight() * Config.TILE_SIZE / 2, screenWidth, screenHeight);
        graphicsContext.setGlobalAlpha(1);
    }
}
