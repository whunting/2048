package com.example.hunting.testavos;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

public class Game extends GridLayout{
    private Card [][] cards = new Card[4][4]; // 游戏界面中的16个卡片类
    private static Game g = null;
    public static Game getG() {
        return g;
    }

    private List<Point> points = new ArrayList<Point>(); // 存放空的小方块

    public Game(Context context) {
        super(context);
        g = this;
        initGame(); // 初始化界面
    }

    /**
     *  初始化界面
     */

    private void initGame() {
        setColumnCount(4);
        setBackgroundColor(0x8E8372);
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

    private void slideLeft() {
        boolean test = false;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                for (int x1 = x+1; x1 < 4; x1++) {
                    if (cards[x1][y].getNum() > 0) {
                        if (cards[x][y].getNum() <= 0) {
                            cards[x][y].setNum(cards[x1][y].getNum());
                            cards[x][y].setBackgroundColor(cards[x][y].getNum());
                            cards[x1][y].setNum(0);
                            cards[x1][y].setBackgroundColor(cards[x1][y].getNum());
                            x--;
                            test = true;
                        }
                        else if (cards[x][y].equal(cards[x1][y])) {
                            cards[x][y].setNum(cards[x][y].getNum()*2);
                            cards[x][y].setBackgroundColor(cards[x][y].getNum());
                            cards[x+1][y].setNum(0);
                            cards[x1][y].setBackgroundColor(cards[x1][y].getNum());
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


    private void slideRight() {
        boolean test = false;
        for (int y = 0; y < 4; y++) {
            for (int x = 3; x >= 0; x--) {
                for (int x1 = x-1; x1 >= 0; x--) {
                    if (cards[x1][y].getNum() > 0) {
                        if (cards[x][y].getNum() <= 0) {
                            cards[x][y].setNum(cards[x1][y].getNum());
                            cards[x][y].setBackgroundColor(cards[x][y].getNum());
                            cards[x1][y].setNum(0);
                            cards[x1][y].setBackgroundColor(cards[x1][y].getNum());
                            x--;
                            test = true;
                        }
                        else if (cards[x][y].equal(cards[x1][y])) {
                            cards[x][y].setNum(cards[x][y].getNum()*2);
                            cards[x][y].setBackgroundColor(cards[x][y].getNum());
                            cards[x+1][y].setNum(0);
                            cards[x1][y].setBackgroundColor(cards[x1][y].getNum());
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
                            cards[x][y].setBackgroundColor(cards[x][y].getNum());
                            cards[x][y1].setNum(0);
                            cards[x][y1].setBackgroundColor(cards[x][y1].getNum());
                            x--;
                            test = true;
                        }
                        else if (cards[x][y].equal(cards[x][y1])) {
                            cards[x][y].setNum(cards[x][y].getNum()*2);
                            cards[x][y].setBackgroundColor(cards[x][y].getNum());
                            cards[x+1][y].setNum(0);
                            cards[x][y1].setBackgroundColor(cards[x][y1].getNum());
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
                            cards[x][y].setBackgroundColor(cards[x][y].getNum());
                            cards[x][y1].setNum(0);
                            cards[x][y1].setBackgroundColor(cards[x][y1].getNum());
                            x--;
                            test = true;
                        }
                        else if (cards[x][y].equal(cards[x][y1])) {
                            cards[x][y].setNum(cards[x][y].getNum()*2);
                            cards[x][y].setBackgroundColor(cards[x][y].getNum());
                            cards[x+1][y].setNum(0);
                            cards[x][y1].setBackgroundColor(cards[x][y1].getNum());
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



    private  void addRandomNum() {
        points.clear();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (cards[x][y].getNum() <= 0) {
                    points.add(new Point(x,y));
                }
            }
        }
        Point p = points.remove((int)Math.random()*points.size());
        cards[p.x][p.y].setNum(Math.random() > 0.1 ? 2:4);
        cards[p.x][p.y].setBackgroundColor(cards[p.x][p.y].getNum());
    }

    private void checkComplete(){

    }
}
