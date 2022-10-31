package com.example.personal_homework;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.nio.channels.InterruptedByTimeoutException;


public class ThirdActivity extends AppCompatActivity {

    Button btnInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Intent getLogin = getIntent();
        boolean isLogin = getLogin.getBooleanExtra("isLogin",false);
        String ID = getLogin.getStringExtra("ID");
        System.out.println(ID);
        btnInfo = (Button) findViewById(R.id.user_info);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isLogin == true) {
                    Intent go = new Intent(getApplicationContext(), InfoActivity.class);
                    go.putExtra("ID",ID);
                    startActivity(go);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ThirdActivity.this);
                    builder.setTitle("로그아웃 상태입니다");
                    builder.setMessage("로그인이나 회원가입을 해주세요");
                    builder.setPositiveButton("로그인하기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }
                    });
                    builder.setNegativeButton("회원가입하기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(getApplicationContext(), SecondActivity.class));
                        }
                    });
                    AlertDialog stateDlg = builder.create();
                    stateDlg.show();
                }

            }
        });

    }

}
