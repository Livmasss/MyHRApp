package com.example.coreui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.coreui.R


@Composable
fun LikeIconButton(
    isLiked: Boolean,
    onLikedChange: (Boolean) -> Unit
) {
    IconButton(onClick = {
        onLikedChange(!isLiked)
    }) {
        Icon(
            painter = painterResource(
                if (isLiked)
                    R.drawable.ic_like_filled
                else
                    R.drawable.ic_like_outline
            ),
            tint = Color.Unspecified,
            contentDescription = stringResource(R.string.label_favorite)
        )
    }
}