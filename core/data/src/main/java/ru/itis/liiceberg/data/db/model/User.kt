package ru.itis.liiceberg.data.db.model

data class User(
    val id: String,
    val username: String,
    val email: String,
    val password: String,
)