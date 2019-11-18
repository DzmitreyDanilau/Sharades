package com.example.android.guesstheword.viewmodels

import androidx.lifecycle.ViewModel
import timber.log.Timber

class ScoreViewModel(val finalScore: Int) : ViewModel() {
    var score = finalScore

    init {
        Timber.d("Final score is: $score")
    }
}