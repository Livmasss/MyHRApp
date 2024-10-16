package com.example.vacancies.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.coreui.components.VacancyItem
import com.example.coreui.models.VacancyModel
import com.example.coreui.theme.MyHRAppTheme
import com.example.coreui.theme.spacings
import com.example.vacancies.R
import com.example.vacancies.presentation.utils.preview.vacanciesPreviewList
import java.util.Calendar
import java.util.UUID

@Composable
internal fun VacanciesPartialList(
    vacancies: List<VacancyModel>,
    postVacanciesItem: (@Composable () -> Unit)? = null,
    onLikeClicked: (index: Int, value: Boolean) -> Unit,
    onRespondClicked: (index: Int) -> Unit,
) {
    val vacanciesWithCheckedSize = try {
        vacancies.slice(0 until 3)
    }
    catch (e: Exception) {
        vacancies
    }

    Column {
        Text(
            text = stringResource(R.string.text_vacancies_for_you),
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacings.medium))

        VacanciesList(
            vacancies = vacanciesWithCheckedSize,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacings.medium),
            postVacanciesItem = postVacanciesItem,
            onLikeClicked = onLikeClicked,
            onRespondClicked = onRespondClicked
        )
    }
}

@Composable
internal fun VacanciesWholeList(
    vacancies: List<VacancyModel>,
    onLikeClicked: (index: Int, value: Boolean) -> Unit,
    onRespondClicked: (index: Int) -> Unit,
) {
    VacanciesList(
        vacancies = vacancies,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacings.small),
        onLikeClicked = onLikeClicked,
        onRespondClicked = onRespondClicked
    )
}

@Composable
private fun VacanciesList(
    vacancies: List<VacancyModel>,
    verticalArrangement: Arrangement.Vertical,
    postVacanciesItem: (@Composable () -> Unit)? = null,
    onLikeClicked: (index: Int, value: Boolean) -> Unit,
    onRespondClicked: (index: Int) -> Unit,
) {
    LazyColumn(
        verticalArrangement = verticalArrangement
    ) {
        items(
            count = vacancies.size,
            key = {
                vacancies[it].id
            }
        ) { index ->
            val vacancy = vacancies[index]

            VacancyItem(
                model = vacancy,
                onLikedChange = {
                    onLikeClicked(index, it)
                },
                onRespondClicked = {
                    onRespondClicked(index)
                }
            )
        }

        postVacanciesItem?.let {
            item {
                Spacer(modifier = Modifier.height(MaterialTheme.spacings.medium))
                postVacanciesItem()
            }
        }
    }
}

@Composable
@Preview
private fun VacanciesListPreview() {
    val vacancies = remember {
        mutableStateListOf(
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
    }

    MyHRAppTheme {
        VacanciesPartialList(
            vacancies = vacancies,
            onLikeClicked = { index, value ->
                vacancies[index] = vacancies[index].copy(isFavorite = value)
                Log.d("test", vacancies[0].isFavorite.toString())
            },
            onRespondClicked = {}
        )
    }
}