package com.example.lab_2

data class Recipe (
    val id: Int,
    val name: String,
    val cookingTime: Int,
    val complexity: String,
    val ingredients: List<String>,
    val description: String,
    val status: RecipeStatus = RecipeStatus.WANT_TO_COOK
)

val sampleRecipesList = listOf(
    Recipe(
        id = 1,
        name = "Паста карбонара",
        cookingTime = 25,
        complexity = "middle",
        ingredients = listOf("Спагетти", "Яйца", "Бекон", "Сыр пармезан"),
        description = "Классическая итальянская паста.",
        status = RecipeStatus.WANT_TO_COOK
    ),

    Recipe(
        id = 2,
        name = "Овсяноблин",
        cookingTime = 10,
        complexity = "easy",
        ingredients = listOf("Овсяные хлопья", "Яйцо", "Молоко"),
        description = "Полезный завтрак за 10 минут!!",
        status = RecipeStatus.COOKING
    ),

    Recipe(
        id = 3,
        name = "Сырники",
        cookingTime = 20,
        complexity = "hard",
        ingredients = listOf("Творог", "Яйцо", "Мука", "Сахар", "Ванилин"),
        description = "Нежные сырники, которые тают во рту. Подавать со сметаной или вареньем.",
        status = RecipeStatus.WANT_TO_COOK
    ),

    Recipe(
        id = 4,
        name = "Борщ",
        cookingTime = 90,
        complexity = "hard",
        ingredients = listOf("Свёкла", "Капуста", "Картофель", "Морковь", "Лук",
            "Томатная паста", "Говядина", "Чеснок", "Сметана"),
        description = "Настоящий борщ с пампушками. Варится долго, но результат того стоит!",
        status = RecipeStatus.COOKING
    ),

    Recipe(
        id = 5,
        name = "Шоколадный брауни",
        cookingTime = 35,
        complexity = "middle",
        ingredients = listOf( "Тёмный шоколад", "Сливочное масло", "Сахар", "Яйца",
            "Мука", "Какао-порошок", "Грецкие орехи"),
        description = "Влажный шоколадный пирог с хрустящей корочкой и орехами. Идеален с мороженым!",
        status = RecipeStatus.COOKED
    ),
)