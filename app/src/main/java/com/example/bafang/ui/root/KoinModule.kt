package com.example.myapplication.ui.root

import cafe.adriel.voyager.navigator.Navigator
import com.example.myapplication.ui.compose.map.MapViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

class SystemAction(val onBack: () -> Unit, val onFinish: () -> Unit)

fun koinModule(systemAction: SystemAction, navigator: Navigator) = module {
    // finish和back操作
    single { systemAction }
    // 导航操作
    single { navigator }
    // 注入viewModel
    viewModelInjection()
}

// 扩展函数，对viewModel的注入
fun Module.viewModelInjection() {
    viewModel { MapViewModel() }
}