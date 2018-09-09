package com.avocode.alikhsan.basketballscoringapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreenActivity extends AppCompatActivity {
    @BindView(R.id.iv_basket)
    ImageView mIconBasket;

    Animation nAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);
        nAnimation = AnimationUtils.loadAnimation(this,R.anim.anim_basket_splash);
        mIconBasket.startAnimation(nAnimation);
        nThreeadSplash();
    }

    private void nThreeadSplash() {
        Thread nThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(10000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
        nThread.start();;
    }
}
