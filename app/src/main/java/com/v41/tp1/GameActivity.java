package com.v41.tp1;

import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import Game.ColorGame;
import Game.ColorItem;

public class GameActivity extends AppCompatActivity {

    //BEN_CORRECTION : Visibilités manquantes.
    Button[] colorButtons;
    ImageView view; //BEN_CORRECTION : View ? View de quoi ? AH ? C'est ta puce de couleur! Précise le dans le nom. Genre "colorCircleView".

    ColorGame game;

    MediaPlayer correctAnswerSoundPlayer;
    MediaPlayer incorrectAnswerSoundPlayer;

    //BEN_REVIEW : Préfère placer les constantes en tout premier, en haut.
    private final String CORRECT_ANSWER = "CORRECT_ANSWER";
    private final String CORRECT_BUTTON_INDEX = "CORRECT_BUTTON_INDEX";
    private final String COLOR_DISPLAY_NAMES = "COLOR_DISPLAY_NAMES";
    private final String DISPLAYED_COLORS = "DISPLAYED_COLORS";
    private final String COLOR_ITEM_NAME = "COLOR_ITEM_NAME";
    private final String COLOR_ITEM_COLOR = "COLOR_ITEM_COLOR";

    private final int TOTAL_NUMBER_OF_COLORS = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        view = (ImageView) findViewById(R.id.view);
        colorButtons = new Button[4];
        colorButtons[0] = (Button)findViewById(R.id.button);
        colorButtons[1] = (Button)findViewById(R.id.button2);
        colorButtons[2] = (Button)findViewById(R.id.button3);
        colorButtons[3] = (Button)findViewById(R.id.button4);

        //BEN_CORRECTION : Méthode statique appelée dans un contexte non statique. De plus, "correctAnswerSoundPlayer" est null à ce momment là,
        //                 mais à cause que c'est une méthode statique, tu n'as pas de "NullPointerException".
        correctAnswerSoundPlayer = correctAnswerSoundPlayer.create(this.getApplicationContext(),R.raw.good_ping);
        incorrectAnswerSoundPlayer = incorrectAnswerSoundPlayer.create(this.getApplicationContext(),R.raw.wrong_ping);
        for (final Button button : colorButtons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i< colorButtons.length; i++){
                        if (colorButtons[i].getId() == v.getId()){
                            if(game.SelectionIsRight(i)){
                                correctAnswerSoundPlayer.start();
                            } else {
                                incorrectAnswerSoundPlayer.start();
                            }
                        }
                    }
                    game.startNewGame();
                    UpdateButtonTextAndColor();
                }
            });
        }

        ColorItem[] colorItems = new ColorItem[TOTAL_NUMBER_OF_COLORS];
        colorItems[0] = new ColorItem(R.color.Red,R.string.Red);
        colorItems[1] = new ColorItem(R.color.Green,R.string.Green);
        colorItems[2] = new ColorItem(R.color.Blue,R.string.Blue);
        colorItems[3] = new ColorItem(R.color.Orange,R.string.Orange);
        colorItems[4] = new ColorItem(R.color.Black,R.string.Black);
        colorItems[5] = new ColorItem(R.color.Brown,R.string.Brown);
        colorItems[6] = new ColorItem(R.color.Purple,R.string.Purple);
        colorItems[7] = new ColorItem(R.color.Pink,R.string.Pink);

        game = new ColorGame(colorItems);
        game.startNewGame();
        UpdateButtonTextAndColor();
    }

    //BEN_CORRECTION : En Java, les méthodes commence normalement par une minuscule. À ne pas mélanger avec le C#.
    private void UpdateButtonTextAndColor(){
        for (int i = 0; i< colorButtons.length; i++){
            //BEN_CORRECTION : C'est pour cela que les couleurs n'étaient pas correctes. J'ai mis en comemntaires ce que tu avais écrit et juste en dessous
            //                 ce que tu aurait du écrire.

            //colorButtons[i].setTextColor(ContextCompat.getColor(this,game.getColorItems()[i].getColor()));
            colorButtons[i].setTextColor(ContextCompat.getColor(this,game.getDisplayedColors()[i]));

            colorButtons[i].setText(game.getColorItems()[i].getName());
        }
        view.setBackgroundTintList(getColorStateList(game.getColorToFind()));
    }

    //BEN_CORRECTION : Identation + Méthode vide.
    @Override
    protected void onStart() {
        super.onStart();
        }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CORRECT_ANSWER,game.getColorToFind());
        outState.putInt(CORRECT_BUTTON_INDEX,game.getCorrectButtonIndex());
        outState.putIntArray(COLOR_DISPLAY_NAMES,game.getColorDisplayNames());
        outState.putIntArray(DISPLAYED_COLORS,game.getDisplayedColors());
        for (int i = 0; i< TOTAL_NUMBER_OF_COLORS; i++){
            outState.putInt(COLOR_ITEM_NAME + i,game.getColorItems()[i].getName());
            outState.putInt(COLOR_ITEM_COLOR + i,game.getColorItems()[i].getColor());
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        game = new ColorGame(TOTAL_NUMBER_OF_COLORS);
        game.setColorToFind(savedInstanceState.getInt(CORRECT_ANSWER));
        game.setCorrectButtonIndex(savedInstanceState.getInt(CORRECT_BUTTON_INDEX));
        game.setColorDisplayNames(savedInstanceState.getIntArray(COLOR_DISPLAY_NAMES));
        game.setDisplayedColors(savedInstanceState.getIntArray(DISPLAYED_COLORS));
        for (int i = 0; i< game.getColorItems().length; i++){
            game.getColorItems()[i].setName(savedInstanceState.getInt(COLOR_ITEM_NAME+i));
            game.getColorItems()[i].setColor(savedInstanceState.getInt(COLOR_ITEM_COLOR+i));
        }
        UpdateButtonTextAndColor();
    }
}

