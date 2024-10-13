package com.example.vacancies.presentation.screens.other_vacancies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.coreui.models.VacancyModel
import com.example.coreui.theme.MyHRAppTheme
import com.example.coreui.theme.spacings
import com.example.vacancies.R
import com.example.vacancies.presentation.components.VacanciesWholeList
import com.example.vacancies.presentation.screens.main.SearchOptionsRow
import com.example.vacancies.presentation.utils.preview.vacanciesPreviewList

@Composable
fun OtherVacanciesScreen(
    onBackButtonClicked: () -> Unit
) {
    OtherVacanciesRawScreen(
        vacancies = listOf(),
        searchQuery = "",
        onSearchQueryChange = {},
        onBackButtonClicked = onBackButtonClicked
    )
}

@Composable
private fun OtherVacanciesRawScreen(
    vacancies: List<VacancyModel>,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onBackButtonClicked: () -> Unit
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacings.medium)
                .padding(innerPadding)
        ) {
            SearchOptionsRow(
                searchQuery = searchQuery,
                onSearchQueryChanged = onSearchQueryChange,
                leadingIcon = {
                    Icon(
                        modifier = Modifier.clickable {
                            onBackButtonClicked()
                        },
                        painter = painterResource(com.example.coreui.R.drawable.ic_back),
                        contentDescription = stringResource(com.example.coreui.R.string.label_back),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacings.medium))
            SortingRow(vacancies.size)
            Spacer(modifier = Modifier.height(MaterialTheme.spacings.large))

            VacanciesWholeList(vacancies = vacancies)
        }
    }
}

@Composable
private fun SortingRow(
    vacanciesCount: Int
) {
    Row {
        Text(
            text = pluralStringResource(
                R.plurals.text_vacancies_count,
                vacanciesCount,
                vacanciesCount
            ),
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.weight(1f))
        VacanciesSortTypeComposable()
    }
}

@Composable
@Preview
private fun OtherVacanciesRawScreenPreview() {
    MyHRAppTheme {
        OtherVacanciesRawScreen(
            vacancies = vacanciesPreviewList,
            searchQuery = "",
            onSearchQueryChange = {}
        ) {}
    }
}
