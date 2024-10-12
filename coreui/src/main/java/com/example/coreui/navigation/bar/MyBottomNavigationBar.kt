package com.example.coreui.navigation.bar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.coreui.theme.MyHRAppTheme
import com.example.hr_app.presentation.theme.AppColors

@Composable
fun MyBottomNavigationBar(navController: NavController) {
    Column {
        HorizontalDivider()
        Row(
            modifier = Modifier
                .windowInsetsPadding(NavigationBarDefaults.windowInsets)
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            BottomNavItem.getObjectInstances().forEach { item ->
                val selected = currentRoute == item.route

                BottomNavigationItem(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    },
                    content = {
                        Icon(
                            painter = painterResource(item.iconResourceId),
                            contentDescription = stringResource(item.label),
                            tint = if (selected)
                                MaterialTheme.colorScheme.tertiary
                            else
                                AppColors.Grey4
                        )
                    },
                    label = {
                        Text(
                            stringResource(item.label),
                            style = MaterialTheme.typography.bodySmall,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            color = if (selected)
                                MaterialTheme.colorScheme.tertiary
                            else
                                AppColors.Grey4
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun BottomNavigationItem(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    label: @Composable () -> Unit,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .then(modifier)
            .clickable { onClick() }
            .padding(vertical = 15.dp)
    ) {
        content()
        label()
    }
}

@Preview
@Composable
private fun MyBottomNavigationBarPreview() {
    MyHRAppTheme {
        MyBottomNavigationBar(
            rememberNavController()
        )
    }
}
