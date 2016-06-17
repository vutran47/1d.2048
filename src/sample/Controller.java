package sample;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Controller {
    static Pane the_pane;
    static void move_tile(KeyEvent ke) {
        if (the_pane.getChildren().isEmpty()) {
            System.out.println("Cannot proceed");
            return;
        }

        System.out.println("Ok to proceed");
        Pane x = (Pane) the_pane.getChildren().get(0);

        TranslateTransition tt = new TranslateTransition(Duration.millis(1000), x);
        tt.setToY(100);
        tt.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FadeTransition ft = new FadeTransition(Duration.millis(500), x);
                ft.setFromValue(1);
                ft.setToValue(0);
                ft.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        the_pane.getChildren().remove(x);
                    }
                });
                ft.play();
            }
        });
        tt.play();
    }
}
