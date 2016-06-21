package sample;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.util.TreeMap;

public class Components {
    private static TreeMap<Integer, String> mydict;

    // Init the dictionary for color - value
    static {
        mydict = new TreeMap<>();
        mydict.put(2, "#F2F20A");
        mydict.put(4, "#FFBC21");
        mydict.put(8, "#FAD18E");
        mydict.put(16, "#FCD3C2");
        mydict.put(32, "#FA7A7A");
        mydict.put(64, "#FFB0C8");
        mydict.put(128, "#F79CF6");
        mydict.put(256, "#6879E3");
        mydict.put(512, "#66C961");
        mydict.put(1024, "#CC00BB");
        mydict.put(2048, "#000000");
    }

    public static String getColorForKey(int i) {
        if (mydict.containsKey(i)) {
            return mydict.get(i);
        } else {
            return "#1495B3";
        }
    }
}


class Tile extends Label {
    //region Necessary Instance properties and their Getters & Setters
    private int value;
    private int i_;
    private int j_;

    private boolean mergable;

    public boolean isMergable() {
        return mergable;
    }

    public void setMergable(boolean mergable) {
        this.mergable = mergable;
    }

    public void setValue(int i) {
        value = i;
        setText(String.valueOf(i));
        setBackground(new Background(new BackgroundFill(Paint.valueOf(Components.getColorForKey(value)), new CornerRadii(2), null)));
    }

    public int getValue() {
        return value;
    }

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

    // Special attributes for Tile: incremental index:
    public int getInc_index() {
        return getI_()*5+getJ_();
    }

    public void setInc_index(int inc_index) {
        setI_(Math.floorDiv(inc_index, 5));
        setJ_(inc_index - 5*getI_());
    }

    // Layout method for Tile depending on its Row and Column
    public void setLayoutCordinate() {
        setLayoutX(53*getI_()+4);
        setLayoutY(53*getJ_()+4);
    }

    // CONSTRUCTOR
    public Tile(int incremental_index, int value) {
        super();
        setPrefSize(50,50);
        setBackground(new Background(new BackgroundFill(Paint.valueOf(Components.getColorForKey(value)), new CornerRadii(2), null)));
        setFont(Font.font("Impact", 24));
        setTextFill(Paint.valueOf("#F0F7ED"));
        setAlignment(Pos.CENTER);
        setInc_index(incremental_index);
        setValue(value);
        setMergable(true);
    }
}