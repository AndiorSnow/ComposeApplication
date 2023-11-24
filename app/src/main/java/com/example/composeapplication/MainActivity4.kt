package com.example.composeapplication

import android.app.Application
import android.content.res.Resources.Theme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeapplication.ui.theme.ComposeApplicationTheme

class MainActivity4 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //跟随系统主题自适应改变主题颜色风格
            ComposeApplicationTheme {
                MessageCard(Message("Android", "Jetpack Compose"))
            }
        }
    }

    @Composable
    fun MessageCard(msg : Message) {
        Row(
            modifier = Modifier
                .padding(all = 8.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {
            Image (
                painter = painterResource(id = R.drawable.icon_jetpack),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)

            )
            Column {
                Text(text = msg.author)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = msg.body)
            }
        }
    }

}

