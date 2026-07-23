
@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.taskmate.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmate.ui.theme.TaskMateTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.graphics.Color
import com.example.taskmate.ui.theme.Black
import com.example.taskmate.ui.theme.White

@Composable
fun AddTaskScreen() {

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var deadline by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("タスク追加") },

                navigationIcon = {
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .padding(start = 8.dp, end = 16.dp)
                            .background(Black, CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "戻る",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            // タイトル
            Text("タイトル")
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 詳細
            Text("詳細")
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 締め切り
            Text("締め切り日")
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = deadline,
                onValueChange = {},
                placeholder = {},
                modifier = Modifier
                    .fillMaxWidth(),
                readOnly = true,
                enabled = true
            )

            Button(
                onClick = { showDatePicker = true },
                modifier = Modifier
                    .padding(top = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Black,
                    contentColor = White
                )
            ) {
                Text("日付選択")
            }
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { /* 後で処理 */ },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Black,
                    contentColor = White
                )
            ) {
                Text("保存")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddTaskScreen() {
    TaskMateTheme {
        AddTaskScreen()
    }
}

