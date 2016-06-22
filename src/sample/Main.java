package sample;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

import static sample.Controller.*;

public class Main extends Application{
    private static Scene scene;
    private EventHandler<KeyEvent> mover = Controller::move_tile;
    static int glb = 3;
    static int slb = 1;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Controller._main = this;
        group = new Group();
        group.prefHeight(53*glb+5);
        group.prefWidth(53*glb+5);

        // Prepare a dark background
        Rectangle background = new Rectangle(53*glb+5,53*glb+5,Color.DARKGREY);
        group.getChildren().add(background);

        // Prepare tile placeholders
        for (int i = 0; i < glb ; i++) {
            for (int j = 0; j < glb; j++) {
                Rectangle rect = new Rectangle(50,50, Paint.valueOf("#F0F0F0"));
                group.getChildren().add(rect);
                rect.setX(53*i+4);
                rect.setY(53*j+4);
                rect.setArcWidth(4);
                rect.setArcHeight(4);
            }
        }

        // PREPARE SOME RANDOM TILES
        // make column and row index become properties of the instance
        // so I can get them out and sort them whenever required
        while (iSet.size() < 3) {
            int i = new Random().nextInt(glb*glb);
            if (iSet.add(i)) {
                Tile tile = new Tile(i ,new Random().nextDouble() > 0.9 ? 4 : 2);
                group.getChildren().add(tile);
                tile.setLayoutCordinate();
                XTile.add(tile);
            }
        }

        // Attach all to the parent layout
        scene = new Scene(new BorderPane(group), 53*glb+70, 53*glb+70);
        primaryStage.setScene(scene);
        primaryStage.setTitle("2048 Bach Mai version");
        primaryStage.setResizable(false);
        primaryStage.show();

        // EVENT HANDLING
        scene.addEventHandler(KeyEvent.KEY_PRESSED, this.mover);
    }

    void gameover_annouce (boolean gameIsOver) {
        if (gameIsOver) {
            //region Game over handling
            scene.removeEventHandler(KeyEvent.KEY_PRESSED, mover);

            // Prepare the Vbox contain Message and New game button
            Label descrip = new Label("GAME OVER\nAnh Hiếu, anh NGU lắm!\nLàm game mà ko có Animation thì \nvứt cho dog ăn!\nNGU");
            descrip.setTextAlignment(TextAlignment.CENTER);

            Button newgame = new Button("Try again!");
            newgame.setPrefSize(100,20);
            newgame.setTextFill(Color.WHITE);
            newgame.setFont(Font.font(14));
            newgame.setTextAlignment(TextAlignment.CENTER);
            newgame.setBackground(new Background(new BackgroundFill(Color.DARKCYAN, new CornerRadii(5), null)));

            VBox vbox = new VBox(20);
            vbox.getChildren().addAll(descrip,newgame);
            vbox.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            vbox.setOpacity(0);
            vbox.setAlignment(Pos.CENTER);
            group.getChildren().add(vbox);
            vbox.setPrefSize(53*glb+5,53*glb+5);

            // Animation for the Vbox to show
            FadeTransition ft = new FadeTransition(Duration.millis(100),vbox);
            ft.setDelay(Duration.millis(1000));
            ft.setFromValue(0);
            ft.setToValue(0.75);
            ft.play();

            ScaleTransition st = new ScaleTransition(Duration.millis(100),vbox);
            st.setDelay(Duration.millis(1000));
            st.setFromY(0.8);
            st.setFromX(0.8);
            st.setToY(1);
            st.setToX(1);
            st.play();

            // Assign Action for the Try Again button
            newgame.setOnAction(event -> {
                FadeTransition ft2 = new FadeTransition(Duration.millis(500),vbox);
                ft2.setToValue(0);
                ft2.play();
                ft2.setOnFinished(event1 -> {
                    group.getChildren().remove(vbox);
                    Controller.clear_all();
                    scene.addEventHandler(KeyEvent.KEY_PRESSED, mover);
                });
            });
            //endregion
        } else {
            Controller.animationPlaying = false;
        }
    }



    public static void main(String[] args) {
        launch(args);
    }


}
