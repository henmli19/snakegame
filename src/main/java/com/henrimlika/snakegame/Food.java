package com.henrimlika.snakegame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Random;

public class Food {
    private static final String FOOD_IMAGE_PATH = "/apple.png";

    private final Position position;
    private final ImageView imageView;
    private final Random random = new Random();
    private final int gridSize;

    public Food(double x, double y, AnchorPane pane, double size) {
        this.gridSize = (int) size;
        this.position = new Position(x, y);

        Image foodImage = new Image(FOOD_IMAGE_PATH);
        this.imageView = new ImageView(foodImage);
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
        updatePositionRandomly();
    }

    private void updatePositionRandomly() {
        int randomX = random.nextInt(12);
        int randomY = random.nextInt(12);

        double newX = randomX * gridSize;
        double newY = randomY * gridSize;

        imageView.setX(newX);
        imageView.setY(newY);

        position.setXPos(newX);
        position.setYPos(newY);

        System.out.println(newX + " ---FOOD--- " + newY);
    }
}
