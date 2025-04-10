package com.henrimlika.snakegame;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    private final Double snakeSize = 50.;
    private ImageView snakeHead;
    private ImageView snakeTail_1;
    double xPos;
    double yPos;

    Food food;
    private Direction direction;
    private final List<Position> positions = new ArrayList<>();
    private final ArrayList<ImageView> snakeBody = new ArrayList<>();
    private int gameTicks;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button startButton;

    Timeline timeline;
    private boolean canChangeDirection;

    private final Image headImage = new Image(getClass().getResource("/Snake_Head.png").toExternalForm());
    private final Image bodyImage = new Image(getClass().getResource("/Snake_Body.png").toExternalForm());

    @FXML
    void start(MouseEvent event) {
        for (ImageView part : snakeBody) {
            anchorPane.getChildren().remove(part);
        }

        gameTicks = 0;
        positions.clear();
        snakeBody.clear();

        snakeHead = new ImageView(headImage);
        snakeHead.setFitWidth(snakeSize);
        snakeHead.setFitHeight(snakeSize);
        snakeHead.setX(250);
        snakeHead.setY(250);

        snakeTail_1 = new ImageView(bodyImage);
        snakeTail_1.setFitWidth(snakeSize);
        snakeTail_1.setFitHeight(snakeSize);
        snakeTail_1.setX(250 - snakeSize);
        snakeTail_1.setY(250);

        xPos = snakeHead.getLayoutX();
        yPos = snakeHead.getLayoutY();
        direction = Direction.RIGHT;
        canChangeDirection = true;
        food.moveFood();

        snakeBody.add(snakeHead);
        snakeBody.add(snakeTail_1);
        anchorPane.getChildren().addAll(snakeHead, snakeTail_1);

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.3), e -> {
            positions.add(new Position(snakeHead.getX() + xPos, snakeHead.getY() + yPos));
            moveSnakeHead(snakeHead);
            for (int i = 1; i < snakeBody.size(); i++) {
                moveSnakeTail(snakeBody.get(i), i);
            }
            canChangeDirection = true;
            eatFood();
            gameTicks++;
            if (checkIfGameIsOver(snakeHead)) {
                timeline.stop();
            }
        }));
        food = new Food(-50, -50, anchorPane, snakeSize);
    }

    @FXML
    void moveSquareKeyPressed(KeyEvent event) {
        if (canChangeDirection) {
            if (event.getCode().equals(KeyCode.W) && direction != Direction.DOWN) direction = Direction.UP;
            else if (event.getCode().equals(KeyCode.S) && direction != Direction.UP) direction = Direction.DOWN;
            else if (event.getCode().equals(KeyCode.A) && direction != Direction.RIGHT) direction = Direction.LEFT;
            else if (event.getCode().equals(KeyCode.D) && direction != Direction.LEFT) direction = Direction.RIGHT;
            canChangeDirection = false;
        }
    }

    @FXML
    void addBodyPart(ActionEvent event) {
        addSnakeTail();
    }

    private void moveSnakeHead(ImageView head) {
        if (direction == Direction.RIGHT) {
            xPos += snakeSize;
            head.setTranslateX(xPos);
        } else if (direction == Direction.LEFT) {
            xPos -= snakeSize;
            head.setTranslateX(xPos);
        } else if (direction == Direction.UP) {
            yPos -= snakeSize;
            head.setTranslateY(yPos);
        } else if (direction == Direction.DOWN) {
            yPos += snakeSize;
            head.setTranslateY(yPos);
        }
    }

    private void moveSnakeTail(ImageView tail, int tailNumber) {
        double y = positions.get(gameTicks - tailNumber + 1).getYPos() - tail.getY();
        double x = positions.get(gameTicks - tailNumber + 1).getXPos() - tail.getX();
        tail.setTranslateX(x);
        tail.setTranslateY(y);
    }

    private void addSnakeTail() {
        ImageView newTail = new ImageView(bodyImage);
        newTail.setFitWidth(snakeSize);
        newTail.setFitHeight(snakeSize);
        newTail.setX(snakeBody.get(1).getX() + xPos + snakeSize);
        newTail.setY(snakeBody.get(1).getY() + yPos);

        snakeBody.add(newTail);
        anchorPane.getChildren().add(newTail);
    }

    public boolean checkIfGameIsOver(ImageView head) {
        if (xPos > 300 || xPos < -250 || yPos < -250 || yPos > 300) {
            System.out.println("Game_over");
            return true;
        } else return snakeHitItSelf();
    }

    public boolean snakeHitItSelf() {
        int size = positions.size() - 1;
        if (size > 2) {
            for (int i = size - snakeBody.size(); i < size; i++) {
                if (positions.get(size).getXPos() == positions.get(i).getXPos() &&
                        positions.get(size).getYPos() == positions.get(i).getYPos()) {
                    System.out.println("Hit");
                    return true;
                }
            }
        }
        return false;
    }

    private void eatFood() {
        if (xPos + snakeHead.getX() == food.getPosition().getXPos()
                && yPos + snakeHead.getY() == food.getPosition().getYPos()) {
            System.out.println("Eat food");
            foodCantSpawnInsideSnake();
            addSnakeTail();
        }
    }

    private void foodCantSpawnInsideSnake() {
        food.moveFood();
        while (isFoodInsideSnake()) {
            food.moveFood();
        }
    }

    private boolean isFoodInsideSnake() {
        int size = positions.size();
        if (size > 2) {
            for (int i = size - snakeBody.size(); i < size; i++) {
                if (food.getPosition().getXPos() == positions.get(i).getXPos()
                        && food.getPosition().getYPos() == positions.get(i).getYPos()) {
                    System.out.println("Inside");
                    return true;
                }
            }
        }
        return false;
    }
}
