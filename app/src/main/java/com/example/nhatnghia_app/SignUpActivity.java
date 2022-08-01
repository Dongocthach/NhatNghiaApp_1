package com.example.nhatnghia_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignUpActivity extends AppCompatActivity {
    private EditText edEmail, edtPassword;
    private Button btnSignUp;
    private ProgressDialog progressDialog;
    private LinearLayout llsingup;
    private ImageView imgsingup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initUI();
        initListerner();


        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slideshow);
        llsingup.startAnimation(animation);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slideshow1);
        imgsingup.startAnimation(animation1);


    }

    private void initUI(){
        edEmail = findViewById(R.id.SU_ed1);
        edtPassword = findViewById(R.id.SU_ed2);
        btnSignUp = findViewById(R.id.SU_btn1);
        llsingup = findViewById(R.id.llsingup);
        imgsingup = findViewById(R.id.imgsingup);
        progressDialog = new ProgressDialog(this);
    }

    private void initListerner(){
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSignUp();
            }
        });
    }

    private  void onClickSignUp(){
        String strEmail = edEmail.getText().toString().trim();
        String strPassword = edtPassword.getText().toString().trim();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(strEmail, strPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                             Toast.makeText(SignUpActivity.this, "createUserWithEmail:success",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(intent);
                            finishAffinity();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}