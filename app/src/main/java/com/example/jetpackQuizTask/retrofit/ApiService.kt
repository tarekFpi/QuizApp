package com.example.jetpackQuizTask.retrofit
import com.example.jetpackQuizTask.model.quizApiResponse.quiz_response
import retrofit2.http.GET


interface ApiService {

    @GET("quiz.json")
     suspend fun getPost(): quiz_response
}