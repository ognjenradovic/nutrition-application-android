package rs.raf.vezbe11.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.vezbe11.data.models.CategoryEntity
import rs.raf.vezbe11.data.models.MealEntity

@Database(
    entities = [MealEntity::class, CategoryEntity::class],
    version = 10,
    exportSchema = false)
@TypeConverters()
abstract class MealDataBase : RoomDatabase() {
    abstract fun getMealDao(): MealDao
    abstract fun getCategoryDao(): CategoryDao
}
