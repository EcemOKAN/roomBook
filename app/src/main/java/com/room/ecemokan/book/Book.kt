package com.room.ecemokan.book

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "books")
class Book(@PrimaryKey
           val id: String,

           @ColumnInfo(name = "author")
           val author: String,

           val book: String) {
}