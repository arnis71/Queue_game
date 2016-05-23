package ru.arnis.queuegame;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.Set;

/**
 * Created by arnis on 20/05/16.
 */
public class StartScreen extends AppCompatActivity {
    private Button playButton;
    private Button settingsButton;
    private Intent startGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.start_screen);

        playButton = (Button)findViewById(R.id.button_play);
        settingsButton = (Button)findViewById(R.id.button_settings);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Settings.column!=null) {
                    switch (Settings.column) {
                        case "2 columns":
                            startGame = new Intent(v.getContext(), Game2Cols.class);
                            startActivity(startGame);
                            break;
                        case "3 columns":
                            startGame = new Intent(v.getContext(), Game3Cols.class);
                            startActivity(startGame);
                            break;
                        case "4 columns":
                            startGame = new Intent(v.getContext(), Game4Cols.class);
                            startActivity(startGame);
                            break;
                    }
                }else {startGame = new Intent(v.getContext(), Game3Cols.class);
                startActivity(startGame);}
            }
        });
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSettings = new Intent(v.getContext(),Settings.class);
                startActivity(goToSettings);
            }
        });
    }
    protected void hideStatusBar(){
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }


}
