package ru.itis.liiceberg.data.db.dao

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import ru.itis.liiceberg.data.db.model.User
import javax.inject.Inject

class UserFirebaseDao @Inject constructor(
    private val auth: FirebaseAuth,
    private val dbReference: DatabaseReference,
) {
    fun signInUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                println("sign in success")
            } else {
                println(task.exception)
            }
        }
    }

    fun createUserWithEmailAndPassword(email: String, password: String, username: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {

                auth.currentUser?.run {
                    saveToDatabase(
                        id = uid,
                        username = username,
                        email = email,
                        password = password
                    )
                }

            } else {
                println(task.exception)
            }
        }
    }

    private fun saveToDatabase(id: String, username: String, email: String, password: String) {
        val userCreated = User(id = id, username = username, email = email, password = password)
        dbReference.child(id).setValue(userCreated)
    }
}