package rs.raf.vezbe11.presentation.view.states

import rs.raf.vezbe11.data.models.CategoryWrapper
import rs.raf.vezbe11.data.models.Meal
import rs.raf.vezbe11.data.models.MealsWrapper

sealed class CategoriesState {
    object Loading: CategoriesState()
    object DataFetched: CategoriesState()
    data class Success(val categories: CategoryWrapper): CategoriesState()
    data class Error(val message: String): CategoriesState()
}