package com.example.lab_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RecipeApp()
                }
            }
        }
    }
}


object RecipeRoutes {
    const val LIST_ROUTE ="recipe_list"
    const val DETAILS_ROUTE = "recipe_details"

    const val RECIPE_ID_ARG = "recipeId"
    const val DETAILS_ROUTE_PATTERN = "$DETAILS_ROUTE/{$RECIPE_ID_ARG}"
    fun details(recipeId: Int): String = "$DETAILS_ROUTE/$recipeId"
}
@Composable
fun RecipeApp() {
    val recipeViewModel: RecipeViewModel = viewModel()
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = RecipeRoutes.LIST_ROUTE) {
        composable(RecipeRoutes.LIST_ROUTE){
            RecipeListScreen(
                uiState = recipeViewModel.uiState,
                onSearchQueryChange = { recipeViewModel.onSearchQueryChange(it) },
                onFilterChange = { recipeViewModel.onFilterChange(it) },
                onRecipeClick = { recipeId ->
                    navController.navigate(RecipeRoutes.details(recipeId))
                },
                onStatusChange = { recipeId, newStatus ->
                    recipeViewModel.onStatusChange(recipeId, newStatus)
                }
            )
        }

        composable(RecipeRoutes.DETAILS_ROUTE_PATTERN) { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getString(RecipeRoutes.RECIPE_ID_ARG)?.toInt() ?: 1
            val recipe = recipeViewModel.uiState.recipes.find { it.id == recipeId }

            if (recipe != null) {
                RecipeDetailScreen(
                    recipe = recipe,
                    onStatusChange = { id, newStatus ->
                        recipeViewModel.onStatusChange(id, newStatus)
                    },
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
