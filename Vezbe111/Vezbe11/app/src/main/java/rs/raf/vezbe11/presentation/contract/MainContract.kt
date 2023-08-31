package rs.raf.vezbe11.presentation.contract

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.Single
import rs.raf.vezbe11.data.models.Category
import rs.raf.vezbe11.data.models.Meal
import rs.raf.vezbe11.presentation.view.states.*

interface MainContract {

    interface ViewModel {

        val mealsState: LiveData<MealsState>
        val savedMealsState: LiveData<SavedMealsState>
        val filterMealsState: LiveData<FilterMealsState>
        val categoriesState: LiveData<CategoriesState>
        val addDoneMeals: LiveData<AddMealState>


        fun fetchAllMeals()
        fun fetchAllCategories()
        fun getAllCategories()
        fun getAllMeals()
        fun getAllMealsForCategory(category: Category)
        fun getAllMealsForCategoryAndName(category: Category,string:String)
        fun getSavedMeals()
        fun setMealAsSaved(
            mealId: String,
            imageSource: String,
            cookingDate: String,
            mealType: String
        )
        fun getMealsByParameter(value: String, parameter: String, page: Int)
        fun addMeal(meal: Meal)
        fun getCategoriesByName(name: String)
        fun deleteSaveMeal(id: String)

        fun getMealsByCategory(category: String, page: Int)
        fun getMealsByCategoryAndName(category: String,name:String, page: Int)
        fun fetchAllMealsByCategory(name: String, page: Int)
        fun getMealsByArea(category: String, page: Int)
        fun getMealsByIngredient(category: String, page: Int)
        fun fetchAllMealsByArea(name: String, page: Int)
        fun fetchAllMealsByIngredient(name: String, page: Int)
        fun fetchAllMealsByName(name: String, page: Int)
        fun getMealsByName(name: String, page: Int)
        fun fetchMealDetails(id: String)
        //fun getMealDetails(id: String)


        val mealDetailsState: MutableLiveData<MealDetailsState>
    }

}