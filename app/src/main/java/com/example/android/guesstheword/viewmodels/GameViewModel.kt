package com.example.android.guesstheword.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class GameViewModel : ViewModel() {
    var word = MutableLiveData<String>()
    var score = MutableLiveData(0)
    lateinit var wordList: MutableList<String>

    init {
        Timber.d("GameViewModel: created!!")
        word.value = ""
        score.value = 0
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
            word.value = wordList.removeAt(0)
        }
    }

     fun onSkip() {
        if (wordList.isNotEmpty()) {
            score.value = (score.value)?.minus(1)
        }
        nextWord()
    }

     fun onCorrect() {
        if (wordList.isNotEmpty()) {
            score.value = (score.value)?.plus(1)
        }
        nextWord()
    }
}