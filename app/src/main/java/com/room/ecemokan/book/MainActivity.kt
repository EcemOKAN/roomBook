package com.room.ecemokan.book

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

	private lateinit var bookViewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

	    val bookListAdapter = BookListAdapter(this)
	    recyclerview.adapter = bookListAdapter
	    recyclerview.layoutManager = LinearLayoutManager(this)

        fab.setOnClickListener { view ->
            val intent = Intent(this, NewBookActivity::class.java)
            startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE)
        }

	    bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)

	    bookViewModel.allBooks.observe(this, Observer { books ->
			books?.let {
				bookListAdapter.setBooks(books)
			}
	    })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

	        val id = UUID.randomUUID().toString()
	        val authorName = data.getStringExtra(NewBookActivity.NEW_AUTHOR)
	        val bookName = data.getStringExtra(NewBookActivity.NEW_BOOK)

	        val book = Book(id, authorName, bookName)

	        bookViewModel.insert(book)

	        Toast.makeText(
			        applicationContext,
			        R.string.saved,
			        Toast.LENGTH_LONG).show()

        } else {
	        Toast.makeText(
			        applicationContext,
			        R.string.not_saved,
			        Toast.LENGTH_LONG).show()
        }
    }

    companion object {
    	private const val NEW_NOTE_ACTIVITY_REQUEST_CODE = 1
    }
}
