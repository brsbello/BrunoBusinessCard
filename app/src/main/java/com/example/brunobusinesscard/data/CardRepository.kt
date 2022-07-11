package com.example.brunobusinesscard.data

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CardRepository(private val dao: CardDAO) {

    fun insert(card: Card) = runBlocking {
        launch { dao.insert(card) }
    }

    fun getAll() = dao.getAll()

    fun update(card: Card) = runBlocking {
        launch { dao.update(card) }
    }

    fun delete(card: Card) = runBlocking {
        launch { dao.delete(card) }
    }
}
