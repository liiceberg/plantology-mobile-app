package ru.itis.liiceberg.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.itis.liiceberg.ui.R
import ru.itis.liiceberg.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    searchResult: @Composable ColumnScope.() -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier,
) {

    var expanded by rememberSaveable { mutableStateOf(false) }

    SearchBar(
        inputField = {
            SearchBarDefaults.InputField(
                query = searchQuery,
                placeholder = { BodyMediumText(stringResource(R.string.search)) },
                onQueryChange = { query ->
                    onSearchQueryChange(query)
                },
                expanded = expanded,
                leadingIcon = {
                    if (expanded.not()) {
                        SimpleIcon(Icons.Default.Search, 24.dp, tint = MaterialTheme.colorScheme.onSurface)
                    } else {
                        SimpleIconButton(Icons.AutoMirrored.Default.ArrowBack, 24.dp, tint = MaterialTheme.colorScheme.onSurface) {
                            onSearchQueryChange("")
                            expanded = false
                        }
                    }
                },
                trailingIcon = {
                    if (expanded) {
                        SimpleIconButton(Icons.Default.Close, 24.dp, tint = MaterialTheme.colorScheme.onSurface) {
                            onSearchQueryChange("")
                        }
                    }
                },
                onExpandedChange = { expanded = it },
                onSearch = {
                    expanded = false
                    onSearch()
                },
            )
        },
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = if (expanded) Modifier else modifier,
    ) {
        searchResult()
    }
}

@Preview(showBackground = false)
@Composable
fun PreviewSearchView() {

    AppTheme {
        Column {
            SearchView("", {}, {}, {})
        }
    }
}