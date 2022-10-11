package com.example.w5_p4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    GameFrame gameFrame;
    ControlFrame controlFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameFrame = new GameFrame();
        controlFrame = new ControlFrame();

        getSupportFragmentManager().beginTransaction().
                replace(R.id.mainGame, gameFrame, "Game").
                setReorderingAllowed(true).
                commit();

        getSupportFragmentManager().beginTransaction().
                replace(R.id.lowerPart, controlFrame, "control").
                setReorderingAllowed(true).
                commit();
    }
}