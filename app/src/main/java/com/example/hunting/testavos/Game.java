package com.example.hunting.testavos;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;


import java.util.ArrayList;
import java.util.List;

public class Game extends GridLayout{
    public Card [][] cards = new Card[4][4];

    private List<Point> points = new ArrayList<Point>(); // 存放空的小方块
    public Game(Context context) {
        super(context);
        initGame(); // 初始化界面
    }

    public Game(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGame();
    }

    public Game(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initGame();
    }

    /**
     *  初始化界面
     */

    private void initGame() {
        setColumnCount(4);
        setBackgroundColor(0x8E8372);
        addCards(getCardWitch(),getCardWitch());
        startGame();
        setOnTouchListener(new OnTouchListener() {
            private float originalX,originalY,offX,offY;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // 当屏幕检测到第一个触点按下之后就会触发到这个事件。
                        originalX = event.getX();
                        originalY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP: // 当屏幕上有多个点被按住，松开其中一个点时触发（即非最后一个点被放开时）触发。
                        offX = event.getX()-originalX;
                        offY = event.getY()-originalY;

                        if (Math.abs(offX) > Math.abs(offY)) {
                            if (offX < -5) slideLeft();
                            else if (offX > 5)  slideRight();
                        }
                        else {
                            if (offY < -5) slideUp();
                            else if (offY > 5) slideDown();
                        }
                        break;

                    default:
                        break;

                }
                return true;
            }
        });
    }


    /**
     * 布局里面加入卡片
     */
    private void addCards(int cardWidth,int cardHeight){
        Card c;
        for(int y = 0;y < 4;y++){
            for(int x = 0;x < 4;x++){
                c = new Card(getContext());
                c.setNum(0);
                addView(c,cardWidth,cardHeight);
                cards[x][y] = c;
            }
        }

    }

    /**
     * 获取屏幕宽度
     * @return
     */

    private int getCardWitch(){
        DisplayMetrics displayMetrics;
        displayMetrics = getResources().getDisplayMetrics();

        int carWitch;
        carWitch = displayMetrics.widthPixels;

        return (carWitch-10)/4;
    }

    public void startGame(){
        successActivity.getSuccessActivity().clearScore();
        for (int y = 0;y<4;y++){
            for (int x = 0;x < 4;x++) {
                try {
                    cards[x][y].setNum(0);
                }catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
        addRandomNum();
        addRandomNum();

    }

    private  void addRandomNum() {
        points.clear();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                try {
                    if (cards[x][y].getNum() <= 0) {
                        points.add(new Point(x,y));
                    }
                }catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
        if (points.size()>0) {
            Point p = points.remove((int) (Math.random() * points.size()));
            cards[p.x][p.y].setNum(Math.random() > 0.1 ? 2:4);
            cards[p.x][p.y].setBackgroundColor(cards[p.x][p.y].getNum());
        }
    }

    /**
     * 上下左右滑动
     */

    private void slideLeft() {
        boolean test = false;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                for (int x1 = x+1; x1 < 4; x1++) {
                    try {
                        if (cards[x1][y].getNum() > 0) {
                            if (cards[x][y].getNum() <= 0) {
                                cards[x][y].setNum(cards[x1][y].getNum());
                                cards[x1][y].setNum(0);
                                x--;
                                test = true;
                            } else if (cards[x][y].equal(cards[x1][y])) {
                                cards[x][y].setNum(cards[x][y].getNum() * 2);
                                cards[x1][y].setNum(0);
                                successActivity.getSuccessActivity().addScore(cards[x][y].getNum());
                                test = true;

                            }
                        }
                    }catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                        break;
                    }
                }

            }
        if (test) {
            addRandomNum();
            checkComplete();
        }
    }


    private void slideRight() {
        boolean test = false;
        for (int y = 0; y < 4; y++) {
            for (int x = 3; x >= 0; x--) {
                for (int x1 = x-1; x1 >= 0; x1--) {
                    if (cards[x1][y].getNum() > 0) {
                        if (cards[x][y].getNum() <= 0) {
                            cards[x][y].setNum(cards[x1][y].getNum());
                            cards[x1][y].setNum(0);
                            x++;
                            test = true;
                        }
                        else if (cards[x][y].equal(cards[x1][y])) {
                            cards[x][y].setNum(cards[x][y].getNum()*2);
                            cards[x1][y].setNum(0);
                            successActivity.getSuccessActivity().addScore(cards[x][y].getNum());
                            test = true;

                        }
                        break;
                    }
                }

            }
        }
        if (test) {
            addRandomNum();
            checkComplete();
        }
    }

    private void slideUp() {
        boolean test = false;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                for (int y1 = y+1; y1 < 4; y1++) {
                    if (cards[x][y1].getNum() > 0) {
                        if (cards[x][y].getNum() <= 0) {
                            cards[x][y].setNum(cards[x][y1].getNum());
                            cards[x][y1].setNum(0);
                            y--;
                            test = true;
                        }
                        else if (cards[x][y].equal(cards[x][y1])) {
                            cards[x][y].setNum(cards[x][y].getNum()*2);
                            cards[x][y1].setNum(0);
                            successActivity.getSuccessActivity().addScore(cards[x][y].getNum());
                            test = true;

                        }
                        break;
                    }
                }

            }
        }
        if (test) {
            addRandomNum();
            checkComplete();
        }
    }

    private void slideDown() {
        boolean test = false;
        for (int x = 0; x < 4; x++) {
            for (int y = 3; y >= 0; y--) {
                for (int y1 = y-1; y1 >= 0; y1--) {
                    if (cards[x][y1].getNum() > 0) {
                        if (cards[x][y].getNum() <= 0) {
                            cards[x][y].setNum(cards[x][y1].getNum());
                            cards[x][y1].setNum(0);
                            y++;
                            test = true;
                        }
                        else if (cards[x][y].equal(cards[x][y1])) {
                            cards[x][y].setNum(cards[x][y].getNum()*2);
                            cards[x][y1].setNum(0);
                            successActivity.getSuccessActivity().addScore(cards[x][y].getNum());
                            test = true;

                        }
                        break;
                    }
                }

            }
        }
        if (test) {
            addRandomNum();
            checkComplete();
        }
    }


    /**
     * 判断游戏是否结束
     */

    private void checkComplete(){
        boolean check = true;
        for (int x = 0; x < 4 ; x++) {
            for (int y = 0; y < 4; y++) {
                if(cards[x][y].getNum() == 0) {
                    check = false;
                    break;
                }
                else if (x < 3) {
                    if (cards[x][y].equal(cards[x+1][y])) {
                        check = false;
                        break;
                    }
                }
                else if (y < 3) {
                    if (cards[x][y].equal(cards[x][y+1])) {
                        check = false;
                        break;
                    }
                }
                else if (x > 0) {
                    if (cards[x][y].equal(cards[x-1][y])) {
                        check = false;
                        break;
                    }
                }
                else if (y > 0) {
                    if (cards[x][y].equal(cards[x][y-1])) {
                        check = false;
                        break;
                    }
                }

            }

        }
        if (check) {
            new AlertDialog.Builder(getContext()).setTitle("提示").setMessage("游戏结束了").
                    setPositiveButton("重来哈", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startGame();
                }
            }).show();
        }

    }

}
