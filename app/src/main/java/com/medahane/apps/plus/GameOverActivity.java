package com.medahane.apps.plus;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        String title = intent.getStringExtra("title");

        getDialog(score, title);
    }

    public void getDialog(int score, String title) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_restart);

        dialog.setCanceledOnTouchOutside(false);

        TextView tvScore = (TextView) dialog.findViewById(R.id.tv_score_restart);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tv_title_restart);
        Button btnRestart = (Button) dialog.findViewById(R.id.btn_restart);

        tvScore.setText("Score: " + score);
        tvTitle.setText(title);

        dialog.show();

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                    startActivity(intent);
            }
        });

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                finish();
            }
        });



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
