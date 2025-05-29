package com.henrimlika.snakegame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.util.Random;

public class Food {
    private final Position position;
    private final ImageView imageView;
    private final Random random = new Random();
    private final int size;

    public Food(double xPos, double yPos, AnchorPane pane, double size) {
        this.size = (int) size;
        position = new Position(xPos, yPos);

        Image image = new Image("/apple.png");


        imageView = new ImageView(image);
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);

        imageView.setX(position.getXPos());
        imageView.setY(position.getYPos());

        pane.getChildren().add(imageView);
    }

    public Position getPosition() {
        return position;
    }

    public void moveFood() {
        getRandomSpotForFood();
    }

    public void getRandomSpotForFood() {
        int positionX = random.nextInt(12);
        int positionY = random.nextInt(12);


        imageView.setX(positionX * size);
        imageView.setY(positionY * size);


        position.setXPos(positionX * size);
        position.setYPos(positionY * size);

        System.out.println((positionX * size) + "---FOOD---" + (positionY * size));
    }
}