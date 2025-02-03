package ru.itis.liiceberg.data.db.model

import com.google.firebase.firestore.PropertyName

data class Plant(
    var id: String? = null,
    val name: String? = null,
    val scientificName: String? = null,
    val category: FloraCategory? = null,
    val image: List<String>? = null,
    val description: String? = null,
    val family: String? = null,
    val water: String? = null,
    val fertilization: String? = null,
    val hardinessZones: String? = null,
    val higherClassification: String? = null,
    val humidity: String? = null,
    val kingdom: String? = null,
    val order: String? = null,
    val rank: String? = null,
    val sunlight: String? = null,
    val temperature: Temperature? = null,
    val toxicity: Boolean? = null
)

enum class FloraCategory {

    @PropertyName("plant")
    PLANT,
    @PropertyName("tree")
    TREE;

    fun stringValue() : String = this.name.lowercase()

    companion object {
        fun fromString(name: String): FloraCategory = valueOf(name.uppercase())
    }
}

data class Temperature(
    val info: String? = null,
    val max: Int? = null,
    val min: Int? = null,
)


