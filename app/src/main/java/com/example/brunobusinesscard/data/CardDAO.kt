package com.example.brunobusinesscard.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE

@Dao
interface CardDAO {

    @Query("SELECT * FROM Card")
    fun getAll(): LiveData<List<Card>>

    @Insert(onConflict = IGNORE)
    suspend fun insert (card : Card)

    @Update
    fun update(card : Card)

    @Delete
    fun delete(card : Card)
}