package com.example.pavel.finalpj10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    Intent intent;
    TextView textView;
    long timer_s = 0;
    long timer_d = 500;
    long timer_f = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView logo = (ImageView) findViewById(R.id.Logo);
        Animation starAnim = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        logo.startAnimation(starAnim);
        textView=findViewById(R.id.textView);


        intent = new Intent(SplashActivity.this, MainActivity.class);

        starAnim.setAnimationListener(new Animation.AnimationListener() {

            public void onAnimationStart(Animation animation) {}

            public void onAnimationRepeat(Animation animation) {}

            public void onAnimationEnd(Animation animation) {
                startActivity(intent);


/* Чтобы по нажатию на кнопку «Назад» нельзя было снова попасть на экран
с заставкой приложения, вызовем метод finish()*/


                SplashActivity.this.finish();





            }


        });


    }
}

