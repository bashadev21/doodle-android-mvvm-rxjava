package com.example.mvvmarc.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmarc.repository.JokeRepository

class JokesViewModelFactory(private val jokeRepository: JokeRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return JokesViewModel(jokeRepository) as T
    }
}