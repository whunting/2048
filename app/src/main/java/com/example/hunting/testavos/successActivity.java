package com.example.hunting.testavos;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;


public class successActivity extends Activity{
    private TextView tvscore ;
    public int score = 0;

    public successActivity(){
        successActivity = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_main);
        tvscore = (TextView) findViewById(R.id.tv_score);
    }
    public void clearScore() {
        score = 0;
        showScore();
    }

    @SuppressLint("SetTextI18n")
    public void showScore() {
        try {
            if (score == 0) {
                tvscore.setText(String.valueOf(0));
            }
            else {
                tvscore.setText(score+"");
            }
        }catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
    public void addScore(int num) {
        score += num;
        showScore();
    }

    private static successActivity successActivity = null;

    public static successActivity getSuccessActivity() {
        return successActivity;
    }

    /**
     * 菜单、返回键响应
     */
    private long exitTime = 0;

    @SuppressLint("WrongConstant")
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, "再按一次退出哈",1000).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
