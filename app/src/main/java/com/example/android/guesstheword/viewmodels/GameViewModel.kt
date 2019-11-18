package com.example.android.guesstheword.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class GameViewModel : ViewModel() {
    private var _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score
    private var _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word
    private var _isGameFinished = MutableLiveData<Boolean>()
    val isGameFinished: LiveData<Boolean>
        get() = _isGameFinished
    lateinit var wordList: MutableList<String>

    init {
        Timber.d("GameViewModel: created!!")
        _word.value = ""
        _score.value = 0
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
        if (wordList.isEmpty()) {
            onGameFinished()
        } else {
            //Select and remove a word from the list
            _word.value = wordList.removeAt(0)
        }
    }

    fun onSkip() {
        if (wordList.isNotEmpty()) {
            _score.value = (_score.value)?.minus(1)
        }
        nextWord()
    }

    fun onCorrect() {
        if (wordList.isNotEmpty()) {
            _score.value = (_score.value)?.plus(1)
        }
        nextWord()
    }

    fun onGameFinished() {
        _isGameFinished.value = true
    }

    fun onGameFinishComplete() {
        _isGameFinished.value = false
    }
}