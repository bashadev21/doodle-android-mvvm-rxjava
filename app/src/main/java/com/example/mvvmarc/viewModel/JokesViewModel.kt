package com.example.mvvmarc.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmarc.model.JokesModel
import com.example.mvvmarc.repository.JokeRepository
import kotlinx.coroutines.launch

class JokesViewModel(private val jokeRepository: JokeRepository):ViewModel() {
    init {
        viewModelScope.launch {
            jokeRepository.getJokes()
        }
    }
    val jokes:LiveData<JokesModel>
    get() = jokeRepository.jokes
}