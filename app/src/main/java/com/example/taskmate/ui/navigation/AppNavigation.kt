package com.example.taskmate.ui.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskmate.ui.screen.AddTaskScreen
import com.example.taskmate.ui.screen.TaskListScreen
import com.example.taskmate.viewmodel.TaskViewModel


@Composable
fun ApplierNavigation( modifier: Modifier = Modifier ) {

    val navController = rememberNavController()
    val viewModel: TaskViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "taskList"
    ) {
        composable("taskList") {
            TaskListScreen(
                onAddClick = {
                    navController.navigate("addTask")
                },
                viewModel = viewModel
            )
        }
        composable("addTask") {
            AddTaskScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                viewModel = viewModel
            )
        }
    }

}