package ru.itis.liiceberg.data.db.dao

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import ru.itis.liiceberg.common.model.TimeValues
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

    fun updateFavouriteInfo(
        favId: String,
        wateringPeriod: TimeValues?,
        fertilizerPeriod: TimeValues?,
    ) {
        favReference.document(favId)
            .get()
            .addOnSuccessListener { querySnapshot ->
                querySnapshot.reference.update(
                    buildMap {
                        wateringPeriod?.let { put(WATERING_PERIOD, it) }
                        fertilizerPeriod?.let { put(FERTILIZER_PERIOD, it) }
                    }
                )
            }
    }

    suspend fun getFavorites(userId: String): List<FavouritePlant> {
        return favReference
            .whereEqualTo(USER_ID_FILED, userId)
            .get().await()
            .toObjects(FavouritePlant::class.java)
    }

    suspend fun getFavouriteInfo(userId: String, plantId: String): FavouritePlant? {

        val res = favReference
            .whereEqualTo(USER_ID_FILED, userId)
            .whereEqualTo(PLANT_ID_FILED, plantId)
            .get()
            .await()
            .firstOrNull()

        return res?.toObject(FavouritePlant::class.java).apply { this?.id = res?.id }
    }

    companion object {
        private const val USER_ID_FILED = "userId"
        private const val PLANT_ID_FILED = "plantId"
        private const val WATERING_PERIOD = "wateringPeriod"
        private const val FERTILIZER_PERIOD = "fertilizerPeriod"
    }
}