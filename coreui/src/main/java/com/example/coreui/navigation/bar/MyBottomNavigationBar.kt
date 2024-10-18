package com.example.coreui.navigation.bar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.coreui.theme.MyHRAppTheme
import com.example.hr_app.presentation.theme.AppColors

@Composable
fun MyBottomNavigationBar(
    navController: NavController,
    favoritesCount: Int?
) {
    Column {
        HorizontalDivider()
        Row(
            modifier = Modifier
                .windowInsetsPadding(NavigationBarDefaults.windowInsets)
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            BottomNavItem.getObjectInstances().forEach { item ->
                val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true

                if (item == BottomNavItem.Favorite && favoritesCount != null)
                    BottomNavigationItemWithCount(
                        navController = navController,
                        item = item,
                        selected = selected,
                        count = favoritesCount
                    )
                else
                    BottomNavigationItem(
                        navController = navController,
                        item = item,
                        selected = selected
                    )
            }
        }
    }
}

@Composable
private fun RowScope.BottomNavigationItemWithCount(
    modifier: Modifier = Modifier,
    navController: NavController,
    item: BottomNavItem,
    selected: Boolean,
    count: Int,
) {
    BottomNavigationItem(
        modifier = modifier,
        navController = navController,
        item = item,
        selected = selected,
        bubble = {
            if (count > 0)
                CountBubble(count)
        }
    )
}

@Composable
private fun RowScope.BottomNavigationItem(
    modifier: Modifier = Modifier,
    navController: NavController,
    item: BottomNavItem,
    selected: Boolean,
    bubble: @Composable () -> Unit = {}
) {
    BottomNavigationItem(
        modifier = Modifier
            .then(modifier)
            .weight(1f),
        onClick = {
            navController.navigate(item.route) {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        },
        content = {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(item.iconResourceId),
                    contentDescription = stringResource(item.label),
                    tint = if (selected)
                        MaterialTheme.colorScheme.tertiary
                    else
                        AppColors.Grey4
                )
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(bottom = 15.dp, start = 25.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    bubble()
                }
            }
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

@Composable
private fun BottomNavigationItem(
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

@Composable
private fun CountBubble(count: Int) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(15.dp)
            .background(color = AppColors.Red, shape = CircleShape)
    ) {
        Text(
            fontSize = 8.sp,
            lineHeight = 8.sp,
            text = count.toString()
        )
    }
}

@Preview
@Composable
private fun MyBottomNavigationBarPreview() {
    val navController = rememberNavController()
    val favoritesCount by remember {
        mutableIntStateOf(12)
    }

    MyHRAppTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),

            bottomBar = {
                MyBottomNavigationBar(
                    navController = navController,
                    favoritesCount = favoritesCount
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                NavHost(
                    navController = navController,
                    startDestination = BottomNavItem.Search
                ) {
                    composable<BottomNavItem.Search> {  }
                    composable<BottomNavItem.Favorite> {  }
                    composable<BottomNavItem.Responds> {  }
                    composable<BottomNavItem.Messages> {  }
                    composable<BottomNavItem.Responds> {  }
                }
            }
        }
    }
}
