package com.example.lab_2

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RecipeDetailScreen(
    recipe: Recipe,
    onStatusChange: (Int, RecipeStatus) -> Unit,
    onNavigateBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        IconButton(
            onClick = onNavigateBack,
            modifier = Modifier.size(70.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Назад"
            )
        }


        Text(
            text = recipe.name,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text(text = "${recipe.cookingTime} мин •")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = recipe.complexity)
        }


        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(
                text = "Статус: ",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = recipe.status.displayName(),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(end = 8.dp)
            )

            Button(
                onClick = {
                    onStatusChange(recipe.id, recipe.status.next())
                }
            ) {
                Text("Сменить")
            }
        }


        Text(
            text = "Ингредиенты:",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(
                items = recipe.ingredients,
                key = { ingredient -> ingredient }
            ) { ingredient ->
                Text(
                    text = "• $ingredient",
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }

            item {
                Column {
                    Text(
                        text = "Описание:",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )

                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = recipe.description,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }
            }
        }
    }
}

