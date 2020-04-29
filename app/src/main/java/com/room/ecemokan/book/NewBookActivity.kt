package com.room.ecemokan.book

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_new.*

class NewBookActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_new)

		bAdd.setOnClickListener{

			val resultIntent = Intent()

			if (TextUtils.isEmpty(etAuthorName.text) ||
					TextUtils.isEmpty(etBookName.text)) {
				setResult(Activity.RESULT_CANCELED, resultIntent)
			} else {
				val author = etAuthorName.text.toString()
				val book = etBookName.text.toString()

				resultIntent.putExtra(NEW_AUTHOR, author)
				resultIntent.putExtra(NEW_BOOK, book)
				setResult(Activity.RESULT_OK, resultIntent)
			}
			finish()
		}
	}

	companion object {
		const val NEW_AUTHOR = "new_author"
		const val NEW_BOOK = "new_book"
	}
}
