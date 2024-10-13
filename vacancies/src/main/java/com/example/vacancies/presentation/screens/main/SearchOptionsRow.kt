package com.example.vacancies.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.coreui.R
import com.example.coreui.components.MyTextField
import com.example.coreui.theme.spacings


@Composable
internal fun SearchOptionsRow(
    leadingIcon: @Composable () -> Unit = {
        Icon(
            painter = painterResource(R.drawable.ic_search_outline),
            contentDescription = stringResource(R.string.label_search),
        )
    },
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
) {
    Row(
        modifier = Modifier.height(IntrinsicSize.Min)
    ) {
        MyTextField(
            modifier = Modifier.weight(1f),
            value = searchQuery,
            onValueChanged = onSearchQueryChanged,
            enabled = false,
            leadingIcon = leadingIcon,
            label = {
                Text(stringResource(R.string.text_search_bar))
            }
        )

        Spacer(Modifier.width(MaterialTheme.spacings.small))

        IconButton(
            modifier = Modifier.fillMaxHeight()
                .aspectRatio(1f)
                .background(MaterialTheme.colorScheme.surface, shapes.medium),
            onClick = {}
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_filter_outline),
                contentDescription = stringResource(id = R.string.label_filter),
                tint = Color.Unspecified
            )
        }
    }
}