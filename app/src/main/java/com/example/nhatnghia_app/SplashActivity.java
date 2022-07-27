package com.example.nhatnghia_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {
    private TextView appname;
    private LottieAnimationView lottie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        appname = (TextView) findViewById(R.id.appname);
        lottie = (LottieAnimationView) findViewById(R.id.lottie);
        appname.animate().translationY(-2400).setDuration(2700).setStartDelay(0);
        appname.animate().translationX(2000).setDuration(2000).setStartDelay(2900);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                nextActivity();
            }
        },3000);
    }

    private void nextActivity(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            //chua login
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
        }else{
            //da login
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        finish();
    }
}