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
    int i_;
    int j_;

    public int getJ_() {
        return j_;
    }

    public void setJ_(int j_) {
        this.j_ = j_;
    }

    public int getI_() {
        return i_;
    }

    public void setI_(int i_) {
        this.i_ = i_;
    }

    public int getInc_index() {
        return i_*5+j_;
    }

    public void setInc_index(int inc_index) {
        setI_(Math.floorDiv(inc_index, 5));
        setJ_(inc_index - 5*i_);
    }

    public Tile(int a, int b, Paint color, int i_, int j_) {
        super();
        setPrefSize(a,b);
        setBackground(new Background(new BackgroundFill(color, new CornerRadii(2), null)));
        setFont(new Font(25));
        setTextFill(Color.WHITE);
        setAlignment(Pos.CENTER);
        setI_(i_);
        setJ_(j_);
    }

    public void setValue(int i) {
        value = i;
        setText(String.valueOf(i));
    }

    public int getValue() {
        return value;
    }
}