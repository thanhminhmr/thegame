package mrmathami.thegame.drawer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import mrmathami.thegame.Config;
import mrmathami.thegame.drawer.tile.BombDrawer;
import mrmathami.thegame.drawer.tile.FlareDrawer;
import mrmathami.thegame.drawer.Entity.PlayerDrawer;
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
	@Nonnull private GameField gameField;
	private transient float fieldStartPosX = Float.NaN;
	private transient float fieldStartPosY = Float.NaN;
	private transient float fieldZoom = Float.NaN;

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

	public final float getFieldStartPosX() {
		return fieldStartPosX;
	}

	public final float getFieldStartPosY() {
		return fieldStartPosY;
	}

	public final float getFieldZoom() {
		return fieldZoom;
	}

	public final void setGameField(@Nonnull GameField gameField) {
		this.gameField = gameField;
	}

	public final void setFieldViewRegion(float fieldStartPosX, float fieldStartPosY, float fieldZoom) {
		this.fieldStartPosX = fieldStartPosX;
		this.fieldStartPosY = fieldStartPosY;
		this.fieldZoom = fieldZoom;
	}

	public final void render() {
		final GameField gameField = this.gameField;
		final float fieldStartPosX = this.fieldStartPosX;
		final float fieldStartPosY = this.fieldStartPosY;
		final float fieldZoom = this.fieldZoom;

		final List<GameEntity> entities = new ArrayList<>(GameEntities.getOverlappedEntities(gameField.getEntities(),
				fieldStartPosX, fieldStartPosY, Config.SCREEN_WIDTH / fieldZoom, Config.SCREEN_HEIGHT / fieldZoom));
		entities.sort(GameDrawer::entityDrawingOrderComparator);

		graphicsContext.setFill(Color.BLACK);
		graphicsContext.fillRect(0.0f, 0.0f, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);

		GameEntity lastEntity = null;
		for (final GameEntity entity : entities) {
			if (lastEntity != null && entityDrawingOrderComparator(entity, lastEntity) == 0) continue;
			lastEntity = entity;
			final EntityDrawer drawer = getEntityDrawer(entity);
			if (drawer != null) {
				drawer.draw(
						graphicsContext, entity,
						(entity.getPosX() - fieldStartPosX) * fieldZoom,
						(entity.getPosY() - fieldStartPosY) * fieldZoom,
						entity.getWidth() * fieldZoom,
						entity.getHeight() * fieldZoom,
						fieldZoom
				);
			}
		}
	}

	public final float screenToFieldPosX(float screenPosX) {
		return screenPosX * fieldZoom + fieldStartPosX;
	}

	public final float screenToFieldPosY(float screenPosY) {
		return screenPosY * fieldZoom + fieldStartPosY;
	}

	public final float fieldToScreenPosX(float fieldPosX) {
		return (fieldPosX - fieldStartPosX) / fieldZoom;
	}

	public final float fieldToScreenPosY(float fieldPosY) {
		return (fieldPosY - fieldStartPosY) / fieldZoom;
	}
}
