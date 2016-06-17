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
import static sample.Controller.tile_ray;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{

        Group group = new Group();
        group.prefHeight(270);
        group.prefWidth(270);

        // Prepare a dark background
        Rectangle background = new Rectangle(270,270,Color.DARKGREY);
        group.getChildren().add(background);
        background.setX(0);
        background.setY(0);

        // Prepare tile placeholders
        Rectangle rect_ray[][] = new Rectangle[5][5];
        for (int i = 0; i < 5 ; i++) {
            for (int j = 0; j < 5; j++) {
                rect_ray[i][j] = new Rectangle(50,50,Color.LIGHTYELLOW);
                group.getChildren().add(rect_ray[i][j]);
                rect_ray[i][j].setX(53*i+4);
                rect_ray[i][j].setY(53*j+4);
                rect_ray[i][j].setArcWidth(4);
                rect_ray[i][j].setArcHeight(4);
            }
        }

        //region PREPARE RANDOM TILES

        // static import Controller.iSet
        int count = 0;
        while (iSet.size() < 3) {
            int i = new Random().nextInt(25);
            boolean check = iSet.add(i);
            if (check) System.out.println("inserted a number in iSet = " + i);
        }

        for (int i = 0; i < 5 ; i++) { // i being COLUMN
            for (int j = 0; j < 5; j++) { // j being ROW
                if (count < 3 && iSet.contains(i*5+j)) {
                    System.out.printf("i x j = %d x %d \n", i, j );
                    tile_ray[i][j] = new Tile(50,50,Color.DARKORCHID);
                    group.getChildren().add(tile_ray[i][j]);
                    tile_ray[i][j].setLayoutX(53*i+4);
                    tile_ray[i][j].setLayoutY(53*j+4);
                    tile_ray[i][j].setValue(new Random().nextDouble() > 0.8 ? 4 : 2);
                    count++;
                }
            }
        }
        //endregion



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
