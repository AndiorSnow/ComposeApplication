//package com.example.composeapplication
//
//import android.app.Application
//import android.content.res.Resources.Theme
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.animation.animateColorAsState
//import androidx.compose.animation.animateContentSize
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.example.composeapplication.ui.theme.ComposeApplicationTheme
//
//class MainActivity5 : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            ComposeApplicationTheme {
//                Conversation(SampleData.conversationSample)
//            }
//        }
//    }
//
//    @Composable
//    fun MessageCard(msg : Message) {
//        Row(
//            modifier = Modifier
//                .padding(all = 8.dp)
//                .background(MaterialTheme.colorScheme.background)
//        ) {
//            Image (
//                painter = painterResource(id = R.drawable.icon_jetpack),
//                contentDescription = null, //无障碍
//                modifier = Modifier
//                    .size(40.dp)
//                    .clip(CircleShape)
//            )
//            Spacer(modifier = Modifier.width(8.dp))
//            var isExpanded by remember { mutableStateOf(false) }
//            val surfaceColor : Color by animateColorAsState (
//                if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
//            )
//            Column (
//                //点击后变化
//                modifier = Modifier.clickable { isExpanded = !isExpanded }
//            ){
//                Text(
//                    text = msg.author,
//                    color = MaterialTheme.colorScheme.secondaryContainer
//                )
//                Spacer(modifier = Modifier.height(4.dp))
//                // 为一个mutableState对象isExpanded保存了一个初始状态值false
//                // by：委托，用于监控状态值的变化
//                Surface(
//                    shape = MaterialTheme.shapes.medium,   //圆角矩形
//                    shadowElevation = 1.dp,    //底部阴影
//                    color = surfaceColor,
//                    modifier = Modifier        //动画效果
//                        .animateContentSize()
//                        .padding(1.dp)
//                ) {
//                    Text(
//                        text = msg.body,
//                        modifier = Modifier.padding(all = 4.dp),
//                        style = MaterialTheme.typography.bodyMedium,
//                        //isExpanded为true，则显示全部行，否则只显示一行
//                        maxLines = if (isExpanded) Int.MAX_VALUE else 1
//                    )
//                }
//            }
//        }
//    }
//
//    @Composable
//    fun Conversation(messages: List<Message>) {
//        LazyColumn {
//            items(messages) {message ->
//                MessageCard(message)
//            }
//        }
//    }
//}
//
