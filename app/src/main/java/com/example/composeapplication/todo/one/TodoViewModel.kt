package com.example.composeapplication.todo.one

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.composeapplication.todo.TodoItem

class TodoViewModel : ViewModel() {

    private var _todoItems = MutableLiveData(listOf<TodoItem>())

    val todoItems: LiveData<List<TodoItem>> = _todoItems

    fun addItem(item: TodoItem) {
        //重新得到一个新的集合，再令value指向新的集合
        //如果只是向原来的集合中添加元素，value就不会更新，Activity就不会重建
        _todoItems.value = _todoItems.value!! + listOf(item)
    }

    fun removeItem(item: TodoItem) {
        //先把一个list转为一个mutablelist，再删除其中的内容
        _todoItems.value = _todoItems.value!!.toMutableList().also {
            it.remove(item)
        }
    }
}