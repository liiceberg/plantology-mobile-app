package ru.itis.liiceberg.data.db.dao

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import ru.itis.liiceberg.data.db.FirestoreCollections
import ru.itis.liiceberg.data.db.model.Plant
import javax.inject.Inject

class PlantFirebaseDao @Inject constructor(
    private val firestore: FirebaseFirestore,
) {

    suspend fun getAllPlants(): List<Plant> {
        val items = mutableListOf<Plant>()
        val result = firestore.collection(FirestoreCollections.PLANTS)
            .get()
            .await()
        items.addAll(result.toObjects(Plant::class.java))

        result.documents.forEachIndexed { i, doc -> items[i].id = doc.id }

        return items
    }

    suspend fun getPlantById(id: String) : Plant? {
        val result = firestore.collection(FirestoreCollections.PLANTS)
            .document(id)
            .get()
            .await()
        val plant = result.toObject(Plant::class.java)
        plant?.id = id
        return plant
    }

}

