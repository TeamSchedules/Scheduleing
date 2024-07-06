package com.example.uforia;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class GrouppageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        Button button1 = (Button) findViewById(R.id.member);
        Button button2 = (Button) findViewById(R.id.meeting);
        Button button3 = (Button) findViewById(R.id.role);
        Button button4 = (Button) findViewById(R.id.resource);

        button1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MemberAddActivity.class);
                startActivity(intent);
            }

        });

        button2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MeetingAddActivity.class);
                startActivity(intent);
            }

        });

        button3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RoleSetActivity.class);
                startActivity(intent);
            }

        });

        button4.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ResourceActivity.class);
                startActivity(intent);
            }

        });
    }
}
