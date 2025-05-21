package ru.itis.liiceberg.data.db.dao

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import ru.itis.liiceberg.data.db.FirestoreCollections
import ru.itis.liiceberg.data.db.model.Plant
import javax.inject.Inject

class PlantFirebaseDao @Inject constructor(
    firestore: FirebaseFirestore,
) {

    private val plantsReference = firestore.collection(FirestoreCollections.PLANTS)

    suspend fun getAllPlants(): List<Plant> {
        val items = mutableListOf<Plant>()
        val result = plantsReference.get().await()
        items.addAll(result.toObjects(Plant::class.java))

        result.documents.forEachIndexed { i, doc -> items[i].id = doc.id }

        return items
    }

    suspend fun getPlantById(id: String) : Plant? {
        val result = plantsReference
            .document(id)
            .get()
            .await()
        val plant = result.toObject(Plant::class.java)
        plant?.id = id
        return plant
    }

}

