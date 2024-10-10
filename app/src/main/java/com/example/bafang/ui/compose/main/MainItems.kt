package com.example.myapplication.ui.compose.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

enum class MainItems(
    val tag: String,
    val unSelectImageVector: ImageVector,
    val selectImageVector: ImageVector,
) {
    Map(
        "地图",
        unSelectImageVector = Icons.Outlined.LocationOn,
        selectImageVector = Icons.Filled.LocationOn,
    ),

    Person(
        "个人中心",
        unSelectImageVector = Icons.Outlined.Person,
        selectImageVector = Icons.Filled.Person,
    )
}