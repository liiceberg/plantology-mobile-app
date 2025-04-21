package ru.itis.liiceberg.data.db.dao

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import ru.itis.liiceberg.data.db.FirestoreCollections
import ru.itis.liiceberg.data.db.model.Task
import ru.itis.liiceberg.common.model.TaskType
import javax.inject.Inject

class TasksFirebaseDao @Inject constructor(
    private val firestore: FirebaseFirestore,
) {

    private val tasksReference = firestore.collection(FirestoreCollections.TASKS)

    suspend fun getTasksByFavourite(favId: String): List<Task> {
        return tasksReference
            .whereEqualTo(FAV_ID_FILED, favId)
            .get().await()
            .toObjects(Task::class.java)
    }

    fun addTask(favId: String, type: TaskType) {
        val task = Task(
            favId = favId,
            type = type,
            lastCaringDate = Timestamp.now(),
        )
        tasksReference.add(task)
    }

    companion object {
        private const val FAV_ID_FILED = "favId"
    }
}