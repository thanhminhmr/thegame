package mrmathami.thegame;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.WindowEvent;
import mrmathami.thegame.drawer.GameDrawer;
import mrmathami.thegame.field.GameField;
import mrmathami.thegame.field.entity.Player;
import mrmathami.thegame.field.tile.Wall;
import mrmathami.utilities.ThreadFactoryBuilder;

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

	private Player player;
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
	 *
	 * @param graphicsContext the screen to draw on
	 */
	public GameController(GraphicsContext graphicsContext) {
		// The screen to draw on
		this.graphicsContext = graphicsContext;

		// Just a few acronyms.
		final int width = Config.TILE_HORIZONTAL;
		final int height = Config.TILE_VERTICAL;

		this.player = new Player(0, 1.0f, 1.0f, 0.9f, 0.9f, 10000.0f);

		// The game field. Please consider create another way to load a game field.
		// I don't have much time, so, spawn some wall then :)
		this.field = new GameField(width, height);
		field.doSpawn(new Wall(0, 0, 0, width, 1));
		field.doSpawn(new Wall(0, 0, height - 1, width, 1));
		field.doSpawn(new Wall(0, 0, 1, 1, height - 2));
		field.doSpawn(new Wall(0, width - 1, 1, 1, height - 2));
		field.doSpawn(player);

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
	 *
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

	final void closeRequestHandler(WindowEvent windowEvent) {
		scheduledFuture.cancel(true);
		stop();
		Platform.exit();
		System.exit(0);
	}

//	final void mouseClickHandler(MouseEvent mouseEvent) {
////		final Random random = new Random();
////		field.doSpawn(new Bomb(
////				field.getTickCount(),
////				random.nextInt(Config.TILE_HORIZONTAL - 2) + 1,
////				random.nextInt(Config.TILE_VERTICAL - 2) + 1,
////				30.0f,
////				10,
////				Config.GAME_TPS * 3,
////				1.0f
////		));
//	}

	final void keyDownHandler(KeyEvent keyEvent) {
		final KeyCode keyCode = keyEvent.getCode();
		if (keyCode == KeyCode.W) {
			player.onKeyDown(Player.KEY_UP);
		} else if (keyCode == KeyCode.S) {
			player.onKeyDown(Player.KEY_DOWN);
		} else if (keyCode == KeyCode.A) {
			player.onKeyDown(Player.KEY_LEFT);
		} else if (keyCode == KeyCode.D) {
			player.onKeyDown(Player.KEY_RIGHT);
		} else if (keyCode == KeyCode.I) {
			player.onKeyDown(Player.KEY_A);
		} else if (keyCode == KeyCode.J) {
			player.onKeyDown(Player.KEY_B);
		} else if (keyCode == KeyCode.K) {
			player.onKeyDown(Player.KEY_X);
		} else if (keyCode == KeyCode.L) {
			player.onKeyDown(Player.KEY_Y);
		}
	}

	final void keyUpHandler(KeyEvent keyEvent) {
		final KeyCode keyCode = keyEvent.getCode();
		if (keyCode == KeyCode.W) {
			player.onKeyUp(Player.KEY_UP);
		} else if (keyCode == KeyCode.S) {
			player.onKeyUp(Player.KEY_DOWN);
		} else if (keyCode == KeyCode.A) {
			player.onKeyUp(Player.KEY_LEFT);
		} else if (keyCode == KeyCode.D) {
			player.onKeyUp(Player.KEY_RIGHT);
		} else if (keyCode == KeyCode.I) {
			player.onKeyUp(Player.KEY_A);
		} else if (keyCode == KeyCode.J) {
			player.onKeyUp(Player.KEY_B);
		} else if (keyCode == KeyCode.K) {
			player.onKeyUp(Player.KEY_X);
		} else if (keyCode == KeyCode.L) {
			player.onKeyUp(Player.KEY_Y);
		}
	}

	final void mouseDownHandler(MouseEvent mouseEvent) {
	}

	final void mouseUpHandler(MouseEvent mouseEvent) {
	}
}
