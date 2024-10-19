package com.example.vacancies.presentation.screens.otherVacancies

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.coreui.theme.spacings
import com.example.vacancies.R

@Composable
internal fun VacanciesSortTypeComposable(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier.then(modifier)
    ) {
        Text(
            text = stringResource(id = R.string.sort_type),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.tertiary
        )
        Spacer(modifier = Modifier.width(MaterialTheme.spacings.extraSmall))
        Icon(
            painter = painterResource(com.example.coreui.R.drawable.ic_sort_type),
            contentDescription = stringResource(id = R.string.sort_type),
            tint = MaterialTheme.colorScheme.tertiary
        )
    }
}