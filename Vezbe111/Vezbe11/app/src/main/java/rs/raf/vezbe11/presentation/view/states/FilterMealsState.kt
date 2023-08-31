package rs.raf.vezbe11.presentation.view.states

import rs.raf.vezbe11.data.models.Meal
import rs.raf.vezbe11.data.models.MealsWrapper

sealed class FilterMealsState {
    object Loading: FilterMealsState()
    object DataFetched: FilterMealsState()
    data class Success(val meals: MealsWrapper): FilterMealsState()
    data class Error(val message: String): FilterMealsState()
}