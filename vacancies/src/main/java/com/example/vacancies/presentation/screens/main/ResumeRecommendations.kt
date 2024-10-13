package com.example.vacancies.presentation.screens.main

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coreui.theme.MyHRAppTheme
import com.example.coreui.theme.spacings
import com.example.vacancies.R
import com.example.vacancies.presentation.models.RecommendationModel

@Composable
internal fun RecommendationsRow(
    recommendations: List<RecommendationModel>
) {
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Max)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacings.small)
    ) {
        recommendations.forEach {
            RecommendationItem(
                model = it
            ) {

            }
        }
    }
}

@Composable
internal fun RecommendationItem(
    modifier: Modifier = Modifier,
    model: RecommendationModel,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(130.dp)
            .then(modifier),
        onClick = onClick
    ) {
        Column(
            Modifier.padding(MaterialTheme.spacings.medium)
        ) {
            model.iconId?.let {
                Icon(
                    painter = painterResource(model.iconId),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacings.medium))
            }

            Text(
                modifier = Modifier.weight(1f),
                text = model.title,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = if (model.buttonText == null) 3 else 2,
                overflow = TextOverflow.Clip
            )

            model.buttonText?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview
@Composable
private fun RecommendationItemPreview() {
    MyHRAppTheme {
        RecommendationsRow(
            recommendations = listOf(
                RecommendationModel(
                    id = "",
                    title = "Поднять резюме в поиске",
                    buttonText = "поднять",
                    iconId = R.drawable.ic_level_up_resume,
                    link = "",
                ),
                RecommendationModel(
                    id = "",
                    title = "Название",
                    iconId = R.drawable.ic_level_up_resume,
                    link = "",
                ),
                RecommendationModel(
                    id = "",
                    title = "Рекомендация с очень длинным названием",
                    iconId = null,
                    link = "",
                ),
            )
        )
    }
}
