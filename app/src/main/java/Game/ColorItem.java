package Game;

import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;

/**
 * Created by Tommy Bouffard on 2017-01-16.
 */

public class ColorItem {
    @StringRes
    private int name;
    @ColorRes
    private int color;

    public ColorItem(int color, int name) {
        this.color = color;
        this.name = name;
    }

    public ColorItem(){

    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}
