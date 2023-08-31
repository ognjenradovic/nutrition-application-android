package rs.raf.vezbe11.presentation.view.states

import rs.raf.vezbe11.data.models.Meal
import rs.raf.vezbe11.data.models.MealsWrapper

sealed class MealsState {
    object Loading: MealsState()
    object DataFetched: MealsState()
    data class Success(val meals: MealsWrapper): MealsState()
    data class Error(val message: String): MealsState()
}