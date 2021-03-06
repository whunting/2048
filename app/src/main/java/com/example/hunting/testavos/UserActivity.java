package com.example.hunting.testavos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends Activity implements View.OnClickListener {
    private ListView lv;
    private UserAdapter ua;
    private List<AVObject> mList = new ArrayList<>();
    Button rank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_main);
        rank =findViewById(R.id.rrank);
        rank.setOnClickListener(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void setListView() {
        lv = findViewById(R.id.list_main);
        ua = new UserAdapter(mList,UserActivity.this);
        lv.setAdapter(ua);
    }

    private void initData() {
        mList.clear();
        final AVQuery<AVObject> avQuery = new AVQuery<>("score");
        avQuery.orderByDescending("createdAt");
        avQuery.include("owner");
        avQuery.whereEqualTo("owner", AVUser.getCurrentUser());
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null ) {
                    mList.addAll(list);
                    setListView();
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,rankActivity.class);
        startActivity(intent);
    }
}
