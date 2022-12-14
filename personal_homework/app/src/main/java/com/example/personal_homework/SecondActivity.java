package com.example.personal_homework;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class SecondActivity extends AppCompatActivity {
    private TextView banner;
    private EditText userEmail, password, username, phone, address;
    private RadioButton accept, decline;
    private Button submitBtn;
    private Button backbtn;

    public void saveInfo()
    {


        String ID = userEmail.getText().toString().trim();
        String PW = password.getText().toString().trim();
        String PhoneNumber = phone.getText().toString().trim();
        String NAME = username.getText().toString();
        String ADRESS = address.getText().toString();
        boolean accept1 = accept.isChecked();

        SharedPreferences userInfo = getSharedPreferences(ID, 0);
        SharedPreferences.Editor editor = userInfo.edit();

        editor.putString("ID", ID);
        editor.putString("Password", PW);
        editor.putString("PhoneNumber", PhoneNumber);
        editor.putString("Name", NAME);
        editor.putString("Adress", ADRESS);
        editor.putBoolean("Accept", accept1);
        editor.commit();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        banner = (TextView) findViewById(R.id.banner);
        userEmail = (EditText) findViewById(R.id.join_email);
        password = (EditText) findViewById(R.id.join_password);
        username = (EditText) findViewById(R.id.join_name);
        phone = (EditText) findViewById(R.id.join_phone);
        address = (EditText) findViewById(R.id.join_address);
        accept = (RadioButton) findViewById(R.id.join_clauseaccept);
        decline = (RadioButton) findViewById(R.id.join_clausedecline);
        submitBtn = (Button) findViewById(R.id.join_submit);
        backbtn = (Button) findViewById(R.id.back);

        if (savedInstanceState == null){
            SharedPreferences members = getSharedPreferences("member info",0);

            String ID = members.getString("ID","");
            String PW = members.getString("PW","");
            String PhoneNumber = members.getString("Phone Number","");
            String NAME = members.getString("name","");
            String ADRESS = members.getString("adress","");
            boolean accept = members.getBoolean("accept", false);
        }
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });



        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEMAIL = userEmail.getText().toString().trim();
                String userPWD = password.getText().toString().trim();
                String userNAME = username.getText().toString();
                String userPHONE = phone.getText().toString().trim();
                String userADDRESS = address.getText().toString();
                boolean isOk = false;
                if (TextUtils.isEmpty(userEMAIL)) {
                    userEmail.setError("???????????? ??????????????????");
                    userEmail.requestFocus();
                    return;
                }
                if(!Pattern.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$", userEMAIL)) {
                    userEmail.setError("????????? ???????????? ??????????????????");
                    userEmail.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(userPWD)) {
                    password.setError("??????????????? ??????????????????");
                    password.requestFocus();
                    return;
                }
                if (!Pattern.matches("^(?=.*[a-zA-Z0-9])(?=.*[a-zA-Z!@#$%^&*])(?=.*[0-9!@#$%^&*]).{8,15}$", userPWD)) {
                    password.setError("???????????? ????????? ???????????????\n??? ??????, ??????, ???????????? ??? 2?????? ?????? ??????(8~15???)");
                    password.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(userNAME)) {
                    username.setError("????????? ??????????????????");
                    username.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(userPHONE)) {
                    phone.setError("????????? ????????? ??????????????????");
                    phone.requestFocus();
                    return;
                }
                if (!Pattern.matches("^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$", userPHONE)) {
                    phone.setError("????????? ????????? ????????? ????????????.");
                    phone.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(userADDRESS)) {
                    address.setError("????????? ??????????????????");
                    address.requestFocus();
                    return;
                }
                if (!accept.isChecked()){
                    Toast.makeText(getApplicationContext(), "???????????? ?????? ????????? ???????????? ???????????? ???????????????.",
                            Toast.LENGTH_SHORT).show();
                }
                else
                    isOk = true;

                if(isOk == true) {
                    saveInfo();
                    Toast.makeText(getApplicationContext(), "??????????????? ?????? ???????????????.", Toast.LENGTH_SHORT).show();
                    finish();
                }
                    }
                });
            }
        }

