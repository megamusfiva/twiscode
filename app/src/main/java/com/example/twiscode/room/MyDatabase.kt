package com.example.twiscode.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Cart::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao

    companion object {
        private var INSTANCE: MyDatabase? = null
        fun getInstance(context: Context): MyDatabase? {
            if (INSTANCE == null) {
                synchronized(MyDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            MyDatabase::class.java, "MyDatabase.db"
                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}