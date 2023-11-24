package com.example.composeapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LayoutStudy() {
    @OptIn(ExperimentalMaterial3Api::class)
    Scaffold (    //为各种Material组件提供槽位
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "LayoutStudy")
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        BodyContent(Modifier.padding(innerPadding))
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(8.dp)) {
        Text (text = "Hi there!")
        Text (text = "thanks for going through this LayoutStudy!")
    }
}