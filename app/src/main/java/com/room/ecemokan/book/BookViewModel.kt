package com.room.ecemokan.book

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.os.AsyncTask

class BookViewModel(application: Application): AndroidViewModel(application) {

	val allBooks: LiveData<List<Book>>
	private val bookDao: BookDao

	init {
		val bookDb = BookRoomDatabase.getDatabase(application)
		bookDao = bookDb!!.bookDao()
		allBooks = bookDao.allBooks
	}

	fun insert(book: Book) {
		InsertAsyncTask(bookDao).execute(book)
	}

	companion object {
		private class InsertAsyncTask(private val bookDao: BookDao) : AsyncTask<Book, Void, Void>() {

			override fun doInBackground(vararg books: Book): Void? {
				bookDao.insert(books[0])
				return null
			}

		}
	}
}