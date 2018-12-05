package com.example.hunting.testavos;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Card extends FrameLayout {
    private TextView t;
    private int num = 0;
    int backgroundColor = 0x8E8372;


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
        switch (num) {
            case 0:
                t.setBackgroundColor(Color.parseColor("#E8E0D4"));
                break;
            case 2:
                t.setBackgroundColor(Color.parseColor("#E8E0D4"));
                break;
            case 4:
                t.setBackgroundColor(Color.parseColor("#EAD2B0"));
                break;
            case 8:
                t.setBackgroundColor(Color.parseColor("#EEB96E"));
                break;
            case 16:
                t.setBackgroundColor(Color.parseColor("#FFA65F"));
                break;

            case 32:
                t.setBackgroundColor(Color.parseColor("#FF8740"));
                break;
            case 64:
                t.setBackgroundColor(Color.parseColor("#F97811"));
                break;
            case 128:
                t.setBackgroundColor(Color.parseColor("#FFC62F"));
                break;
            case 256:
                t.setBackgroundColor(Color.parseColor("#FFDB4E"));
                break;
            case 512:
                t.setBackgroundColor(Color.parseColor("#DD6B48"));
                break;
            case 1024:
                t.setBackgroundColor(Color.parseColor("#CC564A"));
                break;
            case 2048:
                t.setBackgroundColor(Color.parseColor("#E8E0D4"));
                break;
            default:
                t.setBackgroundColor(Color.parseColor("#B90B0B"));
                break;
        }
        if (this.num <= 0) {
            t.setText("");

        } else {
            t.setText(num + "");
        }
    }

    /**
     *判断是否为相同数字的卡片
     * @return
     */
    public boolean equal(Card a) {
        return getNum() == a.getNum();
    }

}
