package com.example.hunting.testavos;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;

import java.util.Date;


public class successActivity extends Activity implements View.OnClickListener {
    private TextView tvscore ;
    public int score = 0;
    Button uv,rs;

    public successActivity(){
        successActivity = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_main);
        tvscore = (TextView) findViewById(R.id.tv_score);
        uv = (Button) findViewById(R.id.userView);
        uv.setOnClickListener(this);
        rs = (Button)findViewById(R.id.restart);
        rs.setOnClickListener(this);
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
                createScore();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.userView:
                Intent intent1 = new Intent(this,UserActivity.class);
                startActivity(intent1);
                break;
            case R.id.restart:
                createScore();
                finish();
                Intent intent2 = new Intent(this, successActivity.class);
                startActivity(intent2);
                break;
        }

    }

    //新建一条记录，数据库表名为score

    public void createScore() {
        AVObject sc = new AVObject("score");
        sc.put("score", score);
        sc.put("createAt", new Date().getTime());
        sc.put("owner", AVUser.getCurrentUser());
        sc.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    successActivity.this.finish();

                } else {
                    Toast.makeText(successActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
}
