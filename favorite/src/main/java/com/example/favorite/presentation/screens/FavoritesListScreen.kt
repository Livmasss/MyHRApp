package com.example.favorite.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.coreui.components.VacancyItem
import com.example.coreui.models.VacancyModel
import com.example.coreui.theme.MyHRAppTheme
import com.example.coreui.theme.spacings
import com.example.hr_app.presentation.theme.AppColors
import org.koin.compose.koinInject
import java.util.Calendar
import java.util.UUID

@Composable
internal fun FavoritesListScreen(
    viewModel: FavoritesViewModel = koinInject()
) {
    LaunchedEffect(Unit) {
        viewModel.initiateFavoriteList(this)
    }

    FavoritesListRawScreen(
        vacancies = viewModel.favoriteList.collectAsState().value,
        onLikedChange = {},
        onRespondClicked = {}
    )
}

@Composable
private fun FavoritesListRawScreen(
    vacancies: List<VacancyModel>,
    onLikedChange: (Boolean) -> Unit,
    onRespondClicked: (VacancyModel) -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = MaterialTheme.spacings.medium)
    ) {
        HeadingTexts(vacanciesCount = vacancies.size)
        Spacer(modifier = Modifier.height(MaterialTheme.spacings.medium))

        FavoriteVacanciesList(
            vacancies = vacancies,
            onLikedChange = onLikedChange,
            onRespondClicked = onRespondClicked
        )
    }
}

@Composable
private fun HeadingTexts(vacanciesCount: Int) {
    Text(
        text = stringResource(id = com.example.coreui.R.string.label_favorite),
        style = MaterialTheme.typography.headlineSmall
    )
    Spacer(modifier = Modifier.height(MaterialTheme.spacings.large))
    Text(
        text = pluralStringResource(com.example.coreui.R.plurals.text_vacancies_count, vacanciesCount, vacanciesCount),
        style = MaterialTheme.typography.bodySmall,
        color = AppColors.Grey4
    )
}

@Composable
private fun FavoriteVacanciesList(
    vacancies: List<VacancyModel>,
    onLikedChange: (Boolean) -> Unit,
    onRespondClicked: (VacancyModel) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacings.medium)
    ) {
        items(
            count = vacancies.size,
            key = { index -> vacancies[index].id }
        ) { index ->
            val vacancy = vacancies[index]

            VacancyItem(
                model = vacancy,
                onLikedChange = onLikedChange,
                onRespondClicked = onRespondClicked
            )
        }
    }
}

@Preview
@Composable
private fun FavoritesListRawScreenPreview() {
    MyHRAppTheme {
        FavoritesListRawScreen(
            vacancies = listOf(
                VacancyModel(
                    id = UUID.randomUUID(),
                    interestedPeopleCount = null,
                    title = "Title",
                    city = "City",
                    isLiked = false,
                    company = "Company",
                    isVerified = true,
                    experienceText = "From 1 to 3",
                    publishDate = Calendar.getInstance()
                ),
                VacancyModel(
                    id = UUID.randomUUID(),
                    interestedPeopleCount = null,
                    title = "Title 2",
                    city = "City 2",
                    isLiked = true,
                    company = "Company 2",
                    isVerified = true,
                    experienceText = "From 1 to 3",
                    publishDate = Calendar.getInstance()
                ),
            ),
            onLikedChange = {},
            onRespondClicked = {}
        )
    }
}