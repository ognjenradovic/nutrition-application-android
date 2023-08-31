package rs.raf.vezbe11.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import rs.raf.vezbe11.data.models.*

interface MealRepository {

    fun fetchAll(): Observable<Resource<Unit>>
    fun getAll(): Observable<MealsWrapper>
    fun getAllByName(name: String): Observable<List<Meal>>
    fun insert(meal: Meal): Completable
    fun getSavedMeals(): Observable<List<Meal>>
    fun setMealAsSaved(mealId: String, imageSource: String, cookingDate: String, mealType: String): Completable

    fun deleteSaveMeal(mealId: String): Completable
    fun fetchAllByName(name: String): Observable<Resource<Unit>>
    fun fetchAllByCategory(name: String): Observable<Resource<Unit>>
    fun getAllByCategory(name: String): Observable<List<Meal>>
    fun getAllByIngredient(name: String): Observable<List<Meal>>
    fun getAllByArea(name: String): Observable<List<Meal>>
    fun fetchAllByIngredient(name: String): Observable<Resource<Unit>>
    fun fetchAllByArea(name: String): Observable<Resource<Unit>>
    fun getMealById(id : String): Single<MealsWrapper>
}