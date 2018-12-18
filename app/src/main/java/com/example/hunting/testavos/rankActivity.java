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
import com.avos.avoscloud.FindCallback;

import java.util.ArrayList;
import java.util.List;

public class rankActivity extends Activity implements View.OnClickListener {
    private ListView rlv;
    private rankAdapter ra;
    private List<AVObject> rList = new ArrayList<>();
    Button history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_rank);
        history = findViewById(R.id.history);
        history.setOnClickListener(this);

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
        rlv = findViewById(R.id.list_rank);
        ra = new rankAdapter(rList,rankActivity.this);
        rlv.setAdapter(ra);
    }

    private void initData() {
        rList.clear();
        final AVQuery<AVObject> avQuery = new AVQuery<>("_User");
        avQuery.orderByDescending("bestScore");
        avQuery.include("username");
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null ) {
                    rList.addAll(list);
                    setListView();
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,UserActivity.class);
        startActivity(intent);
    }
}
