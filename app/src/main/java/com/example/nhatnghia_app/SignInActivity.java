package com.example.nhatnghia_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class SignInActivity extends AppCompatActivity {
    private EditText ed1,ed2;
    private TextView tv1,tv2;
    private Button btn1,btn2;
    private ProgressDialog progressDialog;
    private LinearLayout lldangnhap;

//    private AwesomeValidation validation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initUi();
        initListener();



    }
    private  void initUi()
    {
        progressDialog = new ProgressDialog(SignInActivity.this);
        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        tv1 =findViewById(R.id.tv1);
        tv2= findViewById(R.id.tv2);
        btn1 = findViewById(R.id.login_btn1);
//        btn2 = findViewById(R.id.login_btn2);
        lldangnhap = findViewById(R.id.lldangnhap);
//
    }
    private  void initListener()
    {   tv1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onClickForgotPassword();
        }
    });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSignIn();
            }
        });
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    private void onClickForgotPassword() {
        progressDialog.show();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress = ed1.getText().toString().trim();

        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignInActivity.this,"email sent!", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(SignInActivity.this,"email sent failed!", Toast.LENGTH_SHORT).show();

                        }
                        progressDialog.dismiss();
                    }
                });
    }

    private void onClickSignIn() {
        String strEmail = ed1.getText().toString().trim();
        String strPwd = ed2.getText().toString().trim();
        progressDialog.show();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(strEmail, strPwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        String tentaikhoan = ed1.getText().toString();
                        String matkhau = ed2.getText().toString();
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                            startActivity(intent);
                            finishAffinity();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignInActivity.this, "Wrong user name or password!",
                                    Toast.LENGTH_SHORT).show();
                            ed1.setError("Email khong chinh xác");

                            ed2.setError("Mật không chính xác");

                        }
                    }
                });
}

    }

