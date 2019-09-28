package mrmathami.thegame;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.WindowEvent;
import mrmathami.thegame.drawer.GameDrawer;
import mrmathami.thegame.field.GameField;
import mrmathami.thegame.field.tile.Bomb;
import mrmathami.thegame.field.tile.Wall;
import mrmathami.utilities.ThreadFactoryBuilder;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public final class GameController extends AnimationTimer {
	/**
	 * Advance stuff. Just don't touch me. Google me if you are curious.
	 */
	private static final ScheduledExecutorService SCHEDULER = Executors.newSingleThreadScheduledExecutor(
			new ThreadFactoryBuilder()
					.setNamePrefix("Tick")
					.setDaemon(true)
					.setPriority(Thread.NORM_PRIORITY)
					.build()
	);

	/**
	 * The screen to draw on. Just don't touch me. Google me if you are curious.
	 */
	private final GraphicsContext graphicsContext;

	/**
	 * Game field. Contain everything in the current game field.
	 * Responsible to update the field every tick.
	 */
	private GameField field;
	/**
	 * Game drawer. Responsible to draw the field every tick.
	 */
	private GameDrawer drawer;

	/**
	 * Beat-keeper Manager. Just don't touch me. Google me if you are curious.
	 */
	private ScheduledFuture<?> scheduledFuture;

	/**
	 * The tick value. Just don't touch me.
	 * Google me if you are curious, especially about volatile.
	 * WARNING: Wall of text.
	 */
	private volatile int tick;

	/**
	 * The constructor.
	 * @param graphicsContext the screen to draw on
	 */
	public GameController(GraphicsContext graphicsContext) {
		// The screen to draw on
		this.graphicsContext = graphicsContext;

		// Just a few acronyms.
		final float width = Config.TILE_HORIZONTAL;
		final float height = Config.TILE_VERTICAL;

		// The game field. Please consider create another way to load a game field.
		// I don't have much time, so, spawn some wall then :)
		this.field = new GameField(width, height);
		field.doSpawn(new Wall(0, 0.0f, 0.0f, width, 1.0f));
		field.doSpawn(new Wall(0, 0.0f, height - 1.0f, width, 1.0f));
		field.doSpawn(new Wall(0, 0.0f, 1.0f, 1.0f, height - 2.0f));
		field.doSpawn(new Wall(0, width - 1.0f, 1.0f, 1.0f, height - 2.0f));

		// The drawer. Nothing fun here.
		this.drawer = new GameDrawer(graphicsContext, field);
	}

	/**
	 * Beat-keeper. Just don't touch me.
	 */
	private void tick() {
		//noinspection NonAtomicOperationOnVolatileField
		this.tick += 1;
	}

	/**
	 * A JavaFX loop. Just don't touch me.
	 * @param now not used. It is a timestamp in nanosecond.
	 */
	@Override
	public void handle(long now) {
		final int currentTick = tick;
		final long startNs = System.nanoTime();

		// do a tick
		field.tick();

//		// if it's too late to draw a new frame, skip it
//		if (currentTick != tick) return;

		// draw a new frame
		drawer.render(0.0f, 0.0f, Config.TILE_SIZE);

		// MSPT display, see Config for more information
		final float mspt = (System.nanoTime() - startNs) / 1000000.0f;
		graphicsContext.setFill(Color.BLACK);
		graphicsContext.fillText(String.format("MSPT: %3.2f", mspt), 0, 12);

		// if we have time to spend, do a spin
		while (currentTick == tick) Thread.onSpinWait();
	}

	/**
	 * Start rendering and ticking. Just don't touch me.
	 * Anything that need to initialize should be in the constructor.
	 */
	@Override
	public void start() {
		// Start the beat-keeper. Start to call this::tick at a fixed rate.
		this.scheduledFuture = SCHEDULER.scheduleAtFixedRate(this::tick, 0, Config.GAME_NSPT, TimeUnit.NANOSECONDS);
		// start the JavaFX loop.
		super.start();
	}

	public final void closeRequestHandler(WindowEvent windowEvent) {
		scheduledFuture.cancel(true);
		stop();
		Platform.exit();
		System.exit(0);
	}

	public final void mouseClickHandler(MouseEvent mouseEvent) {
		final Random random = new Random();
		field.doSpawn(new Bomb(
				field.getTickCount(),
				random.nextInt(Config.TILE_HORIZONTAL - 2) + 1,
				random.nextInt(Config.TILE_VERTICAL - 2) + 1,
				30.0f,
				10,
				Config.GAME_TPS * 3,
				1.0f
		));
	}

	public final void keyPressHandler(KeyEvent keyEvent) {
	}

}
