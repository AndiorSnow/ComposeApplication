//package com.example.composeapplication.todo.one
//
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material.ButtonDefaults
//import androidx.compose.material.Text
//import androidx.compose.material.TextButton
//import androidx.compose.material.TextField
//import androidx.compose.material.TextFieldDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged
//import com.example.composeapplication.todo.TodoItem
//
////输入框
//@Composable
//fun TodoInputText(
//    text: String,
//    onTextChange: (String) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    TextField(
//        value = text,
//        onValueChange = onTextChange,
//        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
//        maxLines = 1,
//        modifier = modifier
//    )
//}
//
////按钮
//@Composable
//fun TodoEditButton(
//    onClick: () ->Unit,
//    text: String,
//    modifier: Modifier = Modifier,
//    enabled: Boolean = true
//) {
//    TextButton(
//        onClick = onClick,
//        shape = CircleShape,
//        colors = ButtonDefaults.buttonColors(),
//        modifier = modifier,
//        enabled = enabled
//    ) {
//        Text(text = text)
//    }
//}
//
//@Composable
//fun TodoItemInput(onItemComplete: (TodoItem) -> Unit) {
//    //remember给自己添加内存，在内存中存储一个由mutableStateOf创建的MutableState<String>
//    //它是Compose的内置类型，提供了一个可观察的状态持有者
//    //对value的任何更改都会自动重新组合读取此状态的任何可组合函数
//    val (text, setText) = remember { mutableStateOf("") }
//
//    Column {
//
//        Row (
//            Modifier
//                .padding(horizontal = 16.dp)
//                .padding(top = 16.dp)
//        ){
//            //输入框
//            TodoInputText(
//                text = text,
//                onTextChange = setText,
//                modifier = Modifier
//                    .weight(1f)
//                    .padding(end = 8.dp)
//            )
//            //按钮
//            TodoEditButton(
//                onClick = {
//                    onItemComplete(TodoItem(text))
//                    setText("")
//                },
//                text = "Add",
//                modifier = Modifier.align(Alignment.CenterVertically),
//                enabled = text.isNotBlank()
//            )
//        }
//    }
//}
