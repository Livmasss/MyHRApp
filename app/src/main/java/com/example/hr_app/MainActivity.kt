package com.example.hr_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.coreui.navigation.bar.MyBottomNavigationBar
import com.example.coreui.theme.MyHRAppTheme
import com.example.hr_app.presentation.navigation.MainAppRouter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            enableEdgeToEdge()
            val navController = rememberNavController()

            var favoritesCount by rememberSaveable {
                mutableStateOf<Int?>(null)
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
                        MainAppRouter(
                            navController = navController,
                            onFavoriteCountChange = {
                                favoritesCount = it
                            }
                        )
                    }
                }
            }
        }
    }
}
