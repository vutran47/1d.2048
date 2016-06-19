package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.Random;

import static sample.Controller.iSet;
import static sample.Controller.XTile;
import static sample.Controller.group;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{

        group = new Group();
        group.prefHeight(270);
        group.prefWidth(270);

        // Prepare a dark background
        Rectangle background = new Rectangle(270,270,Color.DARKGREY);
        group.getChildren().add(background);

        // Prepare tile placeholders
        for (int i = 0; i < 5 ; i++) {
            for (int j = 0; j < 5; j++) {
                Rectangle rect = new Rectangle(50,50, Paint.valueOf("#F0F0F0"));
                group.getChildren().add(rect);
                rect.setX(53*i+4);
                rect.setY(53*j+4);
                rect.setArcWidth(4);
                rect.setArcHeight(4);
            }
        }

        // PREPARE RANDOM TILES
        // static import Controller.iSet
        // make column and row index become properties of the instance
        // so I can get them out and sort them whenever required
        while (iSet.size() < 3) {
            int i = new Random().nextInt(25);
            if (iSet.add(i)) {
                Tile tile = new Tile(i ,new Random().nextDouble() > 0.9 ? 4 : 2);
                group.getChildren().add(tile);
                tile.setLayoutCordinate();
                XTile.add(tile);
            }
        }

        // Attach all to the parent layout
        Scene scene = new Scene(new BorderPane(group), 350, 350);
        primaryStage.setScene(scene);
        primaryStage.show();

        //region EVENT HANDLING
        scene.setOnKeyPressed(Controller::move_tile);

        //endregion
    }


    public static void main(String[] args) {
        launch(args);
    }


}
