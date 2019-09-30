package mrmathami.thegame.drawer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import mrmathami.thegame.Config;
import mrmathami.thegame.drawer.tile.BombDrawer;
import mrmathami.thegame.drawer.tile.FlareDrawer;
import mrmathami.thegame.drawer.tile.PlayerDrawer;
import mrmathami.thegame.drawer.tile.WallDrawer;
import mrmathami.thegame.field.GameEntities;
import mrmathami.thegame.field.GameEntity;
import mrmathami.thegame.field.GameField;
import mrmathami.thegame.field.entity.Player;
import mrmathami.thegame.field.tile.Bomb;
import mrmathami.thegame.field.tile.Flare;
import mrmathami.thegame.field.tile.Wall;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class GameDrawer {
	@Nonnull private static final List<Class<?>> ENTITY_DRAWING_ORDER = List.of(
			Bomb.class,
			Player.class,
			Flare.class,
			Wall.class
	);
	@Nonnull private static final Map<Class<?>, EntityDrawer> ENTITY_DRAWER_MAP = Map.of(
			Bomb.class, new BombDrawer(),
			Player.class, new PlayerDrawer(),
			Flare.class, new FlareDrawer(),
			Wall.class, new WallDrawer()
	);

	@Nonnull private final GraphicsContext graphicsContext;
	@Nonnull private final GameField gameField;

	public GameDrawer(@Nonnull GraphicsContext graphicsContext, @Nonnull GameField gameField) {
		this.graphicsContext = graphicsContext;
		this.gameField = gameField;
	}

	public static int entityDrawingOrderComparator(@Nonnull GameEntity entityA, @Nonnull GameEntity entityB) {
		final int compareOrder = Integer.compare(
				ENTITY_DRAWING_ORDER.indexOf(entityA.getClass()),
				ENTITY_DRAWING_ORDER.indexOf(entityB.getClass())
		);
		if (compareOrder != 0) return compareOrder;
		final int comparePosX = Float.compare(entityA.getPosX(), entityB.getPosX());
		if (comparePosX != 0) return comparePosX;
		final int comparePosY = Float.compare(entityA.getPosY(), entityB.getPosY());
		if (comparePosY != 0) return comparePosY;
		final int compareWidth = Float.compare(entityA.getWidth(), entityB.getWidth());
		if (compareWidth != 0) return compareWidth;
		return Float.compare(entityA.getHeight(), entityB.getHeight());
	}

	@Nullable
	private static EntityDrawer getEntityDrawer(@Nonnull GameEntity entity) {
		return ENTITY_DRAWER_MAP.get(entity.getClass());
	}

	public final void render(float posX, float posY, float zoom) {
		final List<GameEntity> entities = new ArrayList<>(GameEntities.getOverlappedEntities(gameField.getEntities(),
				posX, posY, Config.SCREEN_WIDTH / zoom, Config.SCREEN_HEIGHT / zoom));
		entities.sort(GameDrawer::entityDrawingOrderComparator);

		graphicsContext.setFill(Color.BLACK);
		graphicsContext.fillRect(0.0f, 0.0f, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);

		for (final GameEntity entity : entities) {
			final EntityDrawer drawer = getEntityDrawer(entity);
			if (drawer != null) {
				drawer.draw(
						graphicsContext, entity,
						(entity.getPosX() - posX) * zoom,
						(entity.getPosY() - posY) * zoom,
						entity.getWidth() * zoom,
						entity.getHeight() * zoom,
						zoom
				);
			}
		}
	}
}
