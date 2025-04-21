package ru.itis.liiceberg.data.db.dao

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import ru.itis.liiceberg.data.db.FirestoreCollections
import ru.itis.liiceberg.data.db.model.FavouritePlant
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavouritesFirebaseDao @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    private val favReference = firestore.collection(FirestoreCollections.FAVOURITES)

    fun addToFavorites(userId: String, plantId: String) {
        val fav = FavouritePlant(
            userId = userId,
            plantId = plantId,
            wateringPeriod = null,
            fertilizerPeriod = null
        )
        favReference.add(fav)
    }

    fun removeFromFavorites(userId: String, plantId: String) {
        favReference
            .whereEqualTo(USER_ID_FILED, userId)
            .whereEqualTo(PLANT_ID_FILED, plantId)
            .get()
            .addOnSuccessListener { documents ->
                for (doc in documents) {
                    favReference.document(doc.id).delete()
                }
            }
    }

    suspend fun getFavorites(userId: String): List<FavouritePlant> {
        return favReference
            .whereEqualTo(USER_ID_FILED, userId)
            .get().await()
            .toObjects(FavouritePlant::class.java)
    }

    companion object {
        private const val USER_ID_FILED = "userId"
        private const val PLANT_ID_FILED = "plantId"
    }
}