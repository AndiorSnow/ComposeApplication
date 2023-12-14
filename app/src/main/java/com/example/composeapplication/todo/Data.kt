//package com.example.composeapplication.todo
//
//import androidx.annotation.StringRes
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Build
//import androidx.compose.material.icons.filled.Delete
//import androidx.compose.material.icons.filled.Done
//import androidx.compose.material.icons.filled.Info
//import androidx.compose.material.icons.filled.Lock
//import androidx.compose.ui.graphics.vector.ImageVector
//import com.example.composeapplication.R
//import java.util.UUID
//
//data class TodoItem(
//    val task: String,
//    val icon: TodoIcon = TodoIcon.Default,
//    val id: UUID = UUID.randomUUID()
//)
//
//enum class TodoIcon(
//    val imageVector: ImageVector,
//    @StringRes val contentDescription: Int
//) {
//    // 使用了Material Design的图标
//    Square(Icons.Default.Build, R.string.app_name),
//    Done(Icons.Default.Done, R.string.app_name),
//    Event(Icons.Default.Info, R.string.app_name),
//    Privacy(Icons.Default.Lock, R.string.app_name),
//    Trash(Icons.Default.Delete, R.string.app_name);
//
//    companion object {
//        val Default = Square
//    }
//}