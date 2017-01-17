package com.v41.tp1;

import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Tommy Bouffard on 2017-01-16.
 */

public class ColorGame {

    private int colorToFind;
    private int[] colorDisplayNames;

    private ColorItem[] colorItems;

    public ColorGame(ColorItem[] colorItems){
        this.colorItems = colorItems;
        colorDisplayNames = new int[4];
    }

    public void StartNewGame(){
        Collections.shuffle(Arrays.asList(colorItems));
        for(int i = 0; i<colorDisplayNames.length; i++){
             colorDisplayNames[i] = colorItems[i].name;
        }
        Random rn = new Random();
        colorToFind = colorDisplayNames[rn.nextInt(colorDisplayNames.length)];
    }

    public boolean SelectionIsRight(int pushedButtonColorCode){
        return colorToFind == pushedButtonColorCode;
    }
}
