package com.demo.tasktimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TimerActivity extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private TextView textViewTime;
    private TextView textViewTimer;
    private Button buttonStop;
    private String templ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        textViewTime = findViewById(R.id.textViewTime);
        textViewTimer = findViewById(R.id.textViewTimer);
        buttonStop = findViewById(R.id.buttonStop);

        preferences = getSharedPreferences("lastPreferences", MODE_PRIVATE);
        editor = preferences.edit();

        String time = preferences.getString("te","0");
        if (textViewTimer.getText().toString().equals("") || textViewTimer.getText().toString() ==null){
            templ ="60000";
        }else{
            templ = textViewTimer.getText().toString();
        }

        CountDownTimer timer = new CountDownTimer(Long.parseLong(templ),1000) {
            @Override
            public void onTick(long l) {
                textViewTimer.setText(""+ l/1000);
            }

            @Override
            public void onFinish() {
                textViewTimer.setText("finished");
            }
        };
        timer.start();
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("te", textViewTimer.getText().toString());
                editor.commit();
                Intent intentReturn = new Intent();
                intentReturn.putExtra("returnNumber",textViewTimer.getText().toString());
                setResult(RESULT_OK,intentReturn);
                finish();


            }
        });
    }
}