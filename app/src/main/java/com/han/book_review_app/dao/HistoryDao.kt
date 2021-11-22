package com.han.book_review_app.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.han.book_review_app.model.History

@Dao
interface HistoryDao {
    @Query("SELECT * FROM History")
    fun getAll(): List<History>

    @Insert
    fun insertHistory(history: History)

    @Query("DELETE FROM history WHERE keyword == :keyword")
    fun delete(keyword: String)
}