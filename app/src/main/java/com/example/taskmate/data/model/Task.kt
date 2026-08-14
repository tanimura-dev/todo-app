package com.example.taskmate.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val deadline: String,
    val isCompleted: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)