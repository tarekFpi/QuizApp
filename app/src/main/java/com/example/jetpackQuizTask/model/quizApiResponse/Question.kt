package com.example.jetpackQuizTask.model.quizApiResponse


data class Question(
    val answers: Answers,
    val correctAnswer: String,
    val question: String,
    val questionImageUrl: String,
    val score: Int
)