package com.medahane.apps.plus;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView mTvScore;
    TextView mTvTimer;
    Button mBtnOp1;
    Button mBtnOp2;
    Button mBtnResult1;
    Button mBtnResult2;
    Button mBtnResult3;
    AdView mAdvBannerMain;

    int mScore;
    int mOp1, mOp2, mResult1, mResult2, mResult3;
    CountDownTimer cdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdvBannerMain = (AdView) findViewById(R.id.adv_banner_main);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdvBannerMain.loadAd(adRequest);

          /* find views */
        mTvScore = (TextView) findViewById(R.id.tv_score);
        mTvTimer = (TextView) findViewById(R.id.tv_timer);
        mBtnOp1 = (Button) findViewById(R.id.btn_op1);
        mBtnOp2 = (Button) findViewById(R.id.btn_op2);
        mBtnResult1 = (Button) findViewById(R.id.btn_result1);
        mBtnResult2 = (Button) findViewById(R.id.btn_result2);
        mBtnResult3 = (Button) findViewById(R.id.btn_result3);

        int score = getScore();
        mScore = 0;
        mTvScore.setText("+ " + score);

        cdt =  new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                mTvTimer.setText("0" + millisUntilFinished / 1000 + "");
            }

            public void onFinish() {
                Intent intent = new Intent(getApplicationContext(), GameOverActivity.class);
                intent.putExtra("score", mScore);
                intent.putExtra("title", "Time's up!");
                finish();
                startActivity(intent);
            }
        };

        generateNumbers(mScore);
        cdt.start();

        mBtnResult1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOp1 + mOp2 == mResult1) {
                    mScore = mResult1;
                    generateNumbers(mScore);
                    cdt.cancel();
                    cdt.start();
                    if (mScore > getScore()) {
                        setScore(mScore);
                        mTvScore.setText(getScore()+"");
                    }

                } else {
                    cdt.cancel();
                    Intent intent = new Intent(getApplicationContext(), GameOverActivity.class);
                    intent.putExtra("score", mScore);
                    intent.putExtra("title", "Wrong Answer!");
                    finish();
                    startActivity(intent);
                }

            }
        });

        mBtnResult2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOp1 + mOp2 == mResult2) {
                    mScore = mResult2;
                    generateNumbers(mScore);
                    cdt.cancel();
                    cdt.start();
                    if (mScore > getScore()) {
                        setScore(mScore);
                        mTvScore.setText(getScore()+"");
                    }
                } else {
                    cdt.cancel();
                    Intent intent = new Intent(getApplicationContext(), GameOverActivity.class);
                    intent.putExtra("score", mScore);
                    intent.putExtra("title", "Wrong Answer!");
                    finish();
                    startActivity(intent);
                }

            }
        });

        mBtnResult3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOp1 + mOp2 == mResult3) {
                    mScore = mResult3;
                    generateNumbers(mScore);
                    cdt.cancel();
                    cdt.start();
                    if (mScore > getScore()) {
                        setScore(mScore);
                        mTvScore.setText(getScore()+"");
                    }
                } else {
                    cdt.cancel();
                    Intent intent = new Intent(getApplicationContext(), GameOverActivity.class);
                    intent.putExtra("score", mScore);
                    intent.putExtra("title", "Wrong Answer!");
                    finish();
                    startActivity(intent);
                }

            }
        });

    }

    public void generateNumbers(int score) {
        int level;
        if (score < 50) {
            level = 6;
        } else if (score < 100) {
            level = 15;
        } else if (score < 200) {
            level = 25;
        } else {
            level = 40;
        }
        int i0 = 0, i1 = 0, i2 = 0, v0 = 0, v1 = 0, v2 = 0;
        int op1 = 0;
        int op2 = 0;
        Random rand = new Random();
        HashMap<Integer, Integer> results = new HashMap<>();

        op1 = score;
        op2 = rand.nextInt(level) + 1;

        do {
                i0 = rand.nextInt(3);
                i1 = rand.nextInt(3);
                i2 = rand.nextInt(3);
        } while (i0 == i1 || i1 == i2 || i2 == i0);


        v0 = op1 + op2;

        do {
            v1 = op1 + rand.nextInt(level) + 1;
            v2 = op1 + rand.nextInt(level) + 1;
        } while (v0 == v1 || v1 == v2 || v2 == v0);

        results.put(i0, v0);
        results.put(i1, v1);
        results.put(i2, v2);

        mOp1 = op1;
        mOp2 = op2;
        mResult1 = results.get(0);
        mResult2 = results.get(1);
        mResult3 = results.get(2);

        mBtnOp1.setText(mOp1 + "");
        mBtnOp2.setText(mOp2 + "");
        mBtnResult1.setText(mResult1 + "");
        mBtnResult2.setText(mResult2 + "");
        mBtnResult3.setText(mResult3 + "");




    }


    @Override
    protected void onPause() {
        if (mAdvBannerMain != null) {
            mAdvBannerMain.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdvBannerMain != null) {
            mAdvBannerMain.resume();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        cdt.cancel();
        finish();
    }

    @Override
    protected void onDestroy() {
        if (mAdvBannerMain != null) {
            mAdvBannerMain.destroy();
        }
        super.onDestroy();
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }


    public void setScore(int score) {
        SharedPreferences sharedPref= getSharedPreferences("mydata", 0);
        SharedPreferences.Editor editor= sharedPref.edit();
        editor.putInt("score", score);
        editor.commit();
    }

    public int getScore() {
        SharedPreferences sharedPref= getSharedPreferences("mydata", 0);
        return sharedPref.getInt("score", 0);
    }



}
