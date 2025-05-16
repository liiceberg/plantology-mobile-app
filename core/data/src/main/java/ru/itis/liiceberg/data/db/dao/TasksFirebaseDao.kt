package ru.itis.liiceberg.data.db.dao

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import ru.itis.liiceberg.common.model.TaskType
import ru.itis.liiceberg.data.db.FirestoreCollections
import ru.itis.liiceberg.data.db.model.Task
import javax.inject.Inject

class TasksFirebaseDao @Inject constructor(
    private val firestore: FirebaseFirestore,
) {

    private val tasksReference = firestore.collection(FirestoreCollections.TASKS)

    suspend fun getTasksByFavourite(favId: String): List<Task> {
        val items = mutableListOf<Task>()
        val result = tasksReference
            .whereEqualTo(FAV_ID_FILED, favId)
            .get()
            .await()

        items.addAll(result.toObjects(Task::class.java))
        result.documents.forEachIndexed { i, doc -> items[i].id = doc.id }

        return items
    }

    suspend fun addOrUpdateTask(favId: String, type: TaskType) {
        val query = tasksReference
            .whereEqualTo(FAV_ID_FILED, favId)
            .whereEqualTo(TYPE_FILED, type)
            .get()
            .await()

        if (query.isEmpty) {
            val task = Task(
                favId = favId,
                type = type,
                lastCaringDate = Timestamp.now(),
            )
            tasksReference.add(task).await()
        }

    }

    suspend fun completeTask(id: String) {
        tasksReference.document(id).get().await().reference.update(
            LAST_CARING_DATE_FIELD,
            Timestamp.now()
        ).await()
    }

    suspend fun deleteTasksByFavourite(favId: String) {
        val result = tasksReference
            .whereEqualTo(FAV_ID_FILED, favId)
            .get()
            .await()

        for (doc in result) {
            tasksReference.document(doc.id).delete()
        }

    }

    companion object {
        private const val FAV_ID_FILED = "favId"
        private const val TYPE_FILED = "type"
        private const val LAST_CARING_DATE_FIELD = "lastCaringDate"
    }
}