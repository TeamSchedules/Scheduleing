package com.example.uforia;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.content.Intent;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText mEmailText, mPasswordText;
    private FirebaseAuth firebaseAuth;
    Button mLoginBtn;
    Button mRegisterBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailText = (EditText)findViewById(R.id.email_input);
        mPasswordText = (EditText) findViewById(R.id.password_input);
        mLoginBtn = (Button) findViewById(R.id.loginBtn);
        mRegisterBtn = (Button) findViewById(R.id.regiBtn);
        firebaseAuth = FirebaseAuth.getInstance();

        // 로그인 버튼이 눌리면
        mLoginBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String email = mEmailText.getText().toString().trim();
                String pwd = mPasswordText.getText().toString().trim();
                firebaseAuth.signInWithEmailAndPassword(email,pwd)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(getApplicationContext(), MypageActivity.class);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(MainActivity.this, "로그인 오류", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        });

        // 가입 버튼이 눌리면
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                // Intent를 이용해 RegisterActivity 를 호출한다.
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
