package com.medahane.apps.plus;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class ScoreActivity extends AppCompatActivity {

    TextView mTvScore;
    AdView mAdvBannerScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        mAdvBannerScore = (AdView) findViewById(R.id.adv_banner_score);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdvBannerScore.loadAd(adRequest);

        mTvScore = (TextView) findViewById(R.id.tv_score_score);
        SharedPreferences sharedPreferences = getSharedPreferences("mydata", 0);
        mTvScore.setText("+ " + sharedPreferences.getInt("score",0));
    }

    @Override
    public void onPause() {
        if (mAdvBannerScore != null) {
            mAdvBannerScore.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdvBannerScore!= null) {
            mAdvBannerScore.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdvBannerScore != null) {
            mAdvBannerScore.destroy();
        }
        super.onDestroy();
    }
}
