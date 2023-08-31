package rs.raf.vezbe11.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import rs.raf.vezbe11.data.models.Meal
import rs.raf.vezbe11.data.models.MealEntity

@Dao
abstract class MealDao {

    @Insert( onConflict = OnConflictStrategy.IGNORE )
    abstract fun insert(entity: MealEntity): Completable



    @Insert( onConflict = OnConflictStrategy.IGNORE )
    abstract fun insertAll(entities: List<MealEntity>): Completable

    @Query("SELECT * FROM meals")
    abstract fun getAll(): Observable<List<MealEntity>>

    @Query("SELECT * FROM meals WHERE saved = 1")
    abstract fun getSavedMeals(): Observable<List<MealEntity>>

//    @Query("SELECT * FROM meals WHERE strCategory = :category")
//    abstract fun getMealByCategory(category: String): Observable<List<MealEntity>>

    @Query("UPDATE meals SET saved = 1, strImageSource = :imageSource, strMealThumb=:imageSource, cookingDate = :cookingDate, mealType = :mealType WHERE idMeal = :mealId")
    abstract fun setMealAsSaved(mealId: String, imageSource: String, cookingDate: String, mealType: String): Completable

    @Query("UPDATE meals SET saved = 0, strImageSource = :imageSource, strMealThumb=:imageSource, cookingDate = :cookingDate, mealType = :mealType WHERE idMeal = :mealId")
    abstract fun deleteSaveMeal(mealId: String, imageSource: String, cookingDate: String, mealType: String): Completable
//https://www.themealdb.com/images/media/meals/llcbn01574260722.jpg/preview
    @Query("DELETE FROM meals WHERE saved = 0")
    abstract fun deleteAll()

    @Transaction
    open fun deleteAndInsertAll(entities: List<MealEntity>) {
       // deleteAll()
        insertAll(entities).blockingAwait()
    }

    open fun deleteAndInsertAll2(entities: List<MealEntity>) {
         deleteAll()
        insertAll(entities).blockingAwait()
    }

    @Query("SELECT * FROM meals WHERE strMeal LIKE :name || '%'")
    abstract fun getByName(name: String): Single<List<MealEntity>>

    @Query("SELECT * FROM meals WHERE idMeal LIKE :id || '%'")
    abstract fun getById(id: String): Single<List<MealEntity>>

    }




