package com.v41.tp1;

import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class GameActivity extends AppCompatActivity {

    Button[] colorButtons;
    ImageView view;

    ColorGame game;

    MediaPlayer correctAnswerSoundPlayer;
    MediaPlayer incorrectAnswerSoundPlayer;



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

        correctAnswerSoundPlayer = correctAnswerSoundPlayer.create(this.getApplicationContext(),R.raw.good_ping);
        incorrectAnswerSoundPlayer = incorrectAnswerSoundPlayer.create(this.getApplicationContext(),R.raw.wrong_ping);
        for (final Button button : colorButtons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //view.getId()
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

        ColorItem[] colorItems = new ColorItem[8];
        colorItems[0] = new ColorItem(R.color.Red,R.string.Red);
        colorItems[1] = new ColorItem(R.color.Green,R.string.Green);
        colorItems[2] = new ColorItem(R.color.Blue,R.string.Blue);
        colorItems[3] = new ColorItem(R.color.Orange,R.string.Orange);
        colorItems[4] = new ColorItem(R.color.Black,R.string.Black);
        colorItems[5] = new ColorItem(R.color.Brown,R.string.Brown);
        colorItems[6] = new ColorItem(R.color.Purple,R.string.Purple);
        colorItems[7] = new ColorItem(R.color.Pink,R.string.Pink);

        game = new ColorGame(colorItems);
    }

    private void UpdateButtonTextAndColor(){
        for (int i = 0; i< colorButtons.length; i++){
            colorButtons[i].setTextColor(ContextCompat.getColor(this,game.getColorItems()[i].getColor()));
            colorButtons[i].setText(game.getColorItems()[i].getName());
        }
        view.setBackgroundTintList(getColorStateList(game.getColorToFind()));
    }
    @Override
    protected void onStart() {
        super.onStart();

        game.startNewGame();
        UpdateButtonTextAndColor();
        }
    }

