package com.example.jetpackQuizTask.model.quizOfflineResponse


data class quizeRespose(
    val question:String,
    val answers1:String,
    val answers2:String,
    val answers3:String,
    val answers4:String,
    val correctAnswer:String,
)

val QuizeList = listOf(
    quizeRespose(
        "Math Question 1: The average of first 50 natural numbers is ??",
        "A",
        "B",
        "C",
        "D",
        "B"
    ),

    quizeRespose(
        "Math Question 2: The number of 3-digit numbers divisible by 6, is ??",
        "A",
        "B",
        "C",
        "D",
        "C"
    ),


    quizeRespose(
        "Physics Question 3: Which among the following is measured using a Vernier Caliper??",
        "A",
        "B",
        "C",
        "D",
        "A"
    ),

    quizeRespose(
        "Physics Question 4: Which among the following temperature scale is based upon absolute zero?",
        "A",
        "B",
        "C",
        "D",
        "B"
    ),

    quizeRespose(
        "Physics Question 5: Which among the following oxides cause the Acid rain?",
        "A",
        "B",
        "C",
        "D",
        "D"
    ),

    quizeRespose(
        "Physics Question 6: hich of the following type of Coal has maximum carbon content?",
        "A",
        "B",
        "C",
        "D",
        "C"
    ),
    )