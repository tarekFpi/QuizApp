package com.example.jetpackQuizTask.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackQuizTask.model.quizApiResponse.Question
import com.example.jetpackQuizTask.repository.quizRepository
import com.example.jetpackQuizTask.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class quizViewModel @Inject constructor(private val quizRepository: quizRepository) : ViewModel(){


    val _responselist : StateFlow<Resource<List<Question>>>
        get() = quizRepository.stateflow_response


    init {

        viewModelScope.launch(Dispatchers.Main){

          quizRepository.qustionList()
        }
    }

}




