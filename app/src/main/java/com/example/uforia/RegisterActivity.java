package com.example.uforia;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    EditText mEmailText, mPasswordText, mStudentIdText, mName;
    Button mRegisterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mRegisterBtn = (Button) findViewById(R.id.completeRegi);
        mEmailText = findViewById(R.id.email_input);
        mPasswordText = findViewById(R.id.password_input);
        mStudentIdText = findViewById(R.id.student_number_input);
        mName = findViewById(R.id.name_input);
        firebaseAuth = FirebaseAuth.getInstance();

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                final String email = mEmailText.getText().toString().trim();
                String pwd = mPasswordText.getText().toString().trim();

                firebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(
                        RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // 가입 성공시
                                if(task.isSuccessful()){
                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    String email = user.getEmail();
                                    String uid = user.getUid();
                                    String name = mName.getText().toString().trim();

                                    // 해쉬맵 테이블을 파이어베이스 데이터베이스에 저장
                                    HashMap<Object, String> hashMap = new HashMap<>();

                                    hashMap.put("uid",uid);
                                    hashMap.put("email",email);
                                    hashMap.put("name",name);

                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    DatabaseReference reference = database.getReference("Users");
                                    reference.child(uid).setValue(hashMap);

                                    // 가입이 이루어졌을 시 가입 화면을 빠져나감
                                    Intent intent = new Intent(RegisterActivity.this, RegisterComActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(RegisterActivity.this, "회원가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(RegisterActivity.this, "다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                                    return; // 해당 메소드 진행을 멈추고 빠져나감
                                }
                            }
                        }
                );
            }
        });
    }
}