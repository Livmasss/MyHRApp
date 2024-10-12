package com.example.coreui.composables

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.coreui.theme.MyHRAppTheme
import com.example.hr_app.presentation.theme.AppColors

@Composable
fun MyTextField(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    label: @Composable (() -> Unit)? = null,
    value: String,
    onValueChanged: (String) -> Unit,
) {
    TextField(
        modifier = Modifier.then(modifier),
        shape = shapes.medium,
        value = value,
        onValueChange = onValueChanged,
        enabled = enabled,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = AppColors.Grey2,
            unfocusedContainerColor = AppColors.Grey2,
            disabledContainerColor = AppColors.Grey2,
            errorContainerColor = AppColors.Grey2,

            focusedTextColor = AppColors.Grey4,
            unfocusedTextColor = AppColors.Grey4,
            disabledTextColor = AppColors.Grey4,
            errorTextColor = AppColors.Grey4,

            focusedLabelColor = AppColors.Grey4,
            unfocusedLabelColor = AppColors.Grey4,
            disabledLabelColor = AppColors.Grey4,
            errorLabelColor = AppColors.Grey4,

            focusedLeadingIconColor = AppColors.Grey4,
            unfocusedLeadingIconColor = AppColors.Grey4,
            disabledLeadingIconColor = AppColors.Grey4,
            errorLeadingIconColor = AppColors.Grey4,

            focusedTrailingIconColor = AppColors.Grey4,
            unfocusedTrailingIconColor = AppColors.Grey4,
            disabledTrailingIconColor = AppColors.Grey4,
            errorTrailingIconColor = AppColors.Grey4,

            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent
        ),
        label = label
    )
}

@Preview
@Composable
private fun MyTextFieldPreview() {
    MyHRAppTheme {
        MyTextField(
            value = "",
            label = {
                Text(text = "123")
            }
        ) {}
    }
}