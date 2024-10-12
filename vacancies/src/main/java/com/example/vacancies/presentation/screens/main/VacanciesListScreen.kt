package com.example.vacancies.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.coreui.R
import com.example.coreui.composables.MyTextField
import com.example.coreui.theme.MyHRAppTheme
import com.example.coreui.theme.spacings
import com.example.vacancies.presentation.components.RecommendationsRow
import com.example.vacancies.presentation.models.RecommendationModel

@Composable
internal fun VacanciesListScreen(
    navigateToVacancyDetails: () -> Unit
) {
    VacanciesListRawScreen(
        recommendations = listOf(
            RecommendationModel(
                id = "",
                iconId = com.example.vacancies.R.drawable.ic_level_up_resume,
                title = "title",
                buttonText = "text",
                link = ""
            )
        ),
        navigateToVacancyDetails = navigateToVacancyDetails
    )
}

@Composable
internal fun VacanciesListRawScreen(
    recommendations: List<RecommendationModel>,
    navigateToVacancyDetails: () -> Unit
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacings.medium)
                .padding(innerPadding)
        ) {
            SearchOptionsRow(
                searchQuery = ""
            ) {}
            Spacer(Modifier.height(MaterialTheme.spacings.large))

            RecommendationsRow(recommendations = recommendations)
        }
    }
}

@Composable
private fun SearchOptionsRow(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min)
    ) {
        MyTextField(
            modifier = Modifier
                .weight(1f),
            value = searchQuery,
            onValueChanged = onSearchQueryChanged,
            enabled = false,
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = stringResource(R.string.label_search),
                )
            },
            label = {
                Text(stringResource(R.string.text_search_bar))
            }
        )

        Spacer(Modifier.width(MaterialTheme.spacings.small))

        IconButton(
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                .background(MaterialTheme.colorScheme.surface, shapes.medium),
            onClick = {}
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_filter),
                contentDescription = stringResource(id = R.string.label_filter),
                tint = Color.Unspecified
            )
        }
    }
}

@Preview
@Composable
private fun VacanciesListRawScreenPreview() {
    MyHRAppTheme {
        VacanciesListRawScreen(
            recommendations = listOf(
                RecommendationModel(
                    id = "",
                    iconId = com.example.vacancies.R.drawable.ic_level_up_resume,
                    title = "title 1",
                    buttonText = "text",
                    link = ""
                ),
                RecommendationModel(
                    id = "",
                    iconId = com.example.vacancies.R.drawable.ic_level_up_resume,
                    title = "title 2",
                    link = ""
                ),
                RecommendationModel(
                    id = "",
                    iconId = com.example.vacancies.R.drawable.ic_level_up_resume,
                    title = "Really big title number 3",
                    buttonText = "text",
                    link = ""
                )
            )
        ) {}
    }
}
