package com.example.quizappmorning.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizappmorning.model.QuestionList;
import com.example.quizappmorning.repository.QuizRepository;


public class QuizViewModel extends ViewModel {
    QuizRepository repository = new QuizRepository();


    LiveData<QuestionList> questionListLiveData;

    public QuizViewModel(){
        questionListLiveData = repository.getQuestionFromApi();
    }

    public LiveData<QuestionList> getQuestionListLiveData() {
        return questionListLiveData;
    }
}
