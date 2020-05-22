package com.hnam.week5demo.room

import androidx.room.*



@Dao
interface AccountDAO {
    @Query("SELECT * FROM account")
    fun getAll(): List<Account>


    @Query("SELECT * FROM account WHERE name LIKE :name")
    fun findByName(name: String): Account

    @Query("SELECT * FROM account WHERE id =:id")
    fun findById(id: Int): Account

    @Insert
    fun insertAll(vararg todo: Account) : List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(obj: Account): Long

    @Delete
    fun delete(todo: Account)

    @Update
    fun update( account: Account)

    @Query("DELETE FROM account")
    fun deleteAll()
}