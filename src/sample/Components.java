package sample;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

/**
 * Created by Elderaine on 17/06/2016.
 */
public class Components {

}

class Tile extends Label {
    private int value;

    public int getValue() {
        return value;
    }

    public Tile(int a, int b, Paint color) {
        super();
        setPrefSize(a,b);
        setBackground(new Background(new BackgroundFill(color, new CornerRadii(2), null)));
        setFont(new Font(25));
        setTextFill(Color.WHITE);
        setAlignment(Pos.CENTER);
    }

    public void setValue(int i) {
        value = i;
        setText(String.valueOf(i));
    }

}