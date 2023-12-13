package com.example.quizappmorning;

import android.os.Bundle;

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

        DisplayFirstQuestion();
    }

    private void DisplayFirstQuestion() {

        quizViewModel.getQuestionListLiveData().observe(this, new Observer<QuestionList>() {
            @Override
            public void onChanged(QuestionList questions) {


                quizquestionList = questions;

                activityMainBinding.txtQuestion.setText("Question 1" + questions.get(0).getQuestion());
                activityMainBinding.radio1.setText(questions.get(0).getOption1());
                activityMainBinding.radio2.setText(questions.get(0).getOption2());
                activityMainBinding.radio3.setText(questions.get(0).getOption3());
                activityMainBinding.radio4.setText(questions.get(0).getOption4());


            }
        });
    }


}