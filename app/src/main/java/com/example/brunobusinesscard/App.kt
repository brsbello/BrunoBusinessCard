package com.example.brunobusinesscard

import android.app.Application
import com.example.brunobusinesscard.data.AppDataBase
import com.example.brunobusinesscard.data.CardRepository

class App : Application() {
    val database by lazy { AppDataBase.getDataBase(this) }
    val repository by lazy { CardRepository(database.dao()) }
}