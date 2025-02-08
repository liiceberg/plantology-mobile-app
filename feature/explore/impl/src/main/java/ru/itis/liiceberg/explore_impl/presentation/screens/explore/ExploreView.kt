package ru.itis.liiceberg.explore_impl.presentation.screens.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.itis.liiceberg.explore_api.domain.model.ExplorePlantModel
import ru.itis.liiceberg.explore_impl.R
import ru.itis.liiceberg.ui.components.BodySmallText
import ru.itis.liiceberg.ui.components.CardWithImageAndInfo
import ru.itis.liiceberg.ui.components.ErrorMessage
import ru.itis.liiceberg.ui.components.LightTopAppBar
import ru.itis.liiceberg.ui.components.LoadingView
import ru.itis.liiceberg.ui.components.RoundedImage
import ru.itis.liiceberg.ui.components.SearchView
import ru.itis.liiceberg.ui.components.SimpleIconButton
import ru.itis.liiceberg.ui.components.TitleMediumText
import ru.itis.liiceberg.ui.model.LoadState
import ru.itis.liiceberg.ui.theme.AppTheme
import ru.itis.liiceberg.ui.R as R_UI

@Composable
fun ExploreView(
    viewModel: ExploreViewModel = hiltViewModel(),
    navigateToDetails: (plantId: String) -> Unit
) {
    val state by viewModel.viewStates().collectAsStateWithLifecycle()

    ExploreView(
        state,
        navigateToDetails,
    )

    LaunchedEffect(Unit) {
        viewModel.init()
    }
}

@Composable
fun ExploreView(
    state: ExploreState,
    navigateToDetails: (plantId: String) -> Unit,
) {
    Box(Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                LightTopAppBar(
                    title = stringResource(R.string.explore_top_bar_text),
                    action = {
                        SimpleIconButton(
                            icon = painterResource(id = R_UI.drawable.notifications),
                            size = 24.dp,
                            tint = MaterialTheme.colorScheme.onSecondary,
                            onClick = {}
                        )
                    }
                )
            },
        ) { innerPadding ->
            Column(
                Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                SearchView(onSearch = {}, Modifier.padding(bottom = 36.dp))
                AllPlantsList(state.items, navigateToDetails)
            }
        }

        when(state.loadState){
            is LoadState.Error -> ErrorMessage(errorText = state.loadState.message)
            LoadState.Loading -> LoadingView()
            else -> {}
        }
    }
}

@Composable
private fun AllPlantsList(
    items: List<ExplorePlantModel>,
    navigateToDetails: (plantId: String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(items) { plant ->
            ExplorePlantCard(plant, navigateToDetails)
        }
    }
}


@Composable
private fun ExplorePlantCard(plant: ExplorePlantModel, onClick: (plantId: String) -> Unit) {
    with(plant) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clickable { onClick.invoke(id) }
        ) {
            CardWithImageAndInfo(imageUrl = plant.imageUrl, title = name, text = scientificName)
        }
    }
}


@Composable
private fun SearchResultPlantCard(plant: ExplorePlantModel, onClick: (plantId: String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(14.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            RoundedImage(plant.imageUrl, Modifier.size(50.dp))
            Column(Modifier.padding(start = 12.dp)) {
                TitleMediumText(text = plant.name)
                BodySmallText(text = plant.scientificName)
            }
        }

    }
}

@Preview(showBackground = false)
@Composable
private fun ExplorePreview() {
    AppTheme {
        Column(Modifier.fillMaxSize()) {
            val item = ExplorePlantModel(
                "",
                "mint",
                "some name",
                ""
            )
            ExploreView(ExploreState(listOf(item, item, item, item)), {})
        }
    }
}