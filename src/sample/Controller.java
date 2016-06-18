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

    static void move_tile (KeyEvent ke) {

        switch (ke.getCode()) {
            case UP:

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
