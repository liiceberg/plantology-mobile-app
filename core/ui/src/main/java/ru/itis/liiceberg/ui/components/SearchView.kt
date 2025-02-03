package ru.itis.liiceberg.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.itis.liiceberg.ui.theme.AppTheme
import ru.itis.liiceberg.ui.theme.Neutral600

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val textFieldState = rememberTextFieldState()
    var expanded by rememberSaveable { mutableStateOf(false) }

    SearchBar(
        inputField = {
            SearchBarDefaults.InputField(
                query = textFieldState.text.toString(),
                expanded = expanded,
                leadingIcon = { SimpleIcon(Icons.Default.Search, 24.dp, tint = Neutral600) },
                onExpandedChange = {expanded = expanded.not()},
                onQueryChange = { query -> query.also { textFieldState.setTextAndPlaceCursorAtEnd(it) } },
                onSearch = onSearch,
            )
        },
        expanded = expanded,
        onExpandedChange = {expanded = expanded.not()},
        modifier = modifier
    ) {
    }
}

@Preview(showBackground = false)
@Composable
fun PreviewSearchView() {

    AppTheme {
        Column {
            SearchView({})
        }
    }
}