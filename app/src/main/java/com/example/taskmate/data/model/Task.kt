package com.example.taskmate.data.model


data class Task(
    val title: String,
    val description: String,
    val deadline: String,
    val isCompleted: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)