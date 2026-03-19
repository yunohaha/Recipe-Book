package com.example.lab_2

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class RecipeUiState(
    val recipes: List<Recipe> = sampleRecipesList,
    val searchQuery: String = "",
    val selectedFilter: String = "Все"
){
    val wantToCookCount: Int = recipes.count { it.status == RecipeStatus.WANT_TO_COOK }
    val cookingCount: Int = recipes.count { it.status == RecipeStatus.COOKING }
    val cookedCount: Int = recipes.count { it.status == RecipeStatus.COOKED }

   val filteredRecipes: List<Recipe>
       get() {
           val afterSearch = if (searchQuery.isBlank()){
               recipes
           } else{
               recipes.filter {recipe ->
                   recipe.name.contains(searchQuery, ignoreCase = true)
               }
           }
           return when (selectedFilter) {
               "Все" -> afterSearch
               RecipeStatus.WANT_TO_COOK.name ->
                   afterSearch.filter { it.status == RecipeStatus.WANT_TO_COOK }
               RecipeStatus.COOKING.name ->
                   afterSearch.filter { it.status == RecipeStatus.COOKING }
               RecipeStatus.COOKED.name ->
                   afterSearch.filter { it.status == RecipeStatus.COOKED }
               else -> afterSearch
           }
       }
}

class RecipeViewModel : ViewModel(){
    var uiState by mutableStateOf(RecipeUiState())
        private set

    fun onSearchQueryChange(query: String) {
        uiState = uiState.copy(searchQuery = query)
    }

    fun onFilterChange(filter: String) {
        uiState = uiState.copy(selectedFilter = filter)
    }

    fun onStatusChange(recipeId: Int, newStatus: RecipeStatus) {
        val updatedRecipes = uiState.recipes.map { recipe ->
            if (recipe.id == recipeId) {
                recipe.copy(status = newStatus)
            } else {
                recipe
            }
        }
        uiState = uiState.copy(recipes = updatedRecipes)
    }

}