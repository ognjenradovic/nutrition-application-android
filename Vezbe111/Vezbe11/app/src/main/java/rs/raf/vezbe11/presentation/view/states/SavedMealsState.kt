package rs.raf.vezbe11.presentation.view.states

import rs.raf.vezbe11.data.models.Meal
import rs.raf.vezbe11.data.models.MealsWrapper

sealed class SavedMealsState {
    object Loading: SavedMealsState()
    object DataFetched: SavedMealsState()
    data class Success(val meals: MealsWrapper): SavedMealsState()
    data class Error(val message: String): SavedMealsState()
}