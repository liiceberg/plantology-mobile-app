package ru.itis.liiceberg.data.db.dao

import android.util.Log
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import ru.itis.liiceberg.data.db.FirestoreCollections
import ru.itis.liiceberg.data.db.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserFirebaseDao @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
) {

    private var currentUser: User? = null

    fun signInUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
    }

    fun createUserWithEmailAndPassword(email: String, password: String, username: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                getCurrentUserId()?.let { id ->
                    saveUser(userId = id, email = email, password = password, username = username)
                }
            } else {
                Log.d("exc", task.exception.toString())
            }
        }
    }

    fun signOut() {
        currentUser = null
        auth.signOut()
    }

    fun getCurrentUserId(): String? {
        return auth.currentUser?.uid
    }

    suspend fun getCurrentUser(): User? {
        if (currentUser != null) {
            return currentUser
        }
        val id = getCurrentUserId() ?: return null
        currentUser = firestore.collection(FirestoreCollections.USERS)
            .document(id)
            .get()
            .await()
            .toObject(User::class.java)

        return currentUser
    }

    suspend fun verifyCurrentPassword(password: String): Boolean {
        val user = getCurrentUser() ?: throw Exception()
        return user.password == password
    }

    suspend fun updatePassword(newPassword: String) {
        val user = getCurrentUser()
        val credential = EmailAuthProvider.getCredential(user!!.email, user.password)
        auth.currentUser?.run {
            reauthenticate(credential)
            updatePassword(newPassword)
            currentUser = currentUser?.copy(password = newPassword)
            firestore.collection(FirestoreCollections.USERS)
                .document(uid)
                .update(PASSWORD_FIELD, newPassword)
        }
    }

    private fun saveUser(userId: String, email: String, password: String, username: String) {
        val userCreated = User(username = username, email = email, password = password)
        firestore.collection(FirestoreCollections.USERS).document(userId).set(userCreated)
        currentUser = userCreated
    }

    companion object {
        private const val PASSWORD_FIELD = "password"
    }
}