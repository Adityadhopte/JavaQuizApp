package com.example.quizappmorning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.quizappmorning.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity {

    ActivityResultBinding resultBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultBinding = DataBindingUtil.setContentView(this, R.layout.activity_result);

        resultBinding.txtAnswer.setText("Your Score is : " + MainActivity.result + "/" + MainActivity.totalQuestions);

        resultBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i  =new  Intent(ResultActivity.this,MainActivity.class);
                startActivity(i);

            }
        });



    }
}