package mrmathami.thegame.drawer;

import javafx.scene.canvas.GraphicsContext;
import mrmathami.thegame.LoadedImage;
import mrmathami.thegame.entity.GameEntity;
import mrmathami.thegame.entity.tile.Road;

import javax.annotation.Nonnull;

public final class RoadDrawer implements EntityDrawer {
    @Override
    public void draw(long tickCount, @Nonnull GraphicsContext graphicsContext, @Nonnull GameEntity entity, double screenPosX, double screenPosY, double screenWidth, double screenHeight, double zoom) {
//		graphicsContext.setFill(Color.LIGHTGREEN);
//		graphicsContext.fillRect(screenPosX, screenPosY, screenWidth, screenHeight);
//		if (entity instanceof Road) {
//			graphicsContext.setFill(Color.BLACK);
//			graphicsContext.fillText(String.format("%2.2f", ((Road) entity).getDistance()), screenPosX, screenPosY + screenHeight / 2);
//		}
        if (entity instanceof Road) {
            Road road = (Road) entity;
            switch (road.getType()) {
                case "0":
                    graphicsContext.drawImage(LoadedImage.ROAD_0, screenPosX, screenPosY, screenWidth, screenHeight);
                    break;
                case "2":
                    graphicsContext.drawImage(LoadedImage.ROAD_2, screenPosX, screenPosY, screenWidth, screenHeight);
                    break;
                case "3":
                    graphicsContext.drawImage(LoadedImage.ROAD_3, screenPosX, screenPosY, screenWidth, screenHeight);
                    break;
                case "4":
                    graphicsContext.drawImage(LoadedImage.ROAD_4, screenPosX, screenPosY, screenWidth, screenHeight);
                    break;
                case "5":
                    graphicsContext.drawImage(LoadedImage.ROAD_5, screenPosX, screenPosY, screenWidth, screenHeight);
                    break;
				case "6":
					graphicsContext.drawImage(LoadedImage.ROAD_6, screenPosX, screenPosY, screenWidth, screenHeight);
					break;
				case "7":
					graphicsContext.drawImage(LoadedImage.ROAD_7, screenPosX, screenPosY, screenWidth, screenHeight);
					break;
                case "8":
                    graphicsContext.drawImage(LoadedImage.ROAD_8, screenPosX, screenPosY, screenWidth, screenHeight);
                    break;
				case "9":
					graphicsContext.drawImage(LoadedImage.ROAD_9, screenPosX, screenPosY, screenWidth, screenHeight);
					break;
				case "a":
					graphicsContext.drawImage(LoadedImage.ROAD_A, screenPosX, screenPosY, screenWidth, screenHeight);
					break;
				case "b":
					graphicsContext.drawImage(LoadedImage.ROAD_B, screenPosX, screenPosY, screenWidth, screenHeight);
					break;
				case "c":
					graphicsContext.drawImage(LoadedImage.ROAD_C, screenPosX, screenPosY, screenWidth, screenHeight);
					break;
				case "d":
					graphicsContext.drawImage(LoadedImage.ROAD_D, screenPosX, screenPosY, screenWidth, screenHeight);
					break;
            }
        }
//        graphicsContext.drawImage(LoadedImage.ROAD, screenPosX, screenPosY, screenWidth, screenHeight);
    }
}
