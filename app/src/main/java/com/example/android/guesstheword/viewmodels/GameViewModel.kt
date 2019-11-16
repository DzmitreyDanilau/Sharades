package com.example.android.guesstheword.viewmodels

import androidx.lifecycle.ViewModel
import timber.log.Timber

class GameViewModel  : ViewModel(){
 init {
     Timber.d("GameViewModel: created!!")
 }

    override fun onCleared() {
        super.onCleared()
        Timber.d("GameViewModel: destroyed!!")

    }
}