package com.example.quizappmorning.service;


import com.example.quizappmorning.model.QuestionList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuestionApi {

    @GET("myquizapi.php")
    Call<QuestionList> getQuestions();
}
