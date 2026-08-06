package com.example.taskmate.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.taskmate.data.model.Task

class TaskViewModel : ViewModel() {
    val tasks = mutableStateListOf<Task>()

    fun  addTask(task: Task) {
        tasks.add(0,task)
    }

    fun toggleTask(task: Task) {
        val index = tasks.indexOf(task)

        if (index != -1) {
            val updated = task.copy(isCompleted = !task.isCompleted)
            tasks[index] = updated
            tasks.sortWith (
                compareBy<Task> { it.isCompleted }
                .thenByDescending { it.createdAt }
            )
        }
    }
    fun deleteTask(task: Task) {
        tasks.remove(task)
    }
}