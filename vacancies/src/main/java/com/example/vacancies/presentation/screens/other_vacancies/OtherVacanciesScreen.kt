package com.example.vacancies.presentation.screens.other_vacancies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.coreui.components.MyCircleProgressIndicator
import com.example.coreui.models.VacancyModel
import com.example.coreui.theme.MyHRAppTheme
import com.example.coreui.theme.spacings
import com.example.coreui.utils.OnStopDisposedEffect
import com.example.vacancies.presentation.components.VacanciesWholeList
import com.example.vacancies.presentation.screens.main.SearchOptionsRow
import com.example.vacancies.presentation.utils.preview.vacanciesPreviewList
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun OtherVacanciesScreen(
    viewModel: OtherVacanciesViewModel = koinViewModel(),
    onBackButtonClicked: () -> Unit
) {
    val vacancies = viewModel.vacancies.collectAsState().value
    val scope = rememberCoroutineScope()
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        viewModel.initiateScreen(scope)
    }

    OtherVacanciesRawScreen(
        vacancies = vacancies ?: listOf(),
        loading = vacancies == null,
        searchQuery = "",
        onSearchQueryChange = {},
        onBackButtonClicked = onBackButtonClicked,
        changeLikeState = { index, value ->
            viewModel.setIsFavorite(index, value)
        },
        respondVacancy = {}
    )

    OnStopDisposedEffect(lifecycleOwner) {
        viewModel.saveFavoriteVacancies()
    }
}

@Composable
private fun OtherVacanciesRawScreen(
    vacancies: List<VacancyModel>,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onBackButtonClicked: () -> Unit,
    changeLikeState: (index: Int, value: Boolean) -> Unit,
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
        modifier = Modifier.padding(horizontal = MaterialTheme.spacings.medium)
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

        VacanciesWholeList(
            vacancies = vacancies,
            onLikeClicked = changeLikeState,
            onRespondClicked = respondVacancy
        )
    }
}

@Composable
private fun SortingRow(
    vacanciesCount: Int
) {
    Row {
        Text(
            text = pluralStringResource(
                com.example.coreui.R.plurals.text_vacancies_count,
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
            onSearchQueryChange = {},
            onBackButtonClicked = {},
            changeLikeState = { _, _ -> },
            respondVacancy = {},
            loading = false
        )
    }
}
