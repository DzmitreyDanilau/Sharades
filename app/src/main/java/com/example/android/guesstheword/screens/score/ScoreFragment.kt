package com.example.android.guesstheword.screens.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.GameFragmentBinding
import com.example.android.guesstheword.databinding.ScoreFragmentBinding
import com.example.android.guesstheword.viewmodels.ScoreViewModel
import com.example.android.guesstheword.viewmodels.ScoreViewModelFactory

class ScoreFragment : Fragment() {
    private lateinit var binding: ScoreFragmentBinding
    private lateinit var viewModel: ScoreViewModel
    private lateinit var scoreViewModelFactory: ScoreViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil
                .inflate(inflater, R.layout.score_fragment, container, false)
        scoreViewModelFactory = ScoreViewModelFactory(ScoreFragmentArgs.fromBundle(arguments!!).score)
        viewModel = ViewModelProviders.of(this, scoreViewModelFactory).get(ScoreViewModel::class.java)
        binding.scoreText.text = viewModel.score.toString()
        binding.playAgainButton.setOnClickListener { viewModel.onPlayAgain() }
        subscribeObservers()
        return binding.root
    }

    private fun subscribeObservers() {
        viewModel.score.observe(this, Observer {
            binding.scoreText.text = it.toString()
        })
        viewModel.eventPlayAgain.observe(this, Observer {
            if (it) findNavController().navigate(ScoreFragmentDirections.actionRestart())
            viewModel.onPlayAgainComplete()
        })
    }
}
