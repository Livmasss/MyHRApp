package com.example.vacancies.presentation.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.coreui.components.MyCircleProgressIndicator
import com.example.coreui.models.VacancyModel
import com.example.coreui.theme.MyHRAppTheme
import com.example.coreui.theme.spacings
import com.example.vacancies.presentation.components.VacanciesPartialList
import com.example.vacancies.presentation.models.RecommendationModel
import com.example.vacancies.presentation.utils.preview.vacanciesPreviewList
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun MainScreen(
    viewModel: MainVacanciesViewModel = koinViewModel(),
    navigateToVacancyDetails: () -> Unit,
    navigateToOtherVacancies: () -> Unit,
) {
    val screenState by viewModel.mainVacanciesScreen.collectAsState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.initiateScreenData(scope)
    }

    MainRawScreen(
        recommendations = screenState?.recommendations,
        vacancies = screenState?.vacancies ?: listOf(),
        otherVacanciesNumber = screenState?.vacancies?.size ?: 0,
        loading = screenState == null,
        navigateToVacancyDetails = navigateToVacancyDetails,
        navigateToOtherVacancies = navigateToOtherVacancies,
        setVacancyLikedState = { _, _ -> },
        respondVacancy = {}
    )
}

@Composable
internal fun MainRawScreen(
    recommendations: List<RecommendationModel>?,
    vacancies: List<VacancyModel>,
    otherVacanciesNumber: Int,
    navigateToVacancyDetails: () -> Unit,
    navigateToOtherVacancies: () -> Unit,
    setVacancyLikedState: (index: Int, value: Boolean) -> Unit,
    respondVacancy: (index: Int) -> Unit,
    loading: Boolean,
) {
    if (loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            MyCircleProgressIndicator()
        }
        return
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = MaterialTheme.spacings.medium)
    ) {
        SearchOptionsRow(
            searchQuery = ""
        ) {}

        if (!recommendations.isNullOrEmpty()) {
            Spacer(modifier = Modifier.height(MaterialTheme.spacings.medium))
            RecommendationsRow(recommendations = recommendations)
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacings.large))
        VacanciesPartialList(
            vacancies = vacancies,
            postVacanciesItem = {
                OtherVacanciesButton(
                    otherVacanciesNumber = otherVacanciesNumber,
                    onClick = navigateToOtherVacancies
                )
            },
            onLikeClicked = setVacancyLikedState,
            onRespondClicked = respondVacancy
        )
    }
}


@Composable
private fun OtherVacanciesButton(
    otherVacanciesNumber: Int,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        shape = shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.tertiary
        ),
        onClick = onClick
    ) {
        Text(text = pluralStringResource(com.example.vacancies.R.plurals.text_other_vacancies, otherVacanciesNumber, otherVacanciesNumber))
    }
}

@Preview
@Composable
private fun MainRawScreenPreview() {
    MyHRAppTheme {
        MainRawScreen(
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
            ),
            vacancies = vacanciesPreviewList,
            otherVacanciesNumber = 143,
            navigateToVacancyDetails = {},
            navigateToOtherVacancies = {},
            setVacancyLikedState = { _, _ ->},
            respondVacancy = {},
            loading = false,
        )
    }
}
