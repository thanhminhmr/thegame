package mrmathami.thegame;

public final class Config {
	public static final String GAME_NAME = "The Game";
	public static final int GAME_TPS = 30;
	public static final long GAME_NSPT = Math.round(1000000000.0 / GAME_TPS);

	public static final int TILE_SIZE = 24;
	public static final int TILE_HORIZONTAL = 40;
	public static final int TILE_VERTICAL = 30;

	public static final int SCREEN_WIDTH = TILE_SIZE * TILE_HORIZONTAL;
	public static final int SCREEN_HEIGHT = TILE_SIZE * TILE_VERTICAL;


	public static final int FLARE_TICK_DOWN = GAME_TPS / 2;
	public static final int FLARE_TICK_SPREAD = FLARE_TICK_DOWN - GAME_TPS / 15;


	private Config() {
	}


}
