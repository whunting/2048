package com.example.hunting.testavos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.SignUpCallback;

public class registerAcitivity extends AppCompatActivity implements View.OnClickListener{
    Button btn_Rback,btn_Rregister;
    EditText edit_rename,edit_repw,edit_reemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        btn_Rback = (Button)findViewById(R.id.Rback);
        btn_Rregister = (Button)findViewById(R.id.Rregister);
        edit_reemail= (EditText)findViewById(R.id.Rml);
        edit_rename = (EditText)findViewById(R.id.Rnm);
        edit_repw = (EditText)findViewById(R.id.Rpw);
        btn_Rback.setOnClickListener(this);
        btn_Rregister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Rback:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.Rregister:
                AVUser user = new AVUser();
                user.setEmail(edit_reemail.getText().toString());
                user.setUsername(edit_rename.getText().toString());
                user.setPassword(edit_repw.getText().toString());
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(AVException e) {
                        if (e == null) {
                            startActivity(new Intent(registerAcitivity.this, successActivity.class));
                        } else {
                            Toast.makeText(registerAcitivity.this, "注册失败，请重新注册", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                break;
        }
    }
}
