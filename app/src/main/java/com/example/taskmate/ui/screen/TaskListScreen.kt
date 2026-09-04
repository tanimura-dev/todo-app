@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.taskmate.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.taskmate.ui.theme.Black
import com.example.taskmate.ui.theme.White
import com.example.taskmate.viewmodel.TaskViewModel

@Composable
fun TaskListScreen(
    onAddClick: () -> Unit,
    viewModel: TaskViewModel,
) {
    val tasks by viewModel.tasks.collectAsState()
    val quote by viewModel.quote.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadQuote()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("TaskMate")
                },
            )
        },
        floatingActionButton = {
            Box(
                modifier =
                    Modifier
                        .size(63.dp)
                        .background(Black, CircleShape)
                        .clickable { onAddClick() },
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "タスクを追加",
                    tint = White,
                    modifier = Modifier.size(28.dp),
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier.padding(paddingValues),
        ) {
            item {
                quote?.let {
                    Column(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                    ) {
                        Text(
                            text = "名言",
                            style = MaterialTheme.typography.titleMedium,
                        )

                        Text(
                            text = "「${it.meigen}」",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier =
                                Modifier
                                    .padding(top = 6.dp),
                        )

                        Text(
                            text = "${it.auther}",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier =
                                Modifier
                                    .padding(top = 3.dp),
                        )
                    }
                }
            }

            items(tasks) { task ->
                Card(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                    colors =
                        CardDefaults.cardColors(
                            containerColor = White,
                        ),
                    elevation =
                        CardDefaults.cardElevation(
                            defaultElevation = 4.dp,
                        ),
                ) {
                    Row(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(
                            modifier =
                                Modifier
                                    .weight(1f),
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Checkbox(
                                    checked = task.isCompleted,
                                    onCheckedChange = {
                                        viewModel.toggleTask(task)
                                    },
                                )
                                Text(
                                    text = task.title,
                                    style =
                                        MaterialTheme.typography.titleLarge.copy(
                                            textDecoration =
                                                if (task.isCompleted) {
                                                    TextDecoration.LineThrough
                                                } else {
                                                    null
                                                },
                                        ),
                                    color = if (task.isCompleted) Color.Gray else Color.Black,
                                )
                            }

                            if (task.description.isNotBlank()) {
                                Row(
                                    modifier =
                                        Modifier
                                            .padding(start = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Description,
                                        contentDescription = "詳細",
                                        modifier = Modifier.size(16.dp),
                                    )
                                    Text(
                                        text = task.description,
                                        modifier =
                                            Modifier
                                                .padding(start = 8.dp),
                                    )
                                }
                            }
                            if (task.deadline.isNotBlank()) {
                                Row(
                                    modifier =
                                        Modifier
                                            .padding(start = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.DateRange,
                                        contentDescription = "締め切り日",
                                        modifier = Modifier.size(16.dp),
                                    )
                                    Text(
                                        text = task.deadline,
                                        modifier =
                                            Modifier
                                                .padding(start = 8.dp),
                                    )
                                }
                            }
                        }

                        var showDialog by remember { mutableStateOf(false) }

                        IconButton(
                            onClick = {
                                showDialog = true
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Default.DeleteForever,
                                contentDescription = "削除",
                                modifier = Modifier.size(24.dp),
                            )
                        }
                        if (showDialog) {
                            AlertDialog(
                                onDismissRequest = { showDialog = false },
                                text = {
                                    Text("本当に削除しますか？")
                                },
                                confirmButton = {
                                    TextButton(
                                        onClick = {
                                            viewModel.deleteTask(task)
                                            showDialog = false
                                        },
                                    ) {
                                        Text("削除")
                                    }
                                },
                                dismissButton = {
                                    TextButton(
                                        onClick = {
                                            showDialog = false
                                        },
                                    ) {
                                        Text("キャンセル")
                                    }
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}
