package com.example.hunting.testavos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_login,btn_register;
    EditText edit_name,edit_pw;
    CheckBox checkBox1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_login = (Button)findViewById(R.id.login);
        btn_register = (Button)findViewById(R.id.register);
        edit_name = (EditText)findViewById(R.id.Enm);
        edit_pw = (EditText)findViewById(R.id.Epw);
        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        checkBox1 = (CheckBox) findViewById(R.id.cbDisplayPassword);

        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    edit_pw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    edit_pw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                try {
                    AVUser.logInInBackground(edit_name.getText().toString(), edit_pw.getText().toString(), new LogInCallback<AVUser>() {
                        @Override
                        public void done(AVUser avUser, AVException e) {
                            if (e==null){
                                startActivity(new Intent(MainActivity.this, successActivity.class));
                                finish();
                            }
                            else{
                                Toast.makeText(MainActivity.this,"用户名或密码错误",Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                } catch (IllegalArgumentException e) {
                    Toast.makeText(MainActivity.this,"用户名和密码不能为空",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.register:
                Intent intent = new Intent(this,registerAcitivity.class);
                startActivity(intent);
                break;
        }
    }
}
