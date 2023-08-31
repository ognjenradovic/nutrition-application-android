package rs.raf.vezbe11.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.vezbe11.data.models.Category
import rs.raf.vezbe11.data.models.CategoryEntity
import rs.raf.vezbe11.data.models.MealEntity

@Dao
abstract class CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCategory(entity: CategoryEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAllCategories(entities: List<CategoryEntity>): Completable

    @Query("SELECT * FROM categories")
    abstract fun getAllCategories(): Observable<List<CategoryEntity>>

    @Query("DELETE FROM categories")
    abstract fun deleteAllCategories()

    @Transaction
    open fun deleteAndInsertAllCategories(entities: List<CategoryEntity>) {
        deleteAllCategories()
        insertAllCategories(entities).blockingAwait()
    }

//    @Query("SELECT * FROM categories WHERE strCategory LIKE :name || '%'")
//    abstract fun getAllByName(name: String): Observable<List<CategoryEntity>>

//    @Query("SELECT DISTINCT categories.* FROM categories " +
//            "INNER JOIN meals ON categories.strCategory = meals.strCategory WHERE categories.strCategory LIKE :name || '%' ")
//    @RewriteQueriesToDropUnusedColumns
//    abstract fun getAllByName(name: String): Observable<List<CategoryEntity>>

    @Query("SELECT DISTINCT categories.* FROM categories " +
            "INNER JOIN meals ON categories.strCategory = meals.strCategory " +
            "WHERE categories.strCategory LIKE :name || '%' " +
            "OR categories.strCategory LIKE :name || '%' " +
            "OR meals.strMeal LIKE :name || '%' " +
            "OR meals.strIngredient1 LIKE :name || '%' " +
            "OR meals.strIngredient2 LIKE :name || '%' " +
            "OR meals.strIngredient3 LIKE :name || '%' " +
            "OR meals.strIngredient4 LIKE :name || '%' " +
            "OR meals.strIngredient5 LIKE :name || '%' " +
            "OR meals.strIngredient6 LIKE :name || '%' " +
            "OR meals.strIngredient7 LIKE :name || '%' " +
            "OR meals.strIngredient8 LIKE :name || '%' " +
            "OR meals.strIngredient9 LIKE :name || '%' " +
            "OR meals.strIngredient10 LIKE :name || '%' " +
            "OR meals.strIngredient11 LIKE :name || '%' " +
            "OR meals.strIngredient12 LIKE :name || '%' " +
            "OR meals.strIngredient13 LIKE :name || '%' " +
            "OR meals.strIngredient14 LIKE :name || '%' " +
            "OR meals.strIngredient15 LIKE :name || '%' " +
            "OR meals.strIngredient16 LIKE :name || '%' " +
            "OR meals.strIngredient17 LIKE :name || '%' " +
            "OR meals.strIngredient18 LIKE :name || '%' " +
            "OR meals.strIngredient19 LIKE :name || '%' " +
            "OR meals.strIngredient20 LIKE :name || '%'")
    @RewriteQueriesToDropUnusedColumns
    abstract fun getAllByName(name: String): Observable<List<CategoryEntity>>
}
