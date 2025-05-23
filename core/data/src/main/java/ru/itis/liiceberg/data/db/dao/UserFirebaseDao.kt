package ru.itis.liiceberg.data.db.dao

import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import ru.itis.liiceberg.common.exceptions.AppException
import ru.itis.liiceberg.data.db.FirestoreCollections
import ru.itis.liiceberg.data.db.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserFirebaseDao @Inject constructor(
    firestore: FirebaseFirestore,
    private val auth: FirebaseAuth,
) {

    private val usersReference = firestore.collection(FirestoreCollections.USERS)
    private var currentUser: User? = null

    suspend fun signInUser(email: String, password: String) {
        runCatching {
            auth.signInWithEmailAndPassword(email, password).await()
        }.getOrElse { ex ->
            throw when (ex) {
                is FirebaseAuthInvalidCredentialsException -> {
                    AppException.InvalidCredentials(ex.message ?: "")
                }

                else -> ex
            }
        }
    }

    suspend fun createUserWithEmailAndPassword(email: String, password: String, username: String) {
        runCatching {
            auth.createUserWithEmailAndPassword(email, password).await()
        }.getOrElse { ex ->
            when (ex) {
                is FirebaseAuthUserCollisionException -> {
                    throw AppException.SuchEmailAlreadyRegistered(ex.message ?: "")
                }
                else -> throw ex
            }
        }
        getCurrentUserId()?.let { id ->
            saveUser(
                userId = id,
                email = email,
                password = password,
                username = username
            )
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
        currentUser = usersReference
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
            reauthenticate(credential).await()
            updatePassword(newPassword).await()
            currentUser = currentUser?.copy(password = newPassword)
            usersReference
                .document(uid)
                .update(PASSWORD_FIELD, newPassword)
                .await()
        }
    }

    private fun saveUser(userId: String, email: String, password: String, username: String) {
        val userCreated = User(username = username, email = email, password = password)
        usersReference.document(userId).set(userCreated)
        currentUser = userCreated
    }

    companion object {
        private const val PASSWORD_FIELD = "password"
    }
}