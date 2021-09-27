package com.demo.tasktimer;

import static com.demo.tasktimer.Values.TIMER_KEY;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

import com.demo.tasktimer.databinding.ActivityTimerBinding;

public class TimerActivity extends AppCompatActivity {

    ActivityTimerBinding binding;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private Long startTime;
    private long lastSavedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTimerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferences = getSharedPreferences("lastPreferences", MODE_PRIVATE);
        editor = preferences.edit();
        startTime = preferences.getLong(TIMER_KEY, 30);
        startTime = startTime == 0 ? 30 : startTime;

        binding.textViewTime.setText(String.valueOf(startTime));

        Toast.makeText(getApplicationContext(), String.valueOf(startTime), Toast.LENGTH_SHORT).show();

        CountDownTimer timer = new CountDownTimer(startTime * 1000, 1000) {
            @Override
            public void onTick(long l) {
                lastSavedTime = l / 1000;

                editor.putLong(TIMER_KEY, lastSavedTime);
                editor.commit();
                binding.textViewTimer.setText(String.valueOf(lastSavedTime));
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent();
                intent.putExtra("returnNumber", String.valueOf(lastSavedTime));
                setResult(RESULT_OK, intent);
                finish();
            }
        };
        timer.start();

        binding.buttonStop.setOnClickListener(view -> {
            editor.putLong(TIMER_KEY, lastSavedTime);
            editor.commit();
            Toast.makeText(getApplicationContext(), String.valueOf(startTime), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.putExtra("returnNumber", String.valueOf(lastSavedTime));
            setResult(RESULT_OK, intent);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("returnNumber", String.valueOf(lastSavedTime));
        setResult(RESULT_OK, intent);
        finish();
    }
}