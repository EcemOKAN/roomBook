package com.room.ecemokan.book

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface BookDao {

	@Insert
	fun insert(book: Book)

//	@Query("SELECT * FROM books")
//	fun getAllBooks(): LiveData<List<Book>>

	@get:Query("SELECT * FROM books")
	val allBooks: LiveData<List<Book>>
}
