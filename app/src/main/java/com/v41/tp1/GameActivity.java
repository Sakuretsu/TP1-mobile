package com.v41.tp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class GameActivity extends AppCompatActivity {

    Button[] colors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        colors = new Button[4];
        colors[0] = (Button)findViewById(R.id.button);
        colors[1] = (Button)findViewById(R.id.button2);
        colors[2] = (Button)findViewById(R.id.button3);
        colors[3] = (Button)findViewById(R.id.button4);
    }
}
