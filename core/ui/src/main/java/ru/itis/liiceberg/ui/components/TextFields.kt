package ru.itis.liiceberg.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.itis.liiceberg.ui.theme.AppTheme

@Composable
fun SimpleTextField(
    value: String,
    modifier: Modifier = Modifier,
    label: String,
    supportingText: String? = null,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        label = { Text(label) },
        supportingText = { supportingText?.let { Text(it) } },
        isError = supportingText != null,
        shape = RoundedCornerShape(8.dp),
        singleLine = true
    )
}

@Composable
fun PasswordTextField(
    value: String,
    modifier: Modifier = Modifier,
    label: String,
    supportingText: String? = null,
    onValueChange: (String) -> Unit
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        label = { Text(label) },
        supportingText = { supportingText?.let { Text(it) } },
        isError = supportingText != null,
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        visualTransformation = if (isPasswordVisible.not()) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            ShowHidePasswordIcon(
                isVisible = isPasswordVisible,
                toggleIsVisible = {
                    isPasswordVisible = isPasswordVisible.not()
                },
            )
        }
    )
}

@Composable
private fun ShowHidePasswordIcon(
    isVisible: Boolean,
    toggleIsVisible: () -> Unit,
) = IconButton(
    onClick = toggleIsVisible
) {
    Icon(if (isVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility, null)
}


@Preview(showBackground = true)
@Composable
private fun PreviewTextInput() {
    AppTheme {
        Column {
            SimpleTextField(value = "", label = "Email", onValueChange = {})
            SimpleTextField(value = "hello", label = "Password", onValueChange = {})
        }
    }
}