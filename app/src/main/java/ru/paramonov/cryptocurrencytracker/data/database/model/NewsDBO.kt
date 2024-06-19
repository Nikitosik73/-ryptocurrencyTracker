package ru.paramonov.cryptocurrencytracker.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsDBO(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val guid: String,
    val imageUrl: String,
    val title: String,
    val body: String,
    val nameNews: String
)
