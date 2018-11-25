package com.example.hunting.testavos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class successActivity extends AppCompatActivity {
    private TextView gscore;
    private int s = 0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_main);
        gscore = findViewById(R.id.score);
    }
    public void clearScore() {
        s = 0;
        showScore();
    }
    public void showScore() {
        gscore.setText(s);
    }
    public void addScore(int num) {
        s += num;
        showScore();
    }
    private static successActivity successActivity = null;

    public static successActivity getSuccessActivity() {
        return successActivity;
    }
}
