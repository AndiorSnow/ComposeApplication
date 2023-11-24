package com.example.composeapplication.todo

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.composeapplication.R
import java.util.*

data class TodoItem(
    val task: String,
    val icon: TodoIcon = TodoIcon.Default,
    val id: UUID = UUID.randomUUID()
)

enum class TodoIcon(
    val imageVector: ImageVector,
    @StringRes val contentDescription: Int
) {
    // 使用了Material Design的图标
    Square(Icons.Default.Build, R.string.cd_expand),
    Done(Icons.Default.Done, R.string.cd_done),
    Event(Icons.Default.Info, R.string.cd_event),
    Privacy(Icons.Default.Lock, R.string.cd_privacy),
    Trash(Icons.Default.Delete, R.string.cd_restore);

    companion object {
        val Default = Square
    }
}