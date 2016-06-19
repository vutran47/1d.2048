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
    private int i_;
    private int j_;

    public void setValue(int i) {
        value = i;
        setText(String.valueOf(i));
    }

    public int getValue() {
        return value;
    }

    //region SETTER & GETTER for i_ & j_: these are compelling!
    public int getI_() {
        return i_;
    }

    public void setI_(int i_) {
        this.i_ = i_;
    }

    public int getJ_() {
        return j_;
    }

    public void setJ_(int j_) {
        this.j_ = j_;
    }
    //endregion

    public int getInc_index() {
        return getI_()*5+getJ_();
    }

    public void setInc_index(int inc_index) {
        setI_(Math.floorDiv(inc_index, 5));
        setJ_(inc_index - 5*getI_());
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

    public Tile(int incremental_index) {
        super();
        setPrefSize(50,50);
        setBackground(new Background(new BackgroundFill(Color.DARKORCHID, new CornerRadii(2), null)));
        setFont(new Font(25));
        setTextFill(Color.WHITE);
        setAlignment(Pos.CENTER);
        setInc_index(incremental_index);
    }


}