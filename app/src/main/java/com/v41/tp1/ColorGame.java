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

    private int correctButtonIndex;
    private int colorToFind;
    private int[] colorDisplayNames;
    private int[] displayedColors;

    public int[] getColorDisplayNames() {
        return colorDisplayNames;
    }

    public int getColorToFind() {
        return colorToFind;
    }

    public ColorItem[] getColorItems() {
        return colorItems;
    }

    private ColorItem[] colorItems;

    public ColorGame(ColorItem[] colorItems){
        this.colorItems = colorItems;
        colorDisplayNames = new int[4];
        displayedColors = new int[4];
    }

    public void startNewGame(){
        Collections.shuffle(Arrays.asList(colorItems));
        Random rn = new Random();
        for(int i = 0; i<colorDisplayNames.length; i++){
            colorDisplayNames[i] = colorItems[i].getName();
            displayedColors[i] = -1;
            while (displayedColors[i] == -1 || displayedColors[i] == colorItems[i].getColor()){
                displayedColors[i] = colorItems[rn.nextInt(colorItems.length)].getColor();
            }
        }
        correctButtonIndex = rn.nextInt(colorDisplayNames.length);
        colorToFind = colorItems[correctButtonIndex].getColor();
    }

    public boolean SelectionIsRight(int pushedButtonIndex){
        return pushedButtonIndex == correctButtonIndex;
    }


}
