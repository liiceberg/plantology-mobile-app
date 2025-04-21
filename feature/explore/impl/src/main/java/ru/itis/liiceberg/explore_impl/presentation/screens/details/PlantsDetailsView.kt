package ru.itis.liiceberg.explore_impl.presentation.screens.details

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.itis.liiceberg.common.util.showShortToast
import ru.itis.liiceberg.explore_api.domain.model.PlantModel
import ru.itis.liiceberg.explore_impl.R
import ru.itis.liiceberg.ui.components.BodyMediumText
import ru.itis.liiceberg.ui.components.BodySmallText
import ru.itis.liiceberg.ui.components.DarkIcon
import ru.itis.liiceberg.ui.components.ErrorView
import ru.itis.liiceberg.ui.components.HeadlineLargeText
import ru.itis.liiceberg.ui.components.HeadlineSmallText
import ru.itis.liiceberg.ui.components.KeyValueText
import ru.itis.liiceberg.ui.components.LightIcon
import ru.itis.liiceberg.ui.components.LoadingIndicator
import ru.itis.liiceberg.ui.components.RoundedImage
import ru.itis.liiceberg.ui.components.SimpleButtonWithStartIcon
import ru.itis.liiceberg.ui.components.SimpleFloatingActionButton
import ru.itis.liiceberg.ui.components.SimpleImage
import ru.itis.liiceberg.ui.components.TitleMediumText
import ru.itis.liiceberg.ui.components.TitleSmallText
import ru.itis.liiceberg.ui.model.LoadState
import ru.itis.liiceberg.ui.theme.PlantologyTheme
import ru.itis.liiceberg.ui.R as R_UI

@Composable
fun PlantsDetailsView(
    viewModel: PlantsDetailsViewModel = hiltViewModel(),
    plantId: String,
    onBackClick: () -> Unit
) {

    val state by viewModel.viewStates().collectAsStateWithLifecycle()

    PlantsDetailsView(state, onBackClick, viewModel::addFavourite)

    val ctx = LocalContext.current
    LaunchedEffect(Unit) {

        viewModel.loadInfo(plantId)

        viewModel.viewActions().collect { action ->
            when (action) {
                is PlantsDetailsAction.ShowSuccessAddToFavoriteMessage -> {
                    ctx.showShortToast(R.string.success_add_favourite)
                }
                else -> {}
            }
        }
    }
}

@Composable
fun PlantsDetailsView(
    state: PlantsDetailsState,
    onBackClick: () -> Unit,
    addToFavorite: (String) -> Unit,
) {
    state.plantModel?.run {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column {

                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.TopStart) {
                    SimpleImage(url = image.firstOrNull() ?: "")
                    SimpleFloatingActionButton(
                        icon = Icons.AutoMirrored.Filled.ArrowBack,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        onBackClick.invoke()
                    }
                }

                Column(
                    Modifier
                        .offset(y = (-20).dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.secondary)
                        .verticalScroll(rememberScrollState())
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        HeadlineLargeText(text = name)
                        Column {
                            KeyValueText(
                                key = stringResource(R.string.scientific_name),
                                value = scientificName
                            )
                            KeyValueText(key = stringResource(R.string.family), value = family)
                            KeyValueText(key = stringResource(R.string.rank), value = rank)
                            KeyValueText(
                                key = stringResource(R.string.higher_classification),
                                value = higherClassification
                            )
                            KeyValueText(key = stringResource(R.string.kingdom), value = kingdom)
                            KeyValueText(key = stringResource(R.string.order), value = order)
                        }
                        BodyMediumText(text = description)
                    }

                    LazyRow(
                        Modifier.padding(start = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(image.subList(1, image.lastIndex + 1)) { url ->
                            RoundedImage(url = url, Modifier.height(128.dp))
                        }

                    }

                    HeadlineSmallText(
                        text = stringResource(R.string.conditions),
                        modifier = Modifier.padding(start = 16.dp, top = 28.dp, bottom = 16.dp),
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(start = 16.dp)
                            .padding(vertical = 24.dp)
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        ConditionCard(
                            R_UI.drawable.thermometer, stringResource(R.string.temperature),
                            stringResource(
                                R.string.temperature_range,
                                minTemperature ?: "",
                                maxTemperature ?: ""
                            )
                        )
                        ConditionCard(
                            R_UI.drawable.humidity,
                            stringResource(R.string.humidity),
                            humidity
                        )
                        ConditionCard(
                            R_UI.drawable.globe,
                            stringResource(R.string.hardiness_zone),
                            hardinessZones
                        )
                    }

                    HeadlineLargeText(
                        text = stringResource(R.string.care_guide),
                        modifier = Modifier.padding(start = 16.dp, top = 28.dp, bottom = 16.dp),
                    )
                    Column(
                        Modifier.padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        val toxicity = when (toxicity) {
                            true -> stringResource(R.string.toxicity_toxic)
                            false -> stringResource(R.string.toxicity_non_toxic)
                            else -> ""
                        }
                        CareGuideItem(
                            R_UI.drawable.skull,
                            stringResource(R.string.toxicity),
                            toxicity
                        )
                        CareGuideItem(
                            R_UI.drawable.water_drops,
                            stringResource(R.string.water),
                            water
                        )
                        CareGuideItem(
                            R_UI.drawable.sunny,
                            stringResource(R.string.sunlight),
                            sunlight
                        )
                        CareGuideItem(
                            R_UI.drawable.themperature,
                            stringResource(R.string.temperature),
                            temperature
                        )
                    }
                    if (saved.not()) {
                        SimpleButtonWithStartIcon(
                            stringResource(R.string.add_to_my_plants),
                            painterResource(id = R_UI.drawable.save),
                            iconSize = 24.dp,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.padding(vertical = 24.dp, horizontal = 16.dp)
                        ) {
                            addToFavorite(id)
                        }
                    }
                }
            }

            when(state.loadState){
                is LoadState.Error -> ErrorView(errorText = state.loadState.message)
                LoadState.Loading -> LoadingIndicator()
                else -> {}
            }
        }
    }

}

@Composable
private fun ConditionCard(@DrawableRes drawable: Int, title: String, values: String) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(14.dp))
            .background(MaterialTheme.colorScheme.secondary)
            .padding(10.dp)
            .width(116.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        LightIcon(painter = painterResource(id = drawable), Modifier.padding(bottom = 2.dp))
        BodySmallText(text = title)
        TitleSmallText(text = values)
    }
}

@Composable
private fun CareGuideItem(@DrawableRes drawable: Int, title: String, description: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(14.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        DarkIcon(painter = painterResource(id = drawable), modifier = Modifier.size(50.dp))
        Column {
            TitleMediumText(text = title)
            BodySmallText(text = description)
        }
    }
}


@Preview(showBackground = false)
@Composable
private fun PlantsDetailsPreview() {
    PlantologyTheme {
        Column {
            val plant = PlantModel(
                name = "Wild mint",
                scientificName = "Mentha arvensis",
                family = "Lamiaceae",
                rank = "Species",
                higherClassification = "Mint",
                kingdom = "Plantae",
                order = "Lamiales",
                description = "Mentha arvensis, the corn mint, field mint, or wild mint, is a species of flowering plant in the mint family Lamiaceae. It has a circumboreal distribution, being native to the temperate regions of Europe and western and central Asia, east to the Himalaya and eastern Siberia, and North America.",
                temperature = "Plants outdoors in late spring and early\n" +
                        "summer. Soil temperature must be 60 F\n" +
                        "(15 C) or warmer. ",
                humidity = "",
                hardinessZones = "",
                toxicity = false,
                water = "It usually doesn't need to be watered \n" +
                        "regularly, but it should be watered \n" +
                        "every two weeks in the summer and\n" +
                        "every three weeks in the winter",
                sunlight = "It needs partial sun to stay healthy,\n" +
                        "but too much much direct sunlight\n" +
                        "can burn its leaves. Too little sunlight\n" +
                        "can cause the leaves to turn yellow.",
                image = listOf(
                    "https://www.plantarium.ru/dat/plants/1/179/530179_b53cc1a1.jpg",
                    "https://www.plantarium.ru/dat/plants/6/618/581618_33f9b82e.jpg",
                    "https://avatars.mds.yandex.net/get-mpic/5220030/img_id2270192735490663727.jpeg/orig",
                    "https://igardens.ru/wp-content/uploads/2020/10/Мята-перечьная-1.jpg"
                ),
                id = "",
                maxTemperature = 30,
                minTemperature = 10,
                saved = false
            )
            PlantsDetailsView(PlantsDetailsState(plant), {}) {}
        }
    }
}