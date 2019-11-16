package mrmathami.thegame;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.WindowEvent;
import mrmathami.thegame.drawer.GameDrawer;
import mrmathami.thegame.entity.GameEntity;
import mrmathami.thegame.entity.enemy.AbstractEnemy;
import mrmathami.thegame.entity.tile.Mountain;
import mrmathami.thegame.entity.tile.Road;
import mrmathami.thegame.entity.tile.Target;
import mrmathami.thegame.entity.tile.spawner.AbstractSpawner;
import mrmathami.thegame.entity.tile.tower.AbstractTower;
import mrmathami.thegame.entity.tile.tower.MachineGunTower;
import mrmathami.thegame.entity.tile.tower.NormalTower;
import mrmathami.thegame.entity.tile.tower.SniperTower;
import mrmathami.utilities.ThreadFactoryBuilder;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * A game controller. Everything about the game should be managed in here.
 */
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
	 * Kinda advance, modify if you are sure about your change.
	 */
	private GameField field;
	/**
	 * Game drawer. Responsible to draw the field every tick.
	 * Kinda advance, modify if you are sure about your change.
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
	private volatile long tick;

	/**
	 * The constructor.
	 *
	 * @param graphicsContext the screen to draw on
	 */
	GameController(GraphicsContext graphicsContext) {
		// The screen to draw on
		this.graphicsContext = graphicsContext;

		// Just a few acronyms.

		// The game field. Please consider create another way to load a game field.
		// TODO: I don't have much time, so, spawn some wall then :)
		this.field = new GameField(Objects.requireNonNull(GameStage.load("/stage/demo.txt")));

		// The drawer. Nothing fun here.
		this.drawer = new GameDrawer(graphicsContext, field);

		// Field view region is a rectangle region
		// [(posX, posY), (posX + SCREEN_WIDTH / zoom, posY + SCREEN_HEIGHT / zoom)]
		// that the drawer will select and draw everything in it in an self-defined order.
		// Can be modified to support zoom in / zoom out of the map.
		drawer.setFieldViewRegion(0.0, 0.0, Config.TILE_SIZE);
		credit = new Text(String.valueOf(field.credit));
		credit.setFont(Font.font(20));
        credit.setFill(Color.YELLOW);
	}

	GameController(GraphicsContext graphicsContext, GameField other) {
		this.graphicsContext = graphicsContext;
		this.field = other;
		this.drawer = new GameDrawer(graphicsContext, field);
		drawer.setFieldViewRegion(0.0, 0.0, Config.TILE_SIZE);
		credit = new Text(String.valueOf(field.credit));
        credit.setFont(Font.font(20));
        credit.setFill(Color.YELLOW);
	}

	/**
	 * Beat-keeper. Just don't touch me.
	 */
	private void tick() {
		//noinspection NonAtomicOperationOnVolatileField
		this.tick += 1;
	}

    /**
     * A JavaFX loop.
     * Kinda advance, modify if you are sure about your change.
     *
     * @param now not used. It is a timestamp in nanosecond.
     */
    @Override
    public void handle(long now) {
        switch (getStatus()) {
            case NONE: break;
            case PAUSE: return;
            case WIN:
                graphicsContext.drawImage(LoadedImage.WIN, Config.SCREEN_WIDTH / 6, Config.SCREEN_HEIGHT / 6 , Config.SCREEN_WIDTH * 2 / 3, Config.SCREEN_HEIGHT * 2 / 3);
                if (Config.sfx) LoadedAudio.WIN.play();
                stop();
                return;
            case LOSE:
                graphicsContext.drawImage(LoadedImage.LOSE, 0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
                if (Config.sfx) LoadedAudio.LOSE.play();
                stop();
                return;
        }
        // don't touch me.
        final long currentTick = tick;
//        final long startNs = System.nanoTime();

        // do a tick, as fast as possible
        field.tick();

//		// if it's too late to draw a new frame, skip it.
//		// make the game feel really laggy, so...
		if (currentTick != tick) return;

        // draw a new frame, as fast as possible.
        drawer.render();

//        // MSPT display. MSPT stand for Milliseconds Per Tick.
//        // It means how many ms your game spend to update and then draw the game once.
//        // Draw it out mostly for debug
//        final double mspt = (System.nanoTime() - startNs) / 1000000.0;
//        graphicsContext.setFill(Color.BLACK);
//        graphicsContext.fillText(String.format("MSPT: %3.2f", mspt), 0, 12);
        credit.setText(String.valueOf(field.credit));

        if (Config.autoPlay && tick % (Config.GAME_TPS * 2) == 0) autoPlay();
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

    /**
     * On window close request.
     * Kinda advance, modify if you are sure about your change.
     *
     * @param windowEvent currently not used
     */
    final void closeRequestHandler(WindowEvent windowEvent) {
        if (status != Config.GAME_STATUS.WIN && status != Config.GAME_STATUS.LOSE) save();
        stop();
        Platform.exit();
        System.exit(0);
    }

    @Override
    public void stop() {
        scheduledFuture.cancel(true);
        super.stop();
    }

    private void save() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(Config.logPath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(field);
            objectOutputStream.close();
        } catch (Exception ignore) {}
    }

    private Text credit;

    Text getCredit() {
        return credit;
    }

    private Config.KEY_STATUS keyStatus = Config.KEY_STATUS.NONE;

    void setKey(Config.KEY_STATUS keyStatus, Cursor cursor) {
        this.keyStatus = keyStatus;
        graphicsContext.getCanvas().getParent().setCursor(cursor);
    }

    void mouseClickHandler(MouseEvent event) {
        int x = (int) drawer.screenToFieldPosX(event.getX());
        int y = (int) drawer.screenToFieldPosY(event.getY());
        if (keyStatus == Config.KEY_STATUS.NONE) return;
        var entities = GameEntities.getOverlappedEntities(field.getEntities(), x, y, 1, 1);
        for (GameEntity entity : entities)
            if (entity.getClass().equals(Road.class)) {
                setKey(Config.KEY_STATUS.NONE, Cursor.DEFAULT);
                return;
            }
        if (status != Config.GAME_STATUS.NONE) return;
        if (keyStatus == Config.KEY_STATUS.SELL) {
            var towers = GameEntities.entitiesFilter(entities, AbstractTower.class);
            for (AbstractTower tower : towers) {
                switch (tower.getClass().getSimpleName()) {
                    case "NormalTower":     field.credit += 0.8 * Config.NORMAL_TOWER_PRICE;        break;
                    case "SniperTower":     field.credit += 0.8 * Config.SNIPER_TOWER_PRICE;        break;
                    case "MachineGunTower": field.credit += 0.8 * Config.MACHINE_GUN_TOWER_PRICE;   break;
                }
                field.destroy(tower);
            }
        } else {
            for (GameEntity entity : entities)
                if (!entity.getClass().equals(Mountain.class)) return;
            switch (keyStatus) {
                case NORMAL_TOWER:
                    if (field.credit < Config.NORMAL_TOWER_PRICE) return;
                    field.doSpawn(new NormalTower(tick, x, y));
                    field.credit -= Config.NORMAL_TOWER_PRICE;
                    break;
                case MACHINE_GUN_TOWER:
                    if (field.credit < Config.MACHINE_GUN_TOWER_PRICE) return;
                    field.doSpawn(new MachineGunTower(tick, x, y));
                    field.credit -= Config.MACHINE_GUN_TOWER_PRICE;
                    break;
                case SNIPER_TOWER:
                    if (field.credit < Config.SNIPER_TOWER_PRICE) return;
                    field.doSpawn(new SniperTower(tick, x, y));
                    field.credit -= Config.SNIPER_TOWER_PRICE;
                    break;
            }
        }
    }

    private Config.GAME_STATUS status = Config.GAME_STATUS.NONE;

    Config.GAME_STATUS getStatus() {
        var targets = GameEntities.entitiesFilter(field.getEntities(), Target.class);
        if (targets.isEmpty()) status = Config.GAME_STATUS.LOSE;
        else {
            var spawns = GameEntities.entitiesFilter(field.getEntities(), AbstractSpawner.class);
            for (AbstractSpawner spawn : spawns) {
                if (spawn.getNumOfSpawn() > 0) return status;
            }
            var enemies = GameEntities.entitiesFilter(field.getEntities(), AbstractEnemy.class);
            if (enemies.isEmpty()) status = Config.GAME_STATUS.WIN;
        }
        return status;
    }

    void setStatus(Config.GAME_STATUS status) {
        this.status = status;
    }

    private void autoPlay() {
        var mountains = GameEntities.entitiesFilter(field.getEntities(), Mountain.class);
        int k = (int) (mountains.size() * Math.random());
        Mountain randomMountain = null;
        for (Mountain m : mountains)
            if (--k < 0){
                var entities = GameEntities.getOverlappedEntities(field.getEntities(), m.getPosX(), m.getPosY(), 1, 1);
                if (entities.size() > 1) return;
                randomMountain = m;
                break;
            }
        if (randomMountain == null) return;
        switch ((int) (Math.random() * 3)) {
            case 0:
                if (field.credit < Config.NORMAL_TOWER_PRICE) return;
                field.doSpawn(new NormalTower(tick, (long) randomMountain.getPosX(), (long) randomMountain.getPosY()));
                field.credit -= Config.NORMAL_TOWER_PRICE;
                break;
            case 1:
                if (field.credit < Config.SNIPER_TOWER_PRICE) return;
                field.doSpawn(new SniperTower(tick, (long) randomMountain.getPosX(), (long) randomMountain.getPosY()));
                field.credit -= Config.SNIPER_TOWER_PRICE;
                break;
            case 2:
                if (field.credit < Config.MACHINE_GUN_TOWER_PRICE) return;
                field.doSpawn(new MachineGunTower(tick, (long) randomMountain.getPosX(), (long) randomMountain.getPosY()));
                field.credit -= Config.MACHINE_GUN_TOWER_PRICE;
                break;
        }
    }
}