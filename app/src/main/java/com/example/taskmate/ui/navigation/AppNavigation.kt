package com.example.taskmate.ui.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskmate.ui.screen.AddTaskScreen
import com.example.taskmate.ui.screen.TaskListScreen


@Composable
fun ApplierNavigation(modifier: Modifier) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "taskList"
    ) {
        composable("taskList") {
            TaskListScreen(
                onAddClick = {
                    navController.navigate("addTask")
                }
            )
        }
        composable("addTask") {
            AddTaskScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }

}