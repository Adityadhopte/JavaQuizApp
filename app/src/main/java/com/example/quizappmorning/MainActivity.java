package com.example.quizappmorning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.quizappmorning.databinding.ActivityMainBinding;
import com.example.quizappmorning.model.Question;
import com.example.quizappmorning.model.QuestionList;
import com.example.quizappmorning.viewmodel.QuizViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    QuizViewModel quizViewModel;
    List<Question> quizquestionList;
    static int result = 0;
    static int totalQuestions = 0;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        quizViewModel = new ViewModelProvider(this).get(QuizViewModel.class);

        result = 0;
        totalQuestions = 0;

        displayFirstQuestion();

        activityMainBinding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayNextQuestions();

                if (activityMainBinding.btnNext.getText().toString().equals("Finish")) {
                    Intent i = new Intent(MainActivity.this, ResultActivity.class);
                    startActivity(i);
                }

            }
        });
    }

    private void displayFirstQuestion() {
        quizViewModel.getQuestionListLiveData().observe(this, new Observer<QuestionList>() {
            @Override
            public void onChanged(QuestionList questions) {
                quizquestionList = questions;
                displayQuestion(0);
            }
        });
    }

    private void displayQuestion(int index) {
        if (index < quizquestionList.size()) {
            totalQuestions = quizquestionList.size();
            activityMainBinding.txtQuestion.setText("Question " + (index + 1) + " : " +
                    quizquestionList.get(index).getQuestion());

            activityMainBinding.radio1.setText(quizquestionList.get(index).getOption1());
            activityMainBinding.radio2.setText(quizquestionList.get(index).getOption2());
            activityMainBinding.radio3.setText(quizquestionList.get(index).getOption3());
            activityMainBinding.radio4.setText(quizquestionList.get(index).getOption4());

            activityMainBinding.radioGroup.clearCheck();
            i = index;


        } else {
            activityMainBinding.btnNext.setText("Finish");

        }
    }


    public void displayNextQuestions() {


        int selectedOption = activityMainBinding.radioGroup.getCheckedRadioButtonId();
        if (selectedOption != -1) {
            RadioButton radioButton = findViewById(selectedOption);

            if (i < quizquestionList.size()) {
                if (radioButton.getText().toString().equals(quizquestionList.get(i).getCorrectOption())) {
                    result++;
                    activityMainBinding.txtResult.setText("Correct Answers : " + result);

                }
                i++;


                displayQuestion(i);
            }

        } else {
            Toast.makeText(this, "You need to make a selection", Toast.LENGTH_SHORT).show();
        }
    }
}
