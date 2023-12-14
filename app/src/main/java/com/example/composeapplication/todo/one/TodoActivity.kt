//package com.example.composeapplication.todo.one
//
//import android.os.Bundle
//import android.os.PersistableBundle
//import android.util.Log
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.viewModels
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.livedata.observeAsState
//import com.example.composeapplication.todo.TodoIcon
//import com.example.composeapplication.todo.TodoItem
//import com.example.composeapplication.ui.theme.ComposeApplicationTheme
//import java.util.UUID
//
//
//
//class TodoActivity : ComponentActivity() {
//    private val todoViewModel by viewModels<TodoViewModel>()
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            ComposeApplicationTheme {
//                TodoActivityScreen()
////                TodoItemInput {item ->
////                    Log.d("ning", item.task)
////                }
//            }
//        }
//    }
//
//    @Composable
//    private fun TodoActivityScreen() {
////        val items = listOf(
////            TodoItem("Learn compose", TodoIcon.Event),
////            TodoItem("Take the codelab"),
////            TodoItem("Apply state", TodoIcon.Done),
////            TodoItem("Build dynamic UIs", TodoIcon.Square)
////        )
//        //TodoScreen(items)
//        val items: List<TodoItem> by todoViewModel.todoItems.observeAsState(listOf())
//
//        TodoScreen(
//            items = items,
//            onAddItem = { todoViewModel.addItem(it) },
//            onRemoveItem = {todoViewModel.removeItem(it) }
//        )
//    }
//}