package com.example.lab_2

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun RecipeListScreen(
    uiState: RecipeUiState,
    onSearchQueryChange: (String) -> Unit,
    onFilterChange: (String) -> Unit,
    onRecipeClick: (Int) -> Unit,
    onStatusChange: (Int, RecipeStatus) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Кулинарная книга",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = uiState.searchQuery,
            onValueChange = onSearchQueryChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Поиск рецептов...") },
            singleLine = true
        )

        FilterRow(
            selectedFilter = uiState.selectedFilter,
            onFilterChange = onFilterChange
        )

        StatsCard(uiState)

        if (uiState.filteredRecipes.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Ничего не найдено!",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = uiState.filteredRecipes,
                    key = { recipe -> recipe.id }
                ) { recipe ->
                    RecipeCard(
                        recipe = recipe,
                        onClick = { onRecipeClick(recipe.id) },
                        onStatusChange = onStatusChange
                    )
                }
            }
        }
    }
}

@Composable
fun FilterRow(
    selectedFilter: String,
    onFilterChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilterChip(
            selected = selectedFilter == "Все рецепты",
            onClick = { onFilterChange("Все рецепты") },
            label = { Text("Все рецепты") }
        )
        FilterChip(
            selected = selectedFilter == RecipeStatus.WANT_TO_COOK.name,
            onClick = { onFilterChange(RecipeStatus.WANT_TO_COOK.name) },
            label = { Text(RecipeStatus.WANT_TO_COOK.displayName()) }
        )
        FilterChip(
            selected = selectedFilter == RecipeStatus.COOKING.name,
            onClick = { onFilterChange(RecipeStatus.COOKING.name) },
            label = { Text(RecipeStatus.COOKING.displayName()) }
        )
        FilterChip(
            selected = selectedFilter == RecipeStatus.COOKED.name,
            onClick = { onFilterChange(RecipeStatus.COOKED.name) },
            label = { Text(RecipeStatus.COOKED.displayName()) }
        )
    }
}

@Composable
fun StatsCard(uiState: RecipeUiState) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text("Want to cook: ${uiState.wantToCookCount}")
            Text("Cooking: ${uiState.cookingCount}")
            Text("Cooked: ${uiState.cookedCount}")
        }
    }
}

@Composable
fun RecipeCard(
    recipe: Recipe,
    onClick: () -> Unit,
    onStatusChange: (Int, RecipeStatus) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .clickable { onClick() },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = recipe.name,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "${recipe.cookingTime} мин • ${recipe.complexity}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = recipe.status.displayName(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Button(
                onClick = { onStatusChange(recipe.id, recipe.status.next()) }
            ) {
                Text("change status")
            }
        }
    }
}