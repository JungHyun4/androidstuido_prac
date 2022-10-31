package com.example.personal_homework;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    Button LoginBtn;
    Button SignUpBtn;
    Button MainBtn;
    public boolean isLogin = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginBtn = findViewById(R.id.LogInbutton);
        SignUpBtn = findViewById(R.id.SignUPbutton);
        MainBtn = findViewById(R.id.toMain);
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            EditText id = (EditText) findViewById(R.id.InputId);
            EditText pw = (EditText) findViewById(R.id.InputPassword);

            @Override
            public void onClick(View view) {
                String inputid = id.getText().toString().trim();
                String inputpw = pw.getText().toString().trim();

                if (TextUtils.isEmpty(inputid)) {
                    Toast.makeText(getApplicationContext(), "이메일을 입력하세요", Toast.LENGTH_SHORT).show();
                    id.requestFocus();
                    return;
                }
                if (!Pattern.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$", inputid)) {
                    id.setError("이메일 형식으로 입력해주세요");
                    id.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(inputpw)) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                    pw.requestFocus();
                    return;
                }
                    SharedPreferences user = getSharedPreferences(inputid,0);
                    String ID = user.getString("ID","");
                    String PW = user.getString("Password","");


                if (ID.equals(inputid) && PW.equals(inputpw)) {
                    isLogin = true;
                    Intent intent = new Intent(getApplicationContext(), com.example.personal_homework.ThirdActivity.class);
                    intent.putExtra("isLogin",isLogin);
                    intent.putExtra("ID",ID);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"로그인 되었삼",Toast.LENGTH_SHORT).show();

                    return;
                }
                else{
                    Toast.makeText(getApplicationContext(),"등록된 사용자가 아닙니다",Toast.LENGTH_SHORT).show();
                }


            }


        });
        SignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.example.personal_homework.SecondActivity.class);
                startActivity(intent);
            }
        });

        MainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.example.personal_homework.ThirdActivity.class);
                startActivity(intent);
                intent.putExtra("isLogin",isLogin);
            }
        });


    }

}
