
@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.taskmate.ui.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import java.util.Locale
import com.example.taskmate.ui.theme.Black
import com.example.taskmate.ui.theme.White
import com.example.taskmate.viewmodel.TaskViewModel
import com.example.taskmate.data.model.Task


@Composable
fun AddTaskScreen(
    onBackClick: () -> Unit,
    viewModel: TaskViewModel
) {

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var deadline by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val descriptionFocusRequester = remember { FocusRequester() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("タスク追加") },
                navigationIcon = {
                    IconButton(
                        onClick = { onBackClick() },
                        modifier = Modifier
                            .padding(start = 10.dp, end = 16.dp)
                            .padding(top = 3.dp)
                            .background(Black, CircleShape)
                            .size(47.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        descriptionFocusRequester.requestFocus()
                    }
                )
            )
            Spacer(modifier = Modifier.height(12.dp))

            // 詳細
            Text("詳細")
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(descriptionFocusRequester),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                )
            )
            Spacer(modifier = Modifier.height(12.dp))

            // 締め切り
            Text("締め切り日")
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = deadline,
                onValueChange = {},
                placeholder = { Text("日付を選択") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                trailingIcon = {
                    IconButton(
                        onClick = { showDatePicker = true }
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "日付選択"
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    viewModel.addTask(
                        Task(
                            title = title,
                            description = description,
                            deadline = deadline
                        )
                    )
                    onBackClick()
                          },
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

        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    TextButton( onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            val date = java.text.SimpleDateFormat(
                                "yyyy/MM/dd",
                                Locale.getDefault()
                            ).format(
                                java.util.Date(millis)
                            )
                            deadline = date
                        }
                        showDatePicker = false }
                    ) {
                        Text("決定")
                    }
                                },
                        dismissButton = {
                            TextButton( onClick = { showDatePicker = false }
                            ) {
                                Text("キャンセル")
                            }
                        }
            ) {
                DatePicker(
                    state = datePickerState
                        )
                    }
                }
            }
        }

