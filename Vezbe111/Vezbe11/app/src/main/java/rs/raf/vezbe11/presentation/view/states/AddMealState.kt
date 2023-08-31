package rs.raf.vezbe11.presentation.view.states

sealed class AddMealState {
    object Success: AddMealState()
    data class Error(val message: String): AddMealState()
}