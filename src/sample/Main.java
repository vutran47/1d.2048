package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application{
    
    @Override
    public void start(Stage primaryStage) throws Exception{

        Group group = new Group();
        group.prefHeight(270);
        group.prefWidth(270);

        // Prepare a dark background
        Rectangle background = new Rectangle(270,270,Color.DARKSLATEGRAY);
        group.getChildren().add(background);
        background.setX(0);
        background.setY(0);

        // Prepare tile placeholders
        Rectangle rect_ray[][] = new Rectangle[5][5];
        for (int i = 0; i < 5 ; i++) {
            for (int j = 0; j < 5; j++) {
                rect_ray[i][j] = new Rectangle(50,50,Color.DARKGREY);
                group.getChildren().add(rect_ray[i][j]);
                rect_ray[i][j].setX(53*i+4);
                rect_ray[i][j].setY(53*j+4);
                rect_ray[i][j].setArcWidth(4);
                rect_ray[i][j].setArcHeight(4);
            }
        }


        // Attach all to the parent layout
        Scene scene = new Scene(new BorderPane(group), 350, 350);
        primaryStage.setScene(scene);
        primaryStage.show();





    }





    public static void main(String[] args) {
        launch(args);
    }


}
