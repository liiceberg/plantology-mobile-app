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
    firestore: FirebaseFirestore
) {

    private val favReference = firestore.collection(FirestoreCollections.FAVOURITES)

    suspend fun addToFavorites(userId: String, plantId: String) {
        val fav = FavouritePlant(
            userId = userId,
            plantId = plantId,
            wateringPeriod = null,
            fertilizerPeriod = null
        )
        favReference.add(fav).await()
    }

    suspend fun removeFromFavorites(favId: String) {
        favReference.document(favId).delete().await()
    }

    suspend fun updateFavouriteInfo(
        favId: String,
        wateringPeriod: TimeValues?,
        fertilizerPeriod: TimeValues?,
    ) {
        favReference.document(favId)
            .get()
            .await()
            .reference.update(
                buildMap {
                    put(WATERING_PERIOD, wateringPeriod)
                    put(FERTILIZER_PERIOD, fertilizerPeriod)
                }
            ).await()
    }

    suspend fun getFavorites(userId: String): List<FavouritePlant> {
        val items = mutableListOf<FavouritePlant>()
        val result = favReference
            .whereEqualTo(USER_ID_FILED, userId)
            .get()
            .await()

        items.addAll(result.toObjects(FavouritePlant::class.java))
        result.documents.forEachIndexed { i, doc -> items[i].id = doc.id }

        return items
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