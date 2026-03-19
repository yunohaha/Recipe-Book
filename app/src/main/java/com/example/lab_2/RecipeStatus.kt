package com.example.lab_2

enum class RecipeStatus {
    WANT_TO_COOK,
    COOKING,
    COOKED;

    fun displayName(): String {
        return when (this){
            WANT_TO_COOK -> "Want to cook"
            COOKING -> "Cooking"
            COOKED -> "Cooked"
        }
    }

    fun next(): RecipeStatus {
        return when (this) {
            WANT_TO_COOK -> COOKING
            COOKING -> COOKED
            COOKED -> WANT_TO_COOK
        }
    }
}