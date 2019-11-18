package com.example.android.guesstheword.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ScoreViewModelFactory(private val finalScore:Int)  : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ScoreViewModel::class.java)){
            @Suppress("UNCHECKED CAST")
            return ScoreViewModel(finalScore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}