package ru.itis.liiceberg.common.exceptions


sealed class AppException(message: String) : Throwable(message) {

    class SuchEmailAlreadyRegistered(message: String) : AppException(message)
    class InvalidCredentials(message: String) : AppException(message)

    class UploadDataFailed(message: String) : AppException(message)
    class Unknown(message: String) : AppException(message)
}