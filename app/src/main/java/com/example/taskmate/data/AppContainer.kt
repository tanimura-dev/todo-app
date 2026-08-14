package com.example.taskmate.data


import android.content.Context
import com.example.taskmate.data.local.TaskDatabase
import com.example.taskmate.data.repository.OfflineTaskRepository
import com.example.taskmate.data.repository.QuoteRepository
import com.example.taskmate.data.repository.TaskRepository



interface AppContainer {
    val taskRepository: TaskRepository
    val quoteRepository: QuoteRepository
}

class DefaultAppContainer(
    private val context: Context
) : AppContainer {

    override val taskRepository: TaskRepository by lazy {
        OfflineTaskRepository(
            TaskDatabase
                .getDatabase(context)
                .taskDao()
        )
    }

    override val quoteRepository: QuoteRepository by lazy {
        QuoteRepository()
    }
}