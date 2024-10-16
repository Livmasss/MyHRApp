package com.example.coreui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coreui.R
import com.example.coreui.models.VacancyModel
import com.example.coreui.theme.MyHRAppTheme
import com.example.coreui.theme.spacings
import com.example.coreui.utils.MyDateTimeFormatter
import com.example.hr_app.presentation.theme.AppColors
import java.util.Calendar
import java.util.UUID

@Composable
fun VacancyItem(
    modifier: Modifier = Modifier,
    model: VacancyModel,
    onLikedChange: (Boolean) -> Unit,
    onRespondClicked: (VacancyModel) -> Unit
) {
    Card(
        modifier = Modifier.then(modifier)
    ) {
        Column(
            modifier = Modifier.padding(MaterialTheme.spacings.medium)
        ) {
            Row {
                VacancyMainInfo(
                    modifier = modifier
                        .weight(1f)
                        .heightIn(min = 150.dp),
                    model = model
                )
                LikeIconButton(
                    isLiked = model.isFavorite,
                    onLikedChange = onLikedChange
                )
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onRespondClicked(model) }
            ) {
                Text(text = stringResource(id = R.string.label_respond))
            }
        }
    }
}

@Composable
private fun VacancyMainInfo(
    modifier: Modifier = Modifier,
    model: VacancyModel
) {
    Column(modifier) {
        model.interestedPeopleCount?.let {
            if (model.interestedPeopleCount == 0)
                return@let

            Text(
                pluralStringResource(
                    R.plurals.interested_people,
                    model.interestedPeopleCount,
                    model.interestedPeopleCount
                ),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(MaterialTheme.spacings.small))
        }

        Text(text = model.title)
        Spacer(Modifier.height(MaterialTheme.spacings.small))

        Text(
            text = model.city,
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(Modifier.height(MaterialTheme.spacings.extraSmall))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = model.company,
                style = MaterialTheme.typography.bodySmall
            )
            if (model.isVerified)
                Icon(
                    modifier = Modifier.padding(start = MaterialTheme.spacings.small),
                    painter = painterResource(R.drawable.ic_verified_company),
                    contentDescription = model.experienceText,
                    tint = Color.Unspecified
                )
        }
        Spacer(Modifier.height(MaterialTheme.spacings.small))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_experience),
                contentDescription = model.experienceText,
                tint = Color.Unspecified
            )
            Text(
                modifier = Modifier.padding(start = MaterialTheme.spacings.small),
                text = model.experienceText,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacings.small))

        val dateString = MyDateTimeFormatter.formatPublishDate(model.publishDate.time)
        Text(
            text = stringResource(R.string.text_publish_date, dateString),
            style = MaterialTheme.typography.bodySmall,
            color = AppColors.Grey3
        )
    }
}

@Preview
@Composable
private fun VacancyItemPreview() {
    MyHRAppTheme {
        VacancyItem(
            model = VacancyModel(
                id = UUID.randomUUID(),
                interestedPeopleCount = 12,
                title = "Vacancy",
                city = "City",
                isFavorite = true,
                isVerified = true,
                company = "Company",
                experienceText = "Experiency from 1 to 3 years",
                publishDate = Calendar.getInstance()
            ),
            onLikedChange = {},
            onRespondClicked = {}
        )
    }
}

@Preview
@Composable
private fun VacancyItemPreviewNoInterested() {
    MyHRAppTheme {
        VacancyItem(
            model = VacancyModel(
                id = UUID.randomUUID(),
                interestedPeopleCount = null,
                title = "Vacancy",
                city = "City",
                isFavorite = true,
                isVerified = true,
                company = "Company",
                experienceText = "Experiency from 1 to 3 years",
                publishDate = Calendar.getInstance()
            ),
            onLikedChange = {},
            onRespondClicked = {}
        )
    }
}