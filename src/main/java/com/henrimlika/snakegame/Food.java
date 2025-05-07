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

        // Load the apple image from the specified path
        Image image = new Image("/apple.png");

        // Create an ImageView and set its size
        imageView = new ImageView(image);
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);

        // Set the initial position of the food
        imageView.setX(position.getXPos());
        imageView.setY(position.getYPos());

        // Add the image to the pane
        pane.getChildren().add(imageView);
    }

    public Position getPosition() {
        return position;
    }

    public void moveFood() {
        getRandomSpotForFood();
    }

    public void getRandomSpotForFood() {
        int positionX = random.nextInt(12);   // Grid position X (12 grid cells)
        int positionY = random.nextInt(12);   // Grid position Y (12 grid cells)

        // Update the position of the ImageView
        imageView.setX(positionX * size);
        imageView.setY(positionY * size);

        // Update the position object as well
        position.setXPos(positionX * size);
        position.setYPos(positionY * size);

        System.out.println((positionX * size) + "---FOOD---" + (positionY * size));
    }
}