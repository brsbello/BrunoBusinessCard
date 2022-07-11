package com.example.brunobusinesscard.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.brunobusinesscard.App
import com.example.brunobusinesscard.databinding.ActivityMainBinding
import com.example.brunobusinesscard.ui.AddCardActivity.Companion.CARD_ID
import com.example.brunobusinesscard.ui.adapter.CardAdapter
import com.example.brunobusinesscard.ui.viewmodel.MainViewModel
import com.example.brunobusinesscard.ui.viewmodel.MainViewModelFactory
import com.example.brunobusinesscard.utils.Image

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels { MainViewModelFactory((application as App).repository) }
    private val adapter by lazy { CardAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupAdapter()
        insertListeners()
    }

    override fun onResume() {
        super.onResume()
        getAllCards()
    }

    private fun insertListeners() {
        setupFAB()
        editCard()
        deleteCard()
    }


    private fun setupFAB(){
        binding.fabAdd.setOnClickListener{
            val intent = Intent(this, AddCardActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getAllCards(){
        mainViewModel.getAll().observe(this) { adapter.submitList(it) }
    }

    private fun setupAdapter() {
        binding.rvCards.adapter = adapter
    }

    private fun editCard() {
        adapter.listenerEdit = {
            Intent(this, AddCardActivity::class.java).apply {
                putExtra(CARD_ID, it)
                startActivity(this)
            }
        }
    }

    private fun deleteCard() {
        adapter.listenerDelete = {
            mainViewModel.delete(it)
            getAllCards()
        }
    }

    private fun share(){
        adapter.listenerShare = {card -> 
            Image.share(this@MainActivity, card)
        }
    }
}