package ru.itis.liiceberg.common.exceptions

import android.util.Log
import com.google.firebase.FirebaseNetworkException
import ru.itis.liiceberg.common.resources.ResourceManager
import ru.itis.liiceberg.common.R
import javax.inject.Inject

class ExceptionHandlerDelegate @Inject constructor(
    private val resManager: ResourceManager,
) {
    fun handleException(ex: Throwable): Throwable {
        Log.d("DELEGATE EXCEPTION TAG", ex.toString())
        return when (ex) {
            is AppException ->
                ex
            is FirebaseNetworkException -> {
                AppException.UploadDataFailed(resManager.getString(R.string.common_error_network))
            }
            else -> {
                AppException.Unknown(resManager.getString(R.string.common_error_general_message))
            }
        }
    }
}