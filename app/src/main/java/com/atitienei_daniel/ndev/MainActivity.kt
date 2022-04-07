package com.atitienei_daniel.ndev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import com.atitienei_daniel.ndev.ui.Navigation
import com.atitienei_daniel.ndev.ui.theme.NDEVTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@ExperimentalMaterial3Api
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NDEVTheme {
                val systemUiController = rememberSystemUiController()
                val useDarkIcons = !isSystemInDarkTheme()

                val systemBarColor = MaterialTheme.colorScheme.secondaryContainer
                SideEffect {

                    systemUiController.setSystemBarsColor(
                        color = systemBarColor,
                        darkIcons = useDarkIcons
                    )
                }

                Navigation()
            }
        }
    }
}