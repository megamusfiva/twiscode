package com.example.twiscode.room

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface CartDao {

    @Insert(onConflict = REPLACE)
    fun insert(cart: Cart)

    @Delete
    fun delete(cart: Cart)

    @Update
    fun update(cart: Cart)

    @Query("SELECT * from Cart ORDER BY id ASC")
    fun getAll(): List<Cart>

    @Query("SELECT * FROM Cart WHERE id = :id LIMIT 1")
    fun getProduk(id: Int?): Cart

    @Transaction
    @Query("SELECT * FROM Cart WHERE id = :id")
    fun getById(id: Long) : Cart
}