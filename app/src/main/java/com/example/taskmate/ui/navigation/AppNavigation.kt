package com.example.taskmate.ui.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskmate.TaskApplication
import com.example.taskmate.ui.screen.AddTaskScreen
import com.example.taskmate.ui.screen.TaskListScreen
import com.example.taskmate.viewmodel.TaskViewModel
import com.example.taskmate.viewmodel.TaskViewModelFactory


@Composable
fun ApplierNavigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    val application = LocalContext.current.applicationContext as TaskApplication
    val viewModel: TaskViewModel = viewModel(
        factory = TaskViewModelFactory(
            application.container.taskRepository,
            application.container.quoteRepository
        )
    )

    NavHost(
        navController = navController,
        startDestination = "taskList",
        modifier = modifier
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