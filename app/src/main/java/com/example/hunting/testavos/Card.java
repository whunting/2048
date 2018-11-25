package com.example.hunting.testavos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Card extends FrameLayout {
    private TextView t;
    private int num = 0;
    private int backgroundColor = 0xE8E0D4;

    public Card(Context context) {
        super(context);
        t = new TextView(getContext()); //显示
        t.setTextSize(32);
        t.setGravity(Gravity.CENTER);
        t.setBackgroundColor(0xE8E0D4);
        LayoutParams lp = new LayoutParams(-1,-1); //创建布局，填满父容器
        lp.setMargins(15,15,0,0);
        addView(t,lp);
        setNum(0);
    }
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
        t.setBackgroundColor(getBackground(num));
        if (this.num<= 0)
        {
            t.setText("");

        }else {
            t.setText(num + "");
        }
    }
    private int getBackground(int num) {
        int bottomColor = backgroundColor;
        switch (num) {
            case 2:
                bottomColor = 0xE8E0D4;
                break;
            case 4:
                bottomColor = 0xEAD2B0;
                break;
            case 8:
                bottomColor = 0xEEB96E;
                break;
            case 16:
                bottomColor = 0xFFA65F;
                break;

            case 32:
                bottomColor = 0xFF8740;
                break;
            case 64:
                bottomColor = 0xF97811;
                break;
            case 128:
                bottomColor = 0xFFC62F;
                break;
            case 256:
                bottomColor = 0xFFDB4E;
                break;
            case 512:
                bottomColor = 0xDD6B48;
                break;
            case 1024:
                bottomColor = 0xCC564A;
                break;
            case 2048:
                bottomColor = 0xE8E0D4;
                break;
            default:
                bottomColor = 0xB90B0B;
                break;

        }
        return bottomColor;
    }
    /**
     *判断是否为相同数字的卡片
     * @return
     */
    public boolean equal(Card a) {
        return getNum() == a.getNum();
    }

}
