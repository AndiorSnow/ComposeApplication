//package com.example.composeapplication
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.rememberLazyListState
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.Button
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import kotlinx.coroutines.launch
//
//@Composable
//fun SimpleColumn() {
//    val scrollState = rememberScrollState()
//    Column(
//        Modifier.verticalScroll(scrollState)
//     ) {
//        repeat(100) {
//            Text(text = "Item #$it", style = MaterialTheme.typography.titleMedium)
//        }
//    }
//}
//
//@Composable
//fun LazyList() {
//    val scrollState = rememberScrollState()
//    LazyColumn(userScrollEnabled = true) {
//        items(100) {
//            Text(text = "Item #$it", style = MaterialTheme.typography.titleMedium)
//        }
//    }
//}
//
//@Composable
//fun ScrollingList() {
//    val listSize = 100
//    val scrollState = rememberLazyListState()
//    val coroutineScope = rememberCoroutineScope() //协程作用域
//    Column {
//        Row {
//            Button(
//                modifier = Modifier.weight(1f),
//                onClick = {
//                    coroutineScope.launch {
//                        scrollState.animateScrollToItem(0)  //挂机函数，需要在其他函数或协程中被调用
//                    }
//                }
//            ) {
//                Text(text = "Scroll to the top")
//            }
//
//            Button(
//                modifier = Modifier.weight(1f),
//                onClick = {
//                    coroutineScope.launch {
//                        scrollState.animateScrollToItem(listSize - 1)
//                    }
//                }
//             ) {
//                Text(text = "Scroll to the end")
//            }
//        }
//
//        LazyColumn(state = scrollState) {
//            items(listSize) {
//                ImageListItem(index = it)
//            }
//        }
//    }
//}
//
//@Composable
//fun ImageListItem(index: Int) {
//    Row(verticalAlignment = Alignment.CenterVertically) {
//        Image(
//            painter = painterResource(id = R.drawable.icon_jetpack),
//            contentDescription = null,
//            modifier = Modifier.size(50.dp)
//        )
//        Spacer(modifier = Modifier.size(10.dp))
//        Text(text = "Item #$index", style = MaterialTheme.typography.titleMedium)
//    }
//}