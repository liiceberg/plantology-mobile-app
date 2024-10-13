package ru.itis.liiceberg.common.validation

import android.util.Patterns
import ru.itis.liiceberg.common.R
import ru.itis.liiceberg.common.resources.ResourceManager
import javax.inject.Inject

class Validator @Inject constructor(
    private val resourceManager: ResourceManager
) {
    fun validateName(name: String): ValidationResult {
        if (name.isBlank()) {
            return ValidationResult(false, resourceManager.getString(R.string.empty_username_error))
        }
        if (name.trim().matches(Regex(ALPHABETIC_CHARACTERS_WITH_WHITESPACE_PATTERN)).not()) {
            return ValidationResult(
                false,
                resourceManager.getString(R.string.incorrect_username_error)
            )
        }
        return ValidationResult(true)
    }

    fun validateEmail(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(false, resourceManager.getString(R.string.empty_email_error))
        }
        if (Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches().not()) {
            return ValidationResult(false, resourceManager.getString(R.string.email_error))
        }
        return ValidationResult(true)
    }

    fun validatePassword(password: String): ValidationResult {
        with(password.trim()) {
            if (this.matches(Regex(EIGHT_SYMBOLS_AND_MORE_PATTERN)).not()) {
                return ValidationResult(
                    false,
                    resourceManager.getString(R.string.password_length_error)
                )
            }
            if (this.matches(Regex(CONTAIN_UPPER_CASE_CHARACTER_PATTERN)).not()) {
                return ValidationResult(
                    false,
                    resourceManager.getString(R.string.password_upper_case_char_error)
                )
            }
            if (this.matches(Regex(CONTAIN_LOWER_CASE_CHARACTER_PATTERN)).not()) {
                return ValidationResult(
                    false,
                    resourceManager.getString(R.string.password_lower_case_char_error)
                )
            }
            if (this.matches(Regex(CONTAIN_DIGIT_PATTERN)).not()) {
                return ValidationResult(
                    false,
                    resourceManager.getString(R.string.password_digit_char_error)
                )
            }
        }
        return ValidationResult(true)
    }

    fun validatePasswordNotBlank(password: String): ValidationResult {
        if (password.isBlank()) {
            return ValidationResult(false, resourceManager.getString(R.string.empty_password_error))
        }
        return ValidationResult(true)
    }

    fun validateConfirmPassword(password: String, confirmPassword: String): ValidationResult {
        if (password.trim() != confirmPassword.trim()) {
            return ValidationResult(
                false,
                resourceManager.getString(R.string.passwords_not_equals_error)
            )
        }
        return ValidationResult(true)
    }

    companion object {
        private const val ALPHABETIC_CHARACTERS_WITH_WHITESPACE_PATTERN = "[A-Za-z ]+"
        private const val EIGHT_SYMBOLS_AND_MORE_PATTERN = "\\w{8,}"
        private const val CONTAIN_UPPER_CASE_CHARACTER_PATTERN = ".*[A-Z].*"
        private const val CONTAIN_LOWER_CASE_CHARACTER_PATTERN = ".*[a-z].*"
        private const val CONTAIN_DIGIT_PATTERN = ".*\\d.*"

    }
}
