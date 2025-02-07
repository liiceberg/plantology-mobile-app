package ru.itis.liiceberg.common.exceptions

import android.util.Log

inline fun <T, R> T.runCatching(
    exceptionHandlerDelegate: ExceptionHandlerDelegate,
    block: T.() -> R,
): Result<R> {
    return try {
        Log.d("DELEGATE", "try")
        Result.success(block())
    } catch (ex: Throwable) {
        Log.d("DELEGATE", "catch")
        Result.failure(exceptionHandlerDelegate.handleException(ex))
    }
}