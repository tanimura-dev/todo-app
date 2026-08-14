package com.example.taskmate.data.repository

import com.example.taskmate.data.local.TaskDao
import com.example.taskmate.data.model.Task
import kotlinx.coroutines.flow.Flow

class OfflineTaskRepository(private val taskDao: TaskDao) : TaskRepository {
    override fun getAllTasksStream(): Flow<List<Task>> = taskDao.getAllTasks()

    override fun getTaskStream(id: Int): Flow<Task?> = taskDao.getTask(id)

    override  suspend fun insertTask(task: Task) = taskDao.insertTask(task)

    override suspend fun deleteTask(task: Task) = taskDao.delete(task)

    override suspend fun updateTask(task: Task) = taskDao.update(task)
}