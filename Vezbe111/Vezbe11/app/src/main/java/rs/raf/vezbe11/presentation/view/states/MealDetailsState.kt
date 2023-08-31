package rs.raf.vezbe11.presentation.view.states

import rs.raf.vezbe11.data.models.Meal
import rs.raf.vezbe11.data.models.MealsWrapper

sealed class MealDetailsState {
    object Loading: MealDetailsState()
    object DataFetched: MealDetailsState()
    data class Success(val meals: MealsWrapper): MealDetailsState()
    data class Error(val message: String): MealDetailsState()
}