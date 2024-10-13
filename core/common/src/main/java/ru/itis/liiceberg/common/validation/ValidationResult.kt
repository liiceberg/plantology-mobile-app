package ru.itis.liiceberg.common.validation

data class ValidationResult(
    val isValid: Boolean,
    val error: String? = null
) {
    companion object {
        fun empty() = ValidationResult(true, null)
    }
}