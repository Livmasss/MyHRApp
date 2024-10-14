package com.example.vacancies.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.coreui.components.VacancyItem
import com.example.coreui.models.VacancyModel
import com.example.coreui.theme.MyHRAppTheme
import com.example.coreui.theme.spacings
import com.example.vacancies.R
import com.example.vacancies.presentation.utils.preview.vacanciesPreviewList

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
                    onLikeClicked(index, !vacancy.isLiked)
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
    MyHRAppTheme {
        VacanciesPartialList(
            vacancies = vacanciesPreviewList,
            onLikeClicked = { _, _ -> },
            onRespondClicked = {}
        )
    }
}