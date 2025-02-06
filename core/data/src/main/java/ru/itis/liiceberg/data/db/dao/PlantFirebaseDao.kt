package ru.itis.liiceberg.data.db.dao

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import ru.itis.liiceberg.data.db.FirestoreCollections
import ru.itis.liiceberg.data.db.model.FloraCategory
import ru.itis.liiceberg.data.db.model.Plant
import javax.inject.Inject

class PlantFirebaseDao @Inject constructor(
    private val firestore: FirebaseFirestore,
) {

    suspend fun getPlantsByCategory(category: FloraCategory): List<Plant> {
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
/*
        fun savePlant() {
            val plant = Plant(
                name = "Spearmint",
                scientificName = "Mentha spicata",
                family = "Lamiaceae",
                rank = "Species",
                higherClassification = "Mint",
                kingdom = "Plantae",
                order = "Lamiales",
                description = "Mentha arvensis, the corn mint, field mint, or wild mint, is a species of flowering plant in the mint family Lamiaceae. It has a circumboreal distribution, being native to the temperate regions of Europe and western and central Asia, east to the Himalaya and eastern Siberia, and North America.",
                temperature = Temperature(
                    "Plants outdoors in late spring and early\n" +
                            "summer. Soil temperature must be 60 F\n" +
                            "(15 C) or warmer. ", 25, 10
                ),
                humidity = "50%",
                hardinessZones = "3a-7b",
                toxicity = false,
                water = "It usually doesn't need to be watered \n" +
                        "regularly, but it should be watered \n" +
                        "every two weeks in the summer and\n" +
                        "every three weeks in the winter",
                sunlight = "It needs partial sun to stay healthy,\n" +
                        "but too much much direct sunlight\n" +
                        "can burn its leaves. Too little sunlight\n" +
                        "can cause the leaves to turn yellow.",
                category = FloraCategory.PLANT,
                fertilization = "",
                image = listOf(
                    "https://www.google.com/url?sa=i&url=https%3A%2F%2Faidigo.ru%2Fencyclopedia%2Fmint%2F&psig=AOvVaw2cNXIR8IReBHFVqyZBeVWb&ust=1738588535513000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCJidw5KJpYsDFQAAAAAdAAAAABAE",
                    "https://dvasadovoda.ru/upload/iblock/ffd/ffd1dc51156b6ba184da8f0ac49a30ba.jpg",
                    "https://avatars.mds.yandex.net/get-mpic/5220030/img_id2270192735490663727.jpeg/orig",
                    "https://igardens.ru/wp-content/uploads/2020/10/Мята-перечьная-1.jpg"
                )
            )
            firestore.collection("plants")
                .add(plant)
                .addOnSuccessListener { documentReference ->
                    Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("TAG", "Error adding document", e)
                }
        }
*/


    companion object {
        const val FLORA_CATEGORY_FIELD = "flora_category"
    }

}

