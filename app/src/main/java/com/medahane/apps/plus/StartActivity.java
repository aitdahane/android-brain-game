package com.medahane.apps.plus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


public class StartActivity extends AppCompatActivity {


    Button mBtnStart;
    Button mBtnScore;
    AdView mAdvBanner;
    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mInterstitialAd = new InterstitialAd(this);

        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));

        AdRequest adRequest1 = new AdRequest.Builder()
                .build();

        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest1);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
            }
        });

        mAdvBanner = (AdView) findViewById(R.id.adv_banner_start);
        AdRequest adRequest2 = new AdRequest.Builder()
                .build();
        mAdvBanner.loadAd(adRequest2);

        SharedPreferences sharedPref= getSharedPreferences("mydata", 0);
        if (!sharedPref.contains("score")) {
            SharedPreferences.Editor editor= sharedPref.edit();
            editor.putInt("score", 0);
            editor.commit();
        }



        mBtnStart = (Button) findViewById(R.id.btn_start);
        mBtnScore = (Button) findViewById(R.id.btn_score);

        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        mBtnScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ScoreActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onPause() {
        if (mAdvBanner != null) {
            mAdvBanner.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdvBanner != null) {
            mAdvBanner.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdvBanner!= null) {
            mAdvBanner.destroy();
        }
        super.onDestroy();
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
