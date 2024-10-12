package com.example.coreui.navigation.bar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.hr_app.presentation.theme.AppColors
import com.example.coreui.theme.MyHRAppTheme

@Composable
fun MyBottomNavigationBar(navController: NavController) {
    Row {
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
