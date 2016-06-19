package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
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
        background.setX(0);
        background.setY(0);

        // Prepare tile placeholders
        for (int i = 0; i < 5 ; i++) {
            for (int j = 0; j < 5; j++) {
                Rectangle rect = new Rectangle(50,50,Color.LIGHTYELLOW);
                group.getChildren().add(rect);
                rect.setX(53*i+4);
                rect.setY(53*j+4);
                rect.setArcWidth(4);
                rect.setArcHeight(4);
            }
        }

        //region PREPARE RANDOM TILES

        // static import Controller.iSet
        int randommake = 1;
        int count = 0;
        while (iSet.size() < randommake) {
            iSet.add(new Random().nextInt(25));
        }

        // an array of Tile is not necessary,
        // make column and row index become properties of the instance
        // so I can get them out and sort them whenever required
        for (int i = 0; i < 5 ; i++) { // i being COLUMN
            for (int j = 0; j < 5; j++) { // j being ROW
                if (count < randommake && iSet.contains(i*5+j)) {
                    Tile tile = new Tile(50,50,Color.DARKORCHID, i, j);
                    group.getChildren().add(tile);
                    tile.setLayoutX(53*i+4);
                    tile.setLayoutY(53*j+4);
                    tile.setValue(new Random().nextDouble() > 0.8 ? 4 : 2);
                    count++;
                    XTile.add(tile);
                }
            }
        }
        System.out.println("Init " + XTile.size() + " tiles");
        //endregion


        // Attach all to the parent layout
        Scene scene = new Scene(new BorderPane(group), 350, 350);
        primaryStage.setScene(scene);
        primaryStage.show();

        //region EVENT HANDLING
        scene.setOnKeyPressed(Controller::move_tile);

        //endregion
    }

    void remove_a_tile (Group gp, Tile tl) {
        gp.getChildren().remove(tl);
    }





    public static void main(String[] args) {
        launch(args);
    }


}
