//package com.example.composeapplication
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.LocalContentColor
//import androidx.compose.material3.LocalTextStyle
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.material3.darkColorScheme
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.CompositionLocalProvider
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import java.time.format.TextStyle
//
//@Composable
//fun PhotographerCard(modifier: Modifier = Modifier) {
//    Row(
//        modifier = modifier
//            .clip(RoundedCornerShape(4.dp))      //圆角矩形
//            .background(color = MaterialTheme.colorScheme.surface)   //surface和onSurface成对出现，让背景和内容的颜色具有对比度
//            //modifier有先后顺序，以下两个参数调换后效果不同
//            .clickable(onClick = { })
//            .padding(16.dp)    //点击水波纹效果
//    ) {
//        Surface(
//            modifier = Modifier.size(50.dp),
//            shape = CircleShape,
//            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.icon_jetpack),
//                contentDescription = null
//            )
//        }
//        Column (
//            modifier = Modifier
//                .padding(start = 8.dp)
//                .align(Alignment.CenterVertically)
//        ) {
//            Text (text = "Alfred Sisley", fontWeight = FontWeight.Bold)
//            //隐式传参，只作用于内部，设置透明度
//            CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurfaceVariant) {
//                Text (text = "3 minutes ago", style = MaterialTheme.typography.bodyMedium)
//            }
//        }
//    }
//
//}