@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.taskmate.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FabPosition
import androidx.compose.ui.Alignment
import com.example.taskmate.ui.theme.Black
import com.example.taskmate.ui.theme.White


@Composable
fun TaskListScreen(modifier: Modifier) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("TaskMate")
                }
            )
        },
        floatingActionButton = {
            Box(
                modifier = Modifier
                    .size(63.dp)
                    .background(Black, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = White,
                    modifier = Modifier.size(28.dp)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
          items(listOf("勉強","買い物","筋トレ")) { task ->
              Text(
                  text = task,
                  modifier = Modifier.padding(16.dp)
              )
          }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun PreviewTaskListScreen() {
    TaskListScreen(modifier = Modifier)
}