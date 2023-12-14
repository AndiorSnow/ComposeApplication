//package com.example.composeapplication
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.tooling.preview.Preview
//import com.example.composeapplication.ui.theme.ComposeApplicationTheme
//
//class MainActivity3 : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            MessageCard(Message("Android", "Jetpack Compose"))
//        }
//    }
//
//    @Composable
//    fun MessageCard(msg : Message) {
//        Row {
//            Image (
//                painter = painterResource(id = R.drawable.icon_jetpack),
//                contentDescription = null
//            )
//            Column {
//                Text(text = msg.author)
//                Text(text = msg.body)
//            }
//        }
//    }
//
//}
//
