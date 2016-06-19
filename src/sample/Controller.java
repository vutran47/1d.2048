package sample;

import com.sun.istack.internal.NotNull;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.transform.MatrixType;
import javafx.scene.transform.TransformChangedEvent;
import javafx.util.Duration;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Controller {
    static HashSet<Integer> iSet = new HashSet<>();
    static ArrayList<Tile> XTile = new ArrayList<>();
    static Group group;

    static void move_tile (KeyEvent ke) {
        Tile tile = XTile.get(0);
        switch (ke.getCode()) {

            case UP:
                Logic_move.move_to_bound(ke.getCode(),tile);

                //spawning();
                break;
            
            case DOWN:
                Logic_move.move_to_bound(ke.getCode(),tile);


                //spawning();
                break;
            
            case RIGHT:
                Logic_move.move_to_bound(ke.getCode(),tile);


                //spawning();
                break;
            
            case LEFT:
                Logic_move.move_to_bound(ke.getCode(),tile);

                //spawning();
                break;


            //region Delete tiles
            case Q:
                if (XTile.size() > 0) {
                    int i = XTile.size();
                    Tile t = XTile.get(new Random().nextInt(i));
                    group.getChildren().remove(t);
                    XTile.remove(t);
                } else {
                    System.out.println("NO MORE TILE TO DELETE");
                }
                break;
            //endregion
        }

    }

    static void spawning () {
        if (XTile.size() == 25) {
            System.out.println("GAME OVER");
            return;
        }

        iSet.clear();
        for (Tile tile : XTile) {
            iSet.add(tile.getInc_index());
        }
        int spawnz = Math.min(25-XTile.size(), 3);
        int count = 0;
        while (count < spawnz) {
            int i = new Random().nextInt(25);
            if (!iSet.contains(i)) {
                iSet.add(i);
                count++;
                Tile tile = new Tile(i);
                tile.setValue(new Random().nextDouble() > 0.8 ? 4 : 2);
                group.getChildren().add(tile);
                tile.setLayoutX(53*tile.getI_()+4);
                tile.setLayoutY(53*tile.getJ_()+4);
                XTile.add(tile);

                ScaleTransition st = new ScaleTransition(Duration.millis(200), tile);
                st.setFromY(0);
                st.setFromX(0);
                st.setToX(1);
                st.setToY(1);

                FadeTransition ft = new FadeTransition(Duration.millis(400),tile);
                ft.setFromValue(0);
                ft.setToValue(1);

                st.play();
                ft.play();
            }
        }

    }
}


class Logic_move {
    static void move_to_bound (KeyCode kc, Tile tile) {
        switch (kc) {
            case UP:
                tile.setTranslateY(tile.getJ_()*-53);
                tile.setJ_(0);
                break;

            case DOWN:
                tile.setTranslateY((4-tile.getJ_())*53);
                tile.setJ_(4);
                break;

            case RIGHT:
                tile.setTranslateX((4-tile.getI_())*53);
                tile.setI_(4);
                break;

            case LEFT:
                tile.setTranslateX(tile.getI_()*-53);
                tile.setI_(0);
                break;
        }

        tile.setLayoutX(tile.getLayoutX() + tile.getTranslateX());
        tile.setLayoutY(tile.getLayoutY() + tile.getTranslateY());
        tile.setTranslateY(0);
        tile.setTranslateX(0);

    }

    static void move_to_nearest (@NotNull KeyCode kc,@NotNull Tile t_m, @NotNull Tile t_dc) {
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