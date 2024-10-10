package com.example.myapplication.ui.root

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.example.myapplication.ui.compose.main.MainScreen
import org.koin.compose.KoinApplication

@Composable
fun RootUi(systemAction: SystemAction) {
    Navigator(screen = MainScreen()) { navigator -> // 函数的最后一个参数是 lambda 时，可以将这个 lambda 写在函数调用的圆括号外部
        KoinApplication(application = { modules(koinModule(systemAction, navigator)) }) {
            CurrentScreen()
        }
    }
}