package sample;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.util.HashSet;

public class Controller {
    static Tile[][] tile_ray = new Tile[5][5];
    static HashSet<Integer> iSet = new HashSet<>();

    static void move_tile(KeyEvent ke) {



        switch (ke.getCode()) {
            case UP:
                for (Integer integer : iSet) {
                    int i = (int)(Math.floorDiv(integer,5));
                    int j = (integer - 5*i);
                    System.out.printf("Gotcha: Integer = %d",integer);

                    Tile t = tile_ray[i][j];
                    
                    if (j%5 == 0) {
                        System.out.println("Hit the wall");
                    } else {
                        TranslateTransition tt = new TranslateTransition(Duration.millis(200));
                        tt.setNode(t);
                        tt.setToY(-53*j);
                        tt.play();
                        System.out.println("PLAY!");
                    }
                }
                break;
            
            case DOWN:
                break;
            
            case RIGHT:
                break;
            
            case LEFT:
                break;
        }
    }
}
