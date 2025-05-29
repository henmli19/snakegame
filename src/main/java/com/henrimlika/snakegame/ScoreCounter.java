package com.henrimlika.snakegame;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * ScoreCounter class that tracks and displays the score in the Snake game.
 */
public class ScoreCounter {

    private int score;
    private final Label scoreLabel;

    /**
     * Constructor that initializes the score and adds the label to the game UI.
     *
     * @param anchorPane The pane where the score label should be added.
     */
    public ScoreCounter(AnchorPane anchorPane) {
        score = 0;
        scoreLabel = new Label("Score: 0");
        scoreLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
        scoreLabel.setLayoutX(10);
        scoreLabel.setLayoutY(10);
        anchorPane.getChildren().add(scoreLabel);
    }

    /**
     * Increases the score by 1 and updates the label.
     */
    public void increaseScore() {
        score++;
        updateLabel();
    }

    /**
     * Resets the score to 0 and updates the label.
     */
    public void resetScore() {
        score = 0;
        updateLabel();
    }

    /**
     * Returns the current score.
     *
     * @return The current score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Updates the score label with the current score.
     */
    private void updateLabel() {
        scoreLabel.setText("Score: " + score);
    }
}