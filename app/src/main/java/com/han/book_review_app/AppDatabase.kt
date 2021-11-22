package com.han.book_review_app

import androidx.room.Database
import androidx.room.RoomDatabase
import com.han.book_review_app.dao.HistoryDao
import com.han.book_review_app.model.History

@Database(entities = [History::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}