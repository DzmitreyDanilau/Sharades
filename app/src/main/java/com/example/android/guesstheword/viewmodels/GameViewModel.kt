package com.example.android.guesstheword.viewmodels

import androidx.lifecycle.ViewModel
import timber.log.Timber

class GameViewModel : ViewModel() {
    var word = ""
    var score = 0
    lateinit var wordList: MutableList<String>

    init {
        Timber.d("GameViewModel: created!!")
        resetList()
        nextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("GameViewModel: destroyed!!")
    }


    private fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }

    private fun nextWord() {
        if (wordList.isNotEmpty()) {
            //Select and remove a word from the list
            word = wordList.removeAt(0)
        }
        updateWordText()
        updateScoreText()
    }

     fun onSkip() {
        if (wordList.isNotEmpty()) {
            score--
        }
        nextWord()
    }

     fun onCorrect() {
        if (wordList.isNotEmpty()) {
            score++
        }
        nextWord()
    }
}