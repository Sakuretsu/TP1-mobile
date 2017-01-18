package com.v41.tp1;

import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class GameActivity extends AppCompatActivity {

    Button[] colorButtons;
    ImageView view;

    ColorGame game;

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

    @Override
    protected void onStart() {
        super.onStart();

        game.startNewGame();
        for (int i = 0; i< colorButtons.length; i++){
            colorButtons[i].setLinkTextColor(game.getColorItems()[i].getColor());
            colorButtons[i].setText(game.getColorItems()[i].getName());
        }
        view.setColorFilter(game.getColorToFind());
    }
}
