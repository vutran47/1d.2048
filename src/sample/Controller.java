package sample;

import com.sun.istack.internal.NotNull;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.stream.Collectors;

public class Controller {
    // Static attributes and collections
    static HashSet<Integer> iSet = new HashSet<>();
    static ArrayList<Tile> XTile = new ArrayList<>();
    static Group group;
    private static boolean animationPlaying = false;
    static Main _main;

    
    static void move_tile (KeyEvent ke) {
        if (animationPlaying) return;
        KeyCode kc = ke.getCode();
        animationPlaying = true;
        for (Tile tile : XTile) {
            tile.setMergable(true);
        }
        switch (kc) {
            //region Prepare Data per Swipe direction
            case UP:
                for (int i = 0; i < 5; i++) { // Browse columns
                    int finalI = i;
                    ArrayList<Tile> focus = XTile.stream().filter(tile -> tile.getI_() == finalI)
                            .sorted(((o1, o2) -> o1.getJ_() - o2.getJ_()))
                            .collect(Collectors.toCollection(ArrayList<Tile>::new));

                    // if empty column
                    if (focus.size() == 0) continue;

                    // if not empty, move first tile to boundary
                    Logic_move.move_to_bound(kc, focus.get(0));

                    // then take care of the rest in the column by row
                    for (int j = 1; j < focus.size(); j++) {
                        // move them by pairs
                        Logic_move.move_to_nearest(kc, focus.get(j), focus.get(j-1));
                    }
                }

                break;
            
            case DOWN:
                for (int i = 0; i < 5; i++) { // Browse columns
                    int finalI = i;
                    ArrayList<Tile> focus = XTile.stream().filter(tile -> tile.getI_() == finalI)
                            .sorted(((o1, o2) -> o2.getJ_() - o1.getJ_()))
                            .collect(Collectors.toCollection(ArrayList<Tile>::new));

                    // if empty column
                    if (focus.size() == 0) continue;

                    // if not empty, move first tile to boundary
                    Logic_move.move_to_bound(kc, focus.get(0));

                    // then take care of the rest in the column by row
                    for (int j = 1; j < focus.size(); j++) {
                        // move them by pairs
                        Logic_move.move_to_nearest(kc, focus.get(j), focus.get(j-1));
                    }
                }

                break;
            
            case RIGHT:
                for (int j = 0; j < 5; j++) { // Browse rows
                    int finalJ = j;
                    ArrayList<Tile> focus = XTile.stream().filter(tile -> tile.getJ_() == finalJ)
                            .sorted(((o1, o2) -> o2.getI_() - o1.getI_()))
                            .collect(Collectors.toCollection(ArrayList<Tile>::new));

                    // if empty column
                    if (focus.size() == 0) continue;

                    // if not empty, move first tile to boundary
                    Logic_move.move_to_bound(kc, focus.get(0));

                    // then take care of the rest in the column by row
                    for (int i = 1; i < focus.size(); i++) {
                        // move them by pairs
                        Logic_move.move_to_nearest(kc, focus.get(i), focus.get(i-1));
                    }
                }

                break;
            
            case LEFT:
                for (int j = 0; j < 5; j++) { // Browse columns
                    int finalJ = j;
                    ArrayList<Tile> focus = XTile.stream().filter(tile -> tile.getJ_() == finalJ)
                            .sorted(((o1, o2) -> o1.getI_() - o2.getI_()))
                            .collect(Collectors.toCollection(ArrayList<Tile>::new));

                    // if empty column
                    if (focus.size() == 0) continue;

                    // if not empty, move first tile to boundary
                    Logic_move.move_to_bound(kc, focus.get(0));

                    // then take care of the rest in the column by row
                    for (int i = 1; i < focus.size(); i++) {
                        // move them by pairs
                        Logic_move.move_to_nearest(kc, focus.get(i), focus.get(i-1));
                    }
                }

                break;
            //endregion

            //region Supplemental controls for Delete & Spawning Tiles manually
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

            case E:
                spawning();
            //endregion
        }
        spawning();
        _main.gameover_annouce(Controller.isOver());
    }

    static void clear_all () {
        group.getChildren().removeAll(XTile);
        XTile.clear();
        iSet.clear();

        while (iSet.size() < 3) {
            int i = new Random().nextInt(25);
            if (iSet.add(i)) {
                Tile tile = new Tile(i ,new Random().nextDouble() > 0.9 ? 4 : 2);
                group.getChildren().add(tile);
                tile.setLayoutCordinate();
                XTile.add(tile);
            }
        }
    }

    private static void spawning () {
        int count = 0;

        if (XTile.size() == 25) {
            animationPlaying = false;
        }

        iSet.clear();
        iSet.addAll(XTile.stream().map(Tile::getInc_index).collect(Collectors.toList()));
        int spawnz = Math.min(25-XTile.size(), 2);

        while (count < spawnz) {
            int i = new Random().nextInt(25);
            if (!iSet.contains(i)) {
                iSet.add(i);
                count++;
                Tile tile = new Tile(i, Math.random() > 0.9 ? 4 : 2);
                tile.setOpacity(0);
                group.getChildren().add(tile);
                tile.setLayoutCordinate();
                XTile.add(tile);

                //region Animation in & out for new tile
                ScaleTransition st = new ScaleTransition(Duration.millis(100), tile);
                st.setFromY(0);
                st.setFromX(0);
                st.setToX(1);
                st.setToY(1);
                st.setDelay(Duration.millis(250));

                FadeTransition ft = new FadeTransition(Duration.millis(100),tile);
                ft.setFromValue(0);
                ft.setToValue(1);
                ft.setDelay(Duration.millis(250));

                st.play();
                ft.play();

                ft.setOnFinished(event -> animationPlaying = false);
                //endregion
            }
        }
    }

    private static boolean isOver () {
        // Challenge: checking game over condition
        if (XTile.size() < 25) {
            return false;
        } else {
            boolean check_v = false;
            boolean check_h = false;
            ArrayList<Tile> xtc = XTile.stream()
                    .sorted((o1, o2) -> o1.getInc_index() - o2.getInc_index())
                    .collect(Collectors.toCollection(ArrayList::new));

            int i = 0;
            while (i < 5 & !check_h & !check_v) {
                for (int j = 0; j < 4; j++) {
                    check_v = xtc.get(j+i*5).getValue() == xtc.get(i*5+j+1).getValue();
                    check_h = xtc.get(i+j*5).getValue() == xtc.get(i+(j+1)*5).getValue();
                    if (check_h|check_v) break;
                }
                i++;
            }

            return !check_h && !check_v;
        }
    }
}


class Logic_move {

    private static void reset_cord(Tile tile) {
        tile.setLayoutX(tile.getLayoutX() + tile.getTranslateX());
        tile.setLayoutY(tile.getLayoutY() + tile.getTranslateY());
        tile.setTranslateY(0);
        tile.setTranslateX(0);
    }

    static void move_to_bound (KeyCode kc, Tile tile) {
        Logic_move.reset_cord(tile);
        TranslateTransition tt = new TranslateTransition(Duration.millis(200), tile);
        tt.setNode(tile);
        double delta;

        if (tile.getTranslateY() != 0 | tile.getTranslateY() != 0) return;
        switch (kc) {
            case UP:
                if (tile.getJ_() != 0) {
                    delta = tile.getJ_()*-53;
                    tt.setToY(delta);
                    tile.setJ_(0);
                    tt.play();
                }
                break;

            case DOWN:
                if (tile.getJ_() != 4) {
                    delta = (4-tile.getJ_())*53;
                    tt.setToY(delta);
                    tile.setJ_(4);
                    tt.play();
                }
                break;

            case RIGHT:
                if (tile.getI_() != 4) {
                    delta = (4-tile.getI_())*53;
                    tt.setToX(delta);
                    tile.setI_(4);
                    tt.play();
                }
                break;

            case LEFT:
                if (tile.getI_() != 0) {
                    delta = tile.getI_()*-53;
                    tt.setToX(delta);
                    tile.setI_(0);
                    tt.play();
                }
                break;
        }

    }

    static void move_to_nearest (@NotNull KeyCode kc,@NotNull Tile t_m, @NotNull Tile t_dc) {
        reset_cord(t_m);
        reset_cord(t_dc);

        TranslateTransition tt = new TranslateTransition(Duration.millis(200), t_m);
        double delta;
        boolean check_merge_condition;

        switch (kc) {
            case UP:
                // check if mergeable
                check_merge_condition = (t_m.getValue() == t_dc.getValue() && t_dc.isMergable());
                delta = check_merge_condition? (t_dc.getJ_()-t_m.getJ_())*53 : (t_dc.getJ_()-t_m.getJ_() + 1)*53;
                tt.setToY(delta);
                if (check_merge_condition) {
                    t_m.setValue(t_m.getValue()*2);
                    t_m.setMergable(false);
                    Controller.XTile.remove(t_dc);
                    tt.setOnFinished(event -> Controller.group.getChildren().remove(t_dc));
                }
                t_m.setJ_(check_merge_condition? t_dc.getJ_() : t_dc.getJ_() + 1);
                tt.play();
                break;

            case DOWN:
                check_merge_condition = (t_m.getValue() == t_dc.getValue() && t_dc.isMergable());
                delta = check_merge_condition? (t_dc.getJ_()-t_m.getJ_())*53 : (t_dc.getJ_()-t_m.getJ_() - 1)*53;
                tt.setToY(delta);
                if (check_merge_condition) {
                    t_m.setValue(t_m.getValue()*2);
                    t_m.setMergable(false);
                    Controller.XTile.remove(t_dc);
                    tt.setOnFinished(event -> Controller.group.getChildren().remove(t_dc));
                }
                t_m.setJ_(check_merge_condition? t_dc.getJ_() : t_dc.getJ_() - 1);
                tt.play();
                break;

            case RIGHT:
                check_merge_condition = (t_m.getValue() == t_dc.getValue() && t_dc.isMergable());
                delta = check_merge_condition? (t_dc.getI_()-t_m.getI_())*53 : (t_dc.getI_()-t_m.getI_() - 1)*53;
                tt.setToX(delta);
                if (check_merge_condition) {
                    t_m.setValue(t_m.getValue()*2);
                    t_m.setMergable(false);
                    Controller.XTile.remove(t_dc);
                    tt.setOnFinished(event -> Controller.group.getChildren().remove(t_dc));
                }
                t_m.setI_(check_merge_condition? t_dc.getI_() : t_dc.getI_() - 1);
                tt.play();

                break;

            case LEFT:
                check_merge_condition = (t_m.getValue() == t_dc.getValue() && t_dc.isMergable());
                delta = check_merge_condition? (t_dc.getI_()-t_m.getI_())*53 : (t_dc.getI_()-t_m.getI_() + 1)*53;
                tt.setToX(delta);
                if (check_merge_condition) {
                    t_m.setValue(t_m.getValue()*2);
                    t_m.setMergable(false);
                    Controller.XTile.remove(t_dc);
                    tt.setOnFinished(event -> Controller.group.getChildren().remove(t_dc));
                }
                t_m.setI_(check_merge_condition? t_dc.getI_() : t_dc.getI_() + 1);
                tt.play();
                break;
        }
    }



}