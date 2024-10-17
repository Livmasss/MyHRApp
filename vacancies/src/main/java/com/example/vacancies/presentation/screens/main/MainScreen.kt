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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.coreui.components.MyCircleProgressIndicator
import com.example.coreui.models.VacancyModel
import com.example.coreui.theme.MyHRAppTheme
import com.example.coreui.theme.spacings
import com.example.coreui.utils.OnStopDisposedEffect
import com.example.vacancies.presentation.components.VacanciesPartialList
import com.example.vacancies.presentation.models.MainVacanciesScreenModel
import com.example.vacancies.presentation.models.RecommendationModel
import org.koin.androidx.compose.koinViewModel
import java.util.Calendar
import java.util.UUID

@Composable
internal fun MainScreen(
    viewModel: MainVacanciesViewModel = koinViewModel(),
    navigateToVacancyDetails: () -> Unit,
    navigateToOtherVacancies: () -> Unit,
) {
    val screenState by viewModel.mainVacanciesScreen.collectAsState()
    val scope = rememberCoroutineScope()
    val lifecycleOwner = LocalLifecycleOwner.current

    if (screenState == null)
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
        setVacancyLikedState = { index, value ->
            viewModel.setIsFavorite(index, value)
        },
        respondVacancy = {}
    )

    OnStopDisposedEffect(lifecycleOwner) {
        viewModel.saveFavoriteVacancies()
    }
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
    var screen by remember {
        mutableStateOf(
            MainVacanciesScreenModel(
                recommendations = listOf(
                    RecommendationModel(
                        id = "",
                        iconId = com.example.vacancies.R.drawable.ic_level_up_resume,
                        title = "title 1",
                        buttonText = "text",
                        link = ""
                    )
                ),
                vacancies = listOf(
                    VacancyModel(
                        id = UUID.randomUUID(),
                        interestedPeopleCount = 1,
                        title = "Title",
                        city = "City",
                        isFavorite = false,
                        company = "Company",
                        isVerified = false,
                        publishDate = Calendar.getInstance(),
                        experienceText = "Experience from 1 to 3 years"
                    )
                )
            )
        )
    }

    MyHRAppTheme {
        MainRawScreen(
            recommendations = screen.recommendations,
            vacancies = screen.vacancies,
            otherVacanciesNumber = 143,
            navigateToVacancyDetails = {},
            navigateToOtherVacancies = {},
            setVacancyLikedState = { index, value ->
                val newVacancies = List(screen.vacancies.size) {
                    screen.vacancies[it].copy(
                        isFavorite = if (it == index) value else screen.vacancies[it].isFavorite
                    )
                }
                screen = screen.copy(
                    vacancies = newVacancies
                )
                                   },
            respondVacancy = {},
            loading = false,
        )
    }
}
