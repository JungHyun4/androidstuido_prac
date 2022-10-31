package com.example.personal_homework;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity {


    TextView email, name, phone, address;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Button BackBtn = (Button) findViewById(R.id.back_button);
        email = (TextView) findViewById(R.id.info_email);
        name = (TextView) findViewById(R.id.info_name);
        phone = (TextView) findViewById(R.id.info_phone);
        address = (TextView) findViewById(R.id.info_address);


        Intent jaja = getIntent();
        String getID = jaja.getStringExtra("ID");
        System.out.println(getID);

            SharedPreferences userinfo = getSharedPreferences(getID,0);

            String ID = userinfo.getString("ID","");
            String PhoneNumber = userinfo.getString("PhoneNumber","");
            String Name = userinfo.getString("Name","");
            String Adress = userinfo.getString("Adress","");

            email.setText(ID);
            name.setText(Name);
            phone.setText(PhoneNumber);
            address.setText(Adress);
        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

