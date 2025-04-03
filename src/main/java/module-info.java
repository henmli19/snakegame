module com.henrimlika.snakegame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.henrimlika.snakegame to javafx.fxml;
    exports com.henrimlika.snakegame;
}