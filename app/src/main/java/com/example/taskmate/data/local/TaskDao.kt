package com.example.taskmate.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.taskmate.data.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * from Task WHERE id = :id")
    fun getTask(id: Int): Flow<Task>

    @Query("SELECT * from Task ORDER BY isCompleted ASC,createdAt DESC")
    fun getAllTasks(): Flow<List<Task>>
}
