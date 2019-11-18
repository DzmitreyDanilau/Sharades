package com.example.android.guesstheword.viewmodels

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
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
    private val _currentTime = MutableLiveData<Long>()
    private val currentTime: LiveData<Long>
        get() = _currentTime
    val currentTimeString = Transformations.map(currentTime) {
        DateUtils.formatElapsedTime(it)
    }
    lateinit var wordList: MutableList<String>
    lateinit var wordHint: LiveData<String>
    private var timer: CountDownTimer? = null

    init {
        Timber.d("GameViewModel: created!!")
        _word.value = ""
        _score.value = 0
        resetList()
        nextWord()
        startTimer()
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("GameViewModel: destroyed!!")
        timer?.cancel()
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
            resetList()
        } else {
            //Select and remove a word from the list
            _word.value = wordList.removeAt(0)
            showHint()
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

    private fun startTimer() {
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
            override fun onFinish() {
                _currentTime.value = DONE
                onGameFinished()
            }

            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished / ONE_SECOND
            }
        }.start()
    }

    private fun showHint() {
        wordHint = Transformations.map(word) { word ->
            val randomPosition = (1..word.length).random()
            "Current word has " + word.length + " letters" +
                    "\nThe letter at position " + randomPosition + " is " +
                    word[randomPosition - 1].toUpperCase()
        }
    }

    companion object {
        private const val DONE = 0L
        private const val ONE_SECOND = 1000L
        private const val COUNTDOWN_TIME = 60000L
    }
}