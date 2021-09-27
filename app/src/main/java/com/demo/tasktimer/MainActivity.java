package com.demo.tasktimer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.demo.tasktimer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private static final int REQ_CODE_NAME = 111;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


     binding.buttonStart.setOnClickListener(view -> {
         Intent intent = new Intent(MainActivity.this,TimerActivity.class);
         startActivityForResult(intent,REQ_CODE_NAME);});
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_NAME){
            if (resultCode == RESULT_OK){
              binding.textViewResult.setText(data.getStringExtra("returnNumber"));
            }
        }
    }
}