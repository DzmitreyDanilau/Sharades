package com.example.android.guesstheword.screens.game

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.GameFragmentBinding
import com.example.android.guesstheword.viewmodels.GameViewModel
import timber.log.Timber

/**
 * Fragment where the game is played
 */
class GameFragment : Fragment() {
    private lateinit var binding: GameFragmentBinding
    private lateinit var viewModel: GameViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Timber.d("GameFragment: onCreateView")
        Timber.d("Called viewModelProvider")
        viewModel = ViewModelProviders.of(this)[GameViewModel::class.java]
        binding = DataBindingUtil
                .inflate(inflater, R.layout.game_fragment, container, false)

        binding.correctButton.setOnClickListener { onCorrect() }
        binding.skipButton.setOnClickListener { onSkip() }
        updateScoreText()
        updateWordText()
        return binding.root

    }

    private fun updateWordText() {
        binding.wordText.text = word
    }

    private fun updateScoreText() {
        binding.scoreText.text = score.toString()
    }







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("GameFragment: onCreate")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Timber.d("GameFragment: onAttach")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("GameFragment: onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        Timber.d("GameFragment: onStart")
    }

    override fun onPause() {
        super.onPause()
        Timber.d("GameFragment: onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("GameFragment: onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.d("GameFragment: onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        Timber.d("GameFragment: onDetach")
    }

}
