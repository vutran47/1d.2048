package sample;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.stream.Collectors;

public class Controller {
    static HashSet<Integer> iSet = new HashSet<>();
    static ArrayList<Tile> XTile = new ArrayList<>();

    static void move_tile (KeyEvent ke) {

        switch (ke.getCode()) {
            case UP:
                for (int row = 0; row < 5; row++) {
                    int rowz = row;
                    for (Tile xtile : XTile.stream().filter(tile -> rowz == tile.getJ_()).collect(Collectors.toCollection(ArrayList::new))) {
                        System.out.printf("\nRow of this = %d", xtile.getJ_());

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


class Logic_move {
    static void move_to_bound (KeyCode kc, Tile tile) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(200),tile);
        switch (kc) {
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