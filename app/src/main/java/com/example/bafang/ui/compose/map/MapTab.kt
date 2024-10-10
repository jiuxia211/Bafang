package com.example.myapplication.ui.compose.map

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.amap.api.maps.MapsInitializer
import com.melody.map.gd_compose.GDMap
import com.melody.map.gd_compose.poperties.MapProperties
import com.melody.map.gd_compose.poperties.MapUiSettings
import org.koin.compose.viewmodel.koinViewModel

object MapTab : Tab {
    val permissionArray = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    @Composable
    override fun Content() {
        val context = LocalContext.current
        val mapViewModel = koinViewModel<MapViewModel>()
        // 获取定位权限
        var isGetPermission by remember { mutableStateOf(false) }
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            // 检查所有请求的权限是否都被授予
            if (permissions.values.all { it }) {
                // 权限被授予，可以执行相关操作
                isGetPermission = true
            } else {
                // 权限被拒绝，处理相应逻辑
            }
        }
        LaunchedEffect(Unit) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                launcher.launch(permissionArray)
            } else {
                isGetPermission = true
            }
        }
        if (isGetPermission) {
            MapView(mapViewModel)
        }

    }

    override val options: TabOptions
        @Composable
        get() {
            val icon: Painter = rememberVectorPainter(Icons.Filled.LocationOn)
            // 这里设置选项，例如标题和图标
            return remember {
                TabOptions(
                    index = 0u,
                    title = "地图",
                    icon = icon
                )
            }
        }

    @Composable
    fun MapView(mapViewModel: MapViewModel) {
        // 获取当前的 Context
        val context = LocalContext.current

        // 在初始化地图之前进行隐私合规检查
        MapsInitializer.updatePrivacyShow(context, true, true) // 展示隐私权政策弹窗，并包含高德隐私政策
        MapsInitializer.updatePrivacyAgree(context, true)      // 用户已同意隐私权政策
        GDMap(
            modifier = Modifier.fillMaxSize(),
            properties = MapProperties(
                isMyLocationEnabled = true
            ),
            uiSettings = MapUiSettings(
                isScrollGesturesEnabled = true, // 拖拽手势
                isZoomGesturesEnabled = true, // 缩放手势
                myLocationButtonEnabled = true // 右上角定位按钮
            ),
            locationSource = mapViewModel// locationSource中
        )


    }
}