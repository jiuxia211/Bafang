package com.example.myapplication.ui.compose.person

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.example.bafang.R


object PersonTab : Tab {
    @Composable
    override fun Content() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 用户头像和用户名
            Image(
                painter = painterResource(id = R.drawable.default_avatar),
                contentDescription = "User Avatar",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "用户XXX", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(4.dp))
            TextButton(onClick = { /* 登录注册逻辑 */ }) {
                Text(text = "登录/注册 >")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 功能模块按钮
            PersonalFeatureCard(
                title = "我发布的求助",
                icon = rememberVectorPainter(Icons.AutoMirrored.Filled.Help),
                onClick = { /* 跳转到发布求助界面 */ }
            )

            Spacer(modifier = Modifier.height(8.dp))

            ContributionSection()

            Spacer(modifier = Modifier.height(8.dp))

            RankingAndAchievementSection()
        }
    }

    override val options: TabOptions
        @Composable
        get() {
            val icon: Painter = rememberVectorPainter(Icons.Filled.Person)
            // 这里设置选项，例如标题和图标
            return remember {
                TabOptions(
                    index = 0u,
                    title = "我的",
                    icon = icon
                )
            }
        }

    @Composable
    fun PersonalFeatureCard(title: String, icon: Painter, onClick: () -> Unit) {
        OutlinedButton(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(painter = icon, contentDescription = title)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = title, modifier = Modifier.weight(1f))
                Icon(
                    painter = rememberVectorPainter(Icons.AutoMirrored.Filled.KeyboardArrowRight),
                    contentDescription = null
                )
            }
        }
    }

    @Composable
    fun ContributionSection() {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "贡献力量", fontSize = 16.sp, color = Color.Black)
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    PersonalFeatureIcon("我的实况", Icons.Filled.Check)
                    PersonalFeatureIcon("我的救援", Icons.Filled.Favorite)
                    PersonalFeatureIcon("我的物资", Icons.Filled.ShoppingCart)
                }
            }
        }
    }

    @Composable
    fun PersonalFeatureIcon(title: String, icon: ImageVector) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = Color(0xFF3F51B5),
                modifier = Modifier.size(40.dp)
            )
            Text(text = title, fontSize = 12.sp, color = Color.Black)
        }
    }

    @Composable
    fun RankingAndAchievementSection() {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    PersonalFeatureIcon("排行榜", Icons.Filled.EmojiEvents)
                    PersonalFeatureIcon("我的成就", Icons.Filled.Inventory)
                }
            }
        }
    }
}