package com.example.taskmate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmate.data.model.Quote
import com.example.taskmate.data.model.Task
import com.example.taskmate.data.repository.QuoteRepository
import com.example.taskmate.data.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(
    private val taskRepository: TaskRepository,
    private val quoteRepository: QuoteRepository,
) : ViewModel() {
    val tasks: StateFlow<List<Task>> =
        taskRepository
            .getAllTasksStream()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList(),
            )

    private val _quote = MutableStateFlow<Quote?>(null)
    val quote: StateFlow<Quote?> = _quote

    fun loadQuote() {
        viewModelScope.launch {
            _quote.value = quoteRepository.getQuote()
        }
    }

    fun addTask(task: Task) {
        viewModelScope.launch {
            taskRepository.insertTask(task)
        }
    }

    fun toggleTask(task: Task) {
        viewModelScope.launch {
            val updatedTask =
                task.copy(
                    isCompleted = !task.isCompleted,
                )
            taskRepository.updateTask(updatedTask)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskRepository.deleteTask(task)
        }
    }
}
