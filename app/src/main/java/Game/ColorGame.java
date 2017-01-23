package Game;

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

    private final int BUTTON_CHOICE_NUMBERS = 4;

    public void setColorDisplayNames(int[] colorDisplayNames) {
        this.colorDisplayNames = colorDisplayNames;
    }

    public void setColorToFind(int colorToFind) {
        this.colorToFind = colorToFind;
    }

    public int getCorrectButtonIndex() {
        return correctButtonIndex;
    }

    public void setCorrectButtonIndex(int correctButtonIndex) {
        this.correctButtonIndex = correctButtonIndex;
    }

    public int[] getDisplayedColors() {
        return displayedColors;
    }

    public void setDisplayedColors(int[] displayedColors) {
        this.displayedColors = displayedColors;
    }

    //BEN_CORRECTION : Tu ne t'en sert pas, sauf dans "onSaveInstanceState". C'est pour cela que le texte et sa couleur correspondent. Tu as tout un bloc
    //                 de logique applicative que tu ne te sert pas dams "GameActivity".
    //
    //                Voir "GameActivity", je l'ai corrigé. Tu en était à une ligne près.
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
        colorDisplayNames = new int[BUTTON_CHOICE_NUMBERS];
        displayedColors = new int[BUTTON_CHOICE_NUMBERS];
    }

    public ColorGame(int numberOfColors){
        colorDisplayNames = new int[BUTTON_CHOICE_NUMBERS];
        displayedColors = new int[BUTTON_CHOICE_NUMBERS];
        colorItems = new ColorItem[numberOfColors];
        for(int i = 0; i<numberOfColors; i++){
            colorItems[i] = new ColorItem();
        }
    }

    public void startNewGame(){
        Collections.shuffle(Arrays.asList(colorItems));
        //BEN_CORRECTION : Nommage...nommage...nommage... « rn » n'est pas un nom approprié.
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
