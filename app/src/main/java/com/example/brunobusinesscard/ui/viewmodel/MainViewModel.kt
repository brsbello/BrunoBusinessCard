package com.example.brunobusinesscard.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.brunobusinesscard.data.Card
import com.example.brunobusinesscard.data.CardRepository

class MainViewModel(private val cardRepository: CardRepository ) : ViewModel() {

    fun insert(card: Card){
        cardRepository.insert(card)
    }

    fun getAll() :LiveData<List<Card>>{
        return cardRepository.getAll()
    }

    fun update(card: Card){
        cardRepository.update(card)
    }

    fun delete(card: Card){
        cardRepository.delete(card)
    }
}