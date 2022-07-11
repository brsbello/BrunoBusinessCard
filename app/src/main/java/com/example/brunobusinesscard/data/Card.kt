package com.example.brunobusinesscard.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Card(
    @PrimaryKey(autoGenerate = true) val id:Long = 0,
    val nome:String,
    val telefone:String,
    val email:String,
    val empresa:String
) : Parcelable