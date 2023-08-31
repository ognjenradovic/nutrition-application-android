package rs.raf.vezbe11.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import rs.raf.vezbe11.data.datasources.local.MealDao
import rs.raf.vezbe11.data.datasources.remote.MealService
import rs.raf.vezbe11.data.models.Meal
import rs.raf.vezbe11.data.models.MealEntity
import rs.raf.vezbe11.data.models.MealsWrapper
import rs.raf.vezbe11.data.models.Resource
import timber.log.Timber

class MealRepositoryImpl(
    private val localDataSource: MealDao,
    private val remoteDataSource: MealService
) : MealRepository {




    override fun getSavedMeals(): Observable<List<Meal>> {
        return localDataSource.getSavedMeals()
            .map { mealEntities ->
            mealEntities.map { mealEntity ->
                Meal(
                    id = mealEntity.idMeal,
                    name = mealEntity.strMeal,
                    category = mealEntity.strCategory,
                    ingredient1 = mealEntity.strIngredient1,
                    measure1 = mealEntity.strMeasure1,
                    ingredient2 = mealEntity.strIngredient2,
                    measure2 = mealEntity.strMeasure2,
                    ingredient3 = mealEntity.strIngredient3,
                    measure3 = mealEntity.strMeasure3,
                    ingredient4 = mealEntity.strIngredient4,
                    measure4 = mealEntity.strMeasure4,
                    ingredient5 = mealEntity.strIngredient5,
                    measure5 = mealEntity.strMeasure5,
                    ingredient6 = mealEntity.strIngredient6,
                    measure6 = mealEntity.strMeasure6,
                    ingredient7 = mealEntity.strIngredient7,
                    measure7 = mealEntity.strMeasure7,
                    ingredient8 = mealEntity.strIngredient8,
                    measure8 = mealEntity.strMeasure8,
                    ingredient9 = mealEntity.strIngredient9,
                    measure9 = mealEntity.strMeasure9,
                    ingredient10 = mealEntity.strIngredient10,
                    measure10 = mealEntity.strMeasure10,
                    ingredient11 = mealEntity.strIngredient11,
                    measure11 = mealEntity.strMeasure11,
                    ingredient12 = mealEntity.strIngredient12,
                    measure12 = mealEntity.strMeasure12,
                    ingredient13 = mealEntity.strIngredient13,
                    measure13 = mealEntity.strMeasure13,
                    ingredient14 = mealEntity.strIngredient14,
                    measure14 = mealEntity.strMeasure14,
                    ingredient15 = mealEntity.strIngredient15,
                    measure15 = mealEntity.strMeasure15,
                    ingredient16 = mealEntity.strIngredient16,
                    measure16 = mealEntity.strMeasure16,
                    ingredient17 = mealEntity.strIngredient17,
                    measure17 = mealEntity.strMeasure17,
                    ingredient18 = mealEntity.strIngredient18,
                    measure18 = mealEntity.strMeasure18,
                    ingredient19 = mealEntity.strIngredient19,
                    measure19 = mealEntity.strMeasure19,
                    ingredient20 = mealEntity.strIngredient20,
                    measure20 = mealEntity.strMeasure20,
                    area = mealEntity.strArea,
                    drinkAlternate = mealEntity.strDrinkAlternate,
                    instructions = mealEntity.strInstructions,
                    tags = mealEntity.strTags,
                    youtube = mealEntity.strYoutube,
                    source = mealEntity.strSource,
                    imageSource = mealEntity.strMealThumb,
                    creativeCommonsConfirmed = mealEntity.strCreativeCommonsConfirmed,
                    dateModified = mealEntity.dateModified,
                    saved= mealEntity.saved == false,
                    cookingDate = mealEntity.cookingDate,
                    mealType = mealEntity.mealType,
                    strMealThumb = mealEntity.strMealThumb
                )
            }
        }
    }

//    override fun getMealDetails(id : String): Single<List<Meal>> {
////        return localDataSource.getById(id)
////            .map { mealEntities ->
////                mealEntities.map { mealEntity ->
////                    Meal(
////                        id = mealEntity.idMeal,
////                        name = mealEntity.strMeal,
////                        category = mealEntity.strCategory,
////                        ingredient1 = mealEntity.strIngredient1,
////                        measure1 = mealEntity.strMeasure1,
////                        ingredient2 = mealEntity.strIngredient2,
////                        measure2 = mealEntity.strMeasure2,
////                        ingredient3 = mealEntity.strIngredient3,
////                        measure3 = mealEntity.strMeasure3,
////                        ingredient4 = mealEntity.strIngredient4,
////                        measure4 = mealEntity.strMeasure4,
////                        ingredient5 = mealEntity.strIngredient5,
////                        measure5 = mealEntity.strMeasure5,
////                        ingredient6 = mealEntity.strIngredient6,
////                        measure6 = mealEntity.strMeasure6,
////                        ingredient7 = mealEntity.strIngredient7,
////                        measure7 = mealEntity.strMeasure7,
////                        ingredient8 = mealEntity.strIngredient8,
////                        measure8 = mealEntity.strMeasure8,
////                        ingredient9 = mealEntity.strIngredient9,
////                        measure9 = mealEntity.strMeasure9,
////                        ingredient10 = mealEntity.strIngredient10,
////                        measure10 = mealEntity.strMeasure10,
////                        ingredient11 = mealEntity.strIngredient11,
////                        measure11 = mealEntity.strMeasure11,
////                        ingredient12 = mealEntity.strIngredient12,
////                        measure12 = mealEntity.strMeasure12,
////                        ingredient13 = mealEntity.strIngredient13,
////                        measure13 = mealEntity.strMeasure13,
////                        ingredient14 = mealEntity.strIngredient14,
////                        measure14 = mealEntity.strMeasure14,
////                        ingredient15 = mealEntity.strIngredient15,
////                        measure15 = mealEntity.strMeasure15,
////                        ingredient16 = mealEntity.strIngredient16,
////                        measure16 = mealEntity.strMeasure16,
////                        ingredient17 = mealEntity.strIngredient17,
////                        measure17 = mealEntity.strMeasure17,
////                        ingredient18 = mealEntity.strIngredient18,
////                        measure18 = mealEntity.strMeasure18,
////                        ingredient19 = mealEntity.strIngredient19,
////                        measure19 = mealEntity.strMeasure19,
////                        ingredient20 = mealEntity.strIngredient20,
////                        measure20 = mealEntity.strMeasure20,
////                        area = mealEntity.strArea,
////                        drinkAlternate = mealEntity.strDrinkAlternate,
////                        instructions = mealEntity.strInstructions,
////                        tags = mealEntity.strTags,
////                        youtube = mealEntity.strYoutube,
////                        source = mealEntity.strSource,
////                        imageSource = mealEntity.strMealThumb,
////                        creativeCommonsConfirmed = mealEntity.strCreativeCommonsConfirmed,
////                        dateModified = mealEntity.dateModified,
////                        saved= mealEntity.saved == false,
////                        cookingDate = mealEntity.cookingDate,
////                        mealType = mealEntity.mealType
////                    )
////                }.filter { meal -> true==true } // Filter out single meals
////            }
//    }

    override fun setMealAsSaved(
        mealId: String,
        imageSource: String,
        cookingDate: String,
        mealType: String
    ): Completable {
        return localDataSource.setMealAsSaved(mealId, imageSource, cookingDate, mealType)
    }

    override fun deleteSaveMeal(
        mealId: String
    ): Completable {
        return localDataSource.deleteSaveMeal(mealId, "https://www.themealdb.com/images/media/meals/llcbn01574260722.jpg/preview", "Not avaliable", "Not avaliable")
    }

    override fun fetchAll(): Observable<Resource<Unit>> {
        return remoteDataSource
            .getAll("")
            .map {

                Timber.e("Upis u bazu")
                val meals = it.meals // Assuming the object field is called "meals"
                val entities = meals?.map {
                    MealEntity(
                        it.idMeal,
                        it.strMeal,
                        it.strDrinkAlternate,
                        it.strCategory,
                        it.strArea,
                        it.strInstructions,
                        it.strMealThumb,
                        it.strTags,
                        it.strYoutube,
                        it.strIngredient1,
                        it.strIngredient2,
                        it.strIngredient3,
                        it.strIngredient4,
                        it.strIngredient5,
                        it.strIngredient6,
                        it.strIngredient7,
                        it.strIngredient8,
                        it.strIngredient9,
                        it.strIngredient10,
                        it.strIngredient11,
                        it.strIngredient12,
                        it.strIngredient13,
                        it.strIngredient14,
                        it.strIngredient15,
                        it.strIngredient16,
                        it.strIngredient17,
                        it.strIngredient18,
                        it.strIngredient19,
                        it.strIngredient20,
                        it.strMeasure1,
                        it.strMeasure2,
                        it.strMeasure3,
                        it.strMeasure4,
                        it.strMeasure5,
                        it.strMeasure6,
                        it.strMeasure7,
                        it.strMeasure8,
                        it.strMeasure9,
                        it.strMeasure10,
                        it.strMeasure11,
                        it.strMeasure12,
                        it.strMeasure13,
                        it.strMeasure14,
                        it.strMeasure15,
                        it.strMeasure16,
                        it.strMeasure17,
                        it.strMeasure18,
                        it.strMeasure19,
                        it.strMeasure20,
                        it.strSource,
                        it.strImageSource,
                        it.strCreativeCommonsConfirmed,
                        it.dateModified

                        )
                }
                if (entities != null) {
                    localDataSource.deleteAndInsertAll(entities)
                }
            }
            .map {
                Resource.Success(Unit)
            }
    }



    override fun fetchAllByName(name: String): Observable<Resource<Unit>> {
        return remoteDataSource
            .getAll(name)
            .map {

                Timber.e("Upis u bazu")
                val meals = it.meals // Assuming the object field is called "meals"
                val entities = meals?.map {
                    MealEntity(
                        it.idMeal,
                        it.strMeal,
                        it.strDrinkAlternate,
                        it.strCategory,
                        it.strArea,
                        it.strInstructions,
                        it.strMealThumb,
                        it.strTags,
                        it.strYoutube,
                        it.strIngredient1,
                        it.strIngredient2,
                        it.strIngredient3,
                        it.strIngredient4,
                        it.strIngredient5,
                        it.strIngredient6,
                        it.strIngredient7,
                        it.strIngredient8,
                        it.strIngredient9,
                        it.strIngredient10,
                        it.strIngredient11,
                        it.strIngredient12,
                        it.strIngredient13,
                        it.strIngredient14,
                        it.strIngredient15,
                        it.strIngredient16,
                        it.strIngredient17,
                        it.strIngredient18,
                        it.strIngredient19,
                        it.strIngredient20,
                        it.strMeasure1,
                        it.strMeasure2,
                        it.strMeasure3,
                        it.strMeasure4,
                        it.strMeasure5,
                        it.strMeasure6,
                        it.strMeasure7,
                        it.strMeasure8,
                        it.strMeasure9,
                        it.strMeasure10,
                        it.strMeasure11,
                        it.strMeasure12,
                        it.strMeasure13,
                        it.strMeasure14,
                        it.strMeasure15,
                        it.strMeasure16,
                        it.strMeasure17,
                        it.strMeasure18,
                        it.strMeasure19,
                        it.strMeasure20,
                        it.strSource,
                        it.strImageSource,
                        it.strCreativeCommonsConfirmed,
                        it.dateModified,

                        )
                }
                if (entities != null) {
                    localDataSource.deleteAndInsertAll(entities)
                }
            }
            .map {
                Resource.Success(Unit)
            }
    }

    override fun fetchAllByCategory(name: String): Observable<Resource<Unit>> {
        return remoteDataSource
            .getAllByCategory(name)
            .map {

                Timber.e("Upis u bazu")
                val meals = it.meals // Assuming the object field is called "meals"
                val entities = meals?.map {
                    MealEntity(
                        it.idMeal,
                        it.strMeal,
                        it.strDrinkAlternate,
                        name,
                        it.strArea,
                        it.strInstructions,
                        it.strMealThumb,
                        it.strTags,
                        it.strYoutube,
                        it.strIngredient1,
                        it.strIngredient2,
                        it.strIngredient3,
                        it.strIngredient4,
                        it.strIngredient5,
                        it.strIngredient6,
                        it.strIngredient7,
                        it.strIngredient8,
                        it.strIngredient9,
                        it.strIngredient10,
                        it.strIngredient11,
                        it.strIngredient12,
                        it.strIngredient13,
                        it.strIngredient14,
                        it.strIngredient15,
                        it.strIngredient16,
                        it.strIngredient17,
                        it.strIngredient18,
                        it.strIngredient19,
                        it.strIngredient20,
                        it.strMeasure1,
                        it.strMeasure2,
                        it.strMeasure3,
                        it.strMeasure4,
                        it.strMeasure5,
                        it.strMeasure6,
                        it.strMeasure7,
                        it.strMeasure8,
                        it.strMeasure9,
                        it.strMeasure10,
                        it.strMeasure11,
                        it.strMeasure12,
                        it.strMeasure13,
                        it.strMeasure14,
                        it.strMeasure15,
                        it.strMeasure16,
                        it.strMeasure17,
                        it.strMeasure18,
                        it.strMeasure19,
                        it.strMeasure20,
                        it.strSource,
                        it.strImageSource,
                        it.strCreativeCommonsConfirmed,
                        it.dateModified,

                        )
                }
                if (entities != null) {
                    localDataSource.deleteAndInsertAll(entities)
                }
            }
            .map {
                Resource.Success(Unit)
            }
    }

    override fun fetchAllByArea(name: String): Observable<Resource<Unit>> {
        return remoteDataSource
            .getAllByCategory(name)
            .map {

                Timber.e("Upis u bazu")
                val meals = it.meals // Assuming the object field is called "meals"
                val entities = meals?.map {
                    MealEntity(
                        it.idMeal,
                        it.strMeal,
                        it.strDrinkAlternate,
                        it.strCategory,
                        name,
                        it.strInstructions,
                        it.strMealThumb,
                        it.strTags,
                        it.strYoutube,
                        it.strIngredient1,
                        it.strIngredient2,
                        it.strIngredient3,
                        it.strIngredient4,
                        it.strIngredient5,
                        it.strIngredient6,
                        it.strIngredient7,
                        it.strIngredient8,
                        it.strIngredient9,
                        it.strIngredient10,
                        it.strIngredient11,
                        it.strIngredient12,
                        it.strIngredient13,
                        it.strIngredient14,
                        it.strIngredient15,
                        it.strIngredient16,
                        it.strIngredient17,
                        it.strIngredient18,
                        it.strIngredient19,
                        it.strIngredient20,
                        it.strMeasure1,
                        it.strMeasure2,
                        it.strMeasure3,
                        it.strMeasure4,
                        it.strMeasure5,
                        it.strMeasure6,
                        it.strMeasure7,
                        it.strMeasure8,
                        it.strMeasure9,
                        it.strMeasure10,
                        it.strMeasure11,
                        it.strMeasure12,
                        it.strMeasure13,
                        it.strMeasure14,
                        it.strMeasure15,
                        it.strMeasure16,
                        it.strMeasure17,
                        it.strMeasure18,
                        it.strMeasure19,
                        it.strMeasure20,
                        it.strSource,
                        it.strImageSource,
                        it.strCreativeCommonsConfirmed,
                        it.dateModified,

                        )
                }
                if (entities != null) {
                    localDataSource.deleteAndInsertAll(entities)
                }
            }
            .map {
                Resource.Success(Unit)
            }
    }

    override fun getMealById(id: String): Single<MealsWrapper> {
         remoteDataSource
            .getMealById(id)
            .map {
                Timber.e("Upis u bazu")
                val meals = it.meals // Assuming the object field is called "meals"
                val entities = meals?.map {
                    MealEntity(
                        it.idMeal,
                        it.strMeal,
                        it.strDrinkAlternate,
                        it.strCategory,
                        it.strArea,
                        it.strInstructions,
                        it.strMealThumb,
                        it.strTags,
                        it.strYoutube,
                        it.strIngredient1,
                        it.strIngredient2,
                        it.strIngredient3,
                        it.strIngredient4,
                        it.strIngredient5,
                        it.strIngredient6,
                        it.strIngredient7,
                        it.strIngredient8,
                        it.strIngredient9,
                        it.strIngredient10,
                        it.strIngredient11,
                        it.strIngredient12,
                        it.strIngredient13,
                        it.strIngredient14,
                        it.strIngredient15,
                        it.strIngredient16,
                        it.strIngredient17,
                        it.strIngredient18,
                        it.strIngredient19,
                        it.strIngredient20,
                        it.strMeasure1,
                        it.strMeasure2,
                        it.strMeasure3,
                        it.strMeasure4,
                        it.strMeasure5,
                        it.strMeasure6,
                        it.strMeasure7,
                        it.strMeasure8,
                        it.strMeasure9,
                        it.strMeasure10,
                        it.strMeasure11,
                        it.strMeasure12,
                        it.strMeasure13,
                        it.strMeasure14,
                        it.strMeasure15,
                        it.strMeasure16,
                        it.strMeasure17,
                        it.strMeasure18,
                        it.strMeasure19,
                        it.strMeasure20,
                        it.strSource,
                        it.strImageSource,
                        it.strCreativeCommonsConfirmed,
                        it.dateModified,

                        )
                }
                if (entities != null) {
                    localDataSource.deleteAndInsertAll2(entities)
                }
            }

        return localDataSource
            .getById(id)
            .map { mealEntities ->
                MealsWrapper(mealEntities.map { mealEntity ->
                    Meal(
                        id = mealEntity.idMeal,
                        name = mealEntity.strMeal,
                        category = mealEntity.strCategory,
                        ingredient1 = mealEntity.strIngredient1,
                        measure1 = mealEntity.strMeasure1,
                        ingredient2 = mealEntity.strIngredient2,
                        measure2 = mealEntity.strMeasure2,
                        ingredient3 = mealEntity.strIngredient3,
                        measure3 = mealEntity.strMeasure3,
                        ingredient4 = mealEntity.strIngredient4,
                        measure4 = mealEntity.strMeasure4,
                        ingredient5 = mealEntity.strIngredient5,
                        measure5 = mealEntity.strMeasure5,
                        ingredient6 = mealEntity.strIngredient6,
                        measure6 = mealEntity.strMeasure6,
                        ingredient7 = mealEntity.strIngredient7,
                        measure7 = mealEntity.strMeasure7,
                        ingredient8 = mealEntity.strIngredient8,
                        measure8 = mealEntity.strMeasure8,
                        ingredient9 = mealEntity.strIngredient9,
                        measure9 = mealEntity.strMeasure9,
                        ingredient10 = mealEntity.strIngredient10,
                        measure10 = mealEntity.strMeasure10,
                        ingredient11 = mealEntity.strIngredient11,
                        measure11 = mealEntity.strMeasure11,
                        ingredient12 = mealEntity.strIngredient12,
                        measure12 = mealEntity.strMeasure12,
                        ingredient13 = mealEntity.strIngredient13,
                        measure13 = mealEntity.strMeasure13,
                        ingredient14 = mealEntity.strIngredient14,
                        measure14 = mealEntity.strMeasure14,
                        ingredient15 = mealEntity.strIngredient15,
                        measure15 = mealEntity.strMeasure15,
                        ingredient16 = mealEntity.strIngredient16,
                        measure16 = mealEntity.strMeasure16,
                        ingredient17 = mealEntity.strIngredient17,
                        measure17 = mealEntity.strMeasure17,
                        ingredient18 = mealEntity.strIngredient18,
                        measure18 = mealEntity.strMeasure18,
                        ingredient19 = mealEntity.strIngredient19,
                        measure19 = mealEntity.strMeasure19,
                        ingredient20 = mealEntity.strIngredient20,
                        measure20 = mealEntity.strMeasure20,
                        area = mealEntity.strArea,
                        drinkAlternate = mealEntity.strDrinkAlternate,
                        instructions = mealEntity.strInstructions,
                        tags = mealEntity.strTags,
                        youtube = mealEntity.strYoutube,
                        source = mealEntity.strSource,
                        imageSource = mealEntity.strMealThumb,
                        creativeCommonsConfirmed = mealEntity.strCreativeCommonsConfirmed,
                        dateModified = mealEntity.dateModified,
                        saved= mealEntity.saved == false ,
                        cookingDate = mealEntity.cookingDate,
                        mealType = mealEntity.mealType,
                        strMealThumb = mealEntity.strMealThumb
                    )
                })
            }
    }




    override fun fetchAllByIngredient(name: String): Observable<Resource<Unit>> {
        return remoteDataSource
            .getAllByCategory(name)
            .map {

                Timber.e("Upis u bazu")
                val meals = it.meals // Assuming the object field is called "meals"
                val entities = meals?.map {
                    MealEntity(
                        it.idMeal,
                        it.strMeal,
                        it.strDrinkAlternate,
                        it.strCategory,
                        it.strArea,
                        it.strInstructions,
                        it.strMealThumb,
                        it.strTags,
                        it.strYoutube,
                        it.strIngredient1,
                        it.strIngredient2,
                        it.strIngredient3,
                        it.strIngredient4,
                        it.strIngredient5,
                        it.strIngredient6,
                        it.strIngredient7,
                        it.strIngredient8,
                        it.strIngredient9,
                        it.strIngredient10,
                        it.strIngredient11,
                        it.strIngredient12,
                        it.strIngredient13,
                        it.strIngredient14,
                        it.strIngredient15,
                        it.strIngredient16,
                        it.strIngredient17,
                        it.strIngredient18,
                        it.strIngredient19,
                        name,
                        it.strMeasure1,
                        it.strMeasure2,
                        it.strMeasure3,
                        it.strMeasure4,
                        it.strMeasure5,
                        it.strMeasure6,
                        it.strMeasure7,
                        it.strMeasure8,
                        it.strMeasure9,
                        it.strMeasure10,
                        it.strMeasure11,
                        it.strMeasure12,
                        it.strMeasure13,
                        it.strMeasure14,
                        it.strMeasure15,
                        it.strMeasure16,
                        it.strMeasure17,
                        it.strMeasure18,
                        it.strMeasure19,
                        it.strMeasure20,
                        it.strSource,
                        it.strImageSource,
                        it.strCreativeCommonsConfirmed,
                        it.dateModified,

                        )
                }
                if (entities != null) {
                    localDataSource.deleteAndInsertAll(entities)
                }
            }
            .map {
                Resource.Success(Unit)
            }
    }




    override fun getAll(): Observable<MealsWrapper> {
        return localDataSource
            .getAll()
            .map { mealEntities ->
                MealsWrapper(mealEntities.map { mealEntity ->
                    Meal(
                        id = mealEntity.idMeal,
                        name = mealEntity.strMeal,
                        category = mealEntity.strCategory,
                        ingredient1 = mealEntity.strIngredient1,
                        measure1 = mealEntity.strMeasure1,
                        ingredient2 = mealEntity.strIngredient2,
                        measure2 = mealEntity.strMeasure2,
                        ingredient3 = mealEntity.strIngredient3,
                        measure3 = mealEntity.strMeasure3,
                        ingredient4 = mealEntity.strIngredient4,
                        measure4 = mealEntity.strMeasure4,
                        ingredient5 = mealEntity.strIngredient5,
                        measure5 = mealEntity.strMeasure5,
                        ingredient6 = mealEntity.strIngredient6,
                        measure6 = mealEntity.strMeasure6,
                        ingredient7 = mealEntity.strIngredient7,
                        measure7 = mealEntity.strMeasure7,
                        ingredient8 = mealEntity.strIngredient8,
                        measure8 = mealEntity.strMeasure8,
                        ingredient9 = mealEntity.strIngredient9,
                        measure9 = mealEntity.strMeasure9,
                        ingredient10 = mealEntity.strIngredient10,
                        measure10 = mealEntity.strMeasure10,
                        ingredient11 = mealEntity.strIngredient11,
                        measure11 = mealEntity.strMeasure11,
                        ingredient12 = mealEntity.strIngredient12,
                        measure12 = mealEntity.strMeasure12,
                        ingredient13 = mealEntity.strIngredient13,
                        measure13 = mealEntity.strMeasure13,
                        ingredient14 = mealEntity.strIngredient14,
                        measure14 = mealEntity.strMeasure14,
                        ingredient15 = mealEntity.strIngredient15,
                        measure15 = mealEntity.strMeasure15,
                        ingredient16 = mealEntity.strIngredient16,
                        measure16 = mealEntity.strMeasure16,
                        ingredient17 = mealEntity.strIngredient17,
                        measure17 = mealEntity.strMeasure17,
                        ingredient18 = mealEntity.strIngredient18,
                        measure18 = mealEntity.strMeasure18,
                        ingredient19 = mealEntity.strIngredient19,
                        measure19 = mealEntity.strMeasure19,
                        ingredient20 = mealEntity.strIngredient20,
                        measure20 = mealEntity.strMeasure20,
                        area = mealEntity.strArea,
                        drinkAlternate = mealEntity.strDrinkAlternate,
                        instructions = mealEntity.strInstructions,
                        tags = mealEntity.strTags,
                        youtube = mealEntity.strYoutube,
                        source = mealEntity.strSource,
                        imageSource = mealEntity.strMealThumb,
                        creativeCommonsConfirmed = mealEntity.strCreativeCommonsConfirmed,
                        dateModified = mealEntity.dateModified,
                        saved= mealEntity.saved == false ,
                        cookingDate = mealEntity.cookingDate,
                        mealType = mealEntity.mealType,
                        strMealThumb = mealEntity.strMealThumb
                    )
                })
            }
    }

    override fun getAllByName(name: String): Observable<List<Meal>> {
        return remoteDataSource
            .getAll(name)
            .map { mealEntities ->
                mealEntities.meals?.map { mealEntity ->
                    Meal(
                        id = mealEntity.idMeal,
                        name = mealEntity.strMeal,
                        category = mealEntity.strCategory,
                        ingredient1 = mealEntity.strIngredient1,
                        measure1 = mealEntity.strMeasure1,
                        ingredient2 = mealEntity.strIngredient2,
                        measure2 = mealEntity.strMeasure2,
                        ingredient3 = mealEntity.strIngredient3,
                        measure3 = mealEntity.strMeasure3,
                        ingredient4 = mealEntity.strIngredient4,
                        measure4 = mealEntity.strMeasure4,
                        ingredient5 = mealEntity.strIngredient5,
                        measure5 = mealEntity.strMeasure5,
                        ingredient6 = mealEntity.strIngredient6,
                        measure6 = mealEntity.strMeasure6,
                        ingredient7 = mealEntity.strIngredient7,
                        measure7 = mealEntity.strMeasure7,
                        ingredient8 = mealEntity.strIngredient8,
                        measure8 = mealEntity.strMeasure8,
                        ingredient9 = mealEntity.strIngredient9,
                        measure9 = mealEntity.strMeasure9,
                        ingredient10 = mealEntity.strIngredient10,
                        measure10 = mealEntity.strMeasure10,
                        ingredient11 = mealEntity.strIngredient11,
                        measure11 = mealEntity.strMeasure11,
                        ingredient12 = mealEntity.strIngredient12,
                        measure12 = mealEntity.strMeasure12,
                        ingredient13 = mealEntity.strIngredient13,
                        measure13 = mealEntity.strMeasure13,
                        ingredient14 = mealEntity.strIngredient14,
                        measure14 = mealEntity.strMeasure14,
                        ingredient15 = mealEntity.strIngredient15,
                        measure15 = mealEntity.strMeasure15,
                        ingredient16 = mealEntity.strIngredient16,
                        measure16 = mealEntity.strMeasure16,
                        ingredient17 = mealEntity.strIngredient17,
                        measure17 = mealEntity.strMeasure17,
                        ingredient18 = mealEntity.strIngredient18,
                        measure18 = mealEntity.strMeasure18,
                        ingredient19 = mealEntity.strIngredient19,
                        measure19 = mealEntity.strMeasure19,
                        ingredient20 = mealEntity.strIngredient20,
                        measure20 = mealEntity.strMeasure20,
                        area = mealEntity.strArea,
                        drinkAlternate = mealEntity.strDrinkAlternate,
                        instructions = mealEntity.strArea,
                        tags = mealEntity.strArea,
                        youtube = mealEntity.strArea,
                        source = mealEntity.strArea,
                        imageSource = mealEntity.strMealThumb,
                        creativeCommonsConfirmed = mealEntity.strArea,
                        dateModified = mealEntity.strArea,
                        saved = false ,
                        cookingDate = "",
                        mealType = "",
                        strMealThumb = mealEntity.strMealThumb
                    )
                }
            }
    }

    override fun getAllByCategory(name: String): Observable<List<Meal>> {
        return remoteDataSource
            .getAllByCategory(name)
            .map { mealEntities ->
                mealEntities.meals!!.map { mealEntity ->
                    Meal(
                        id = mealEntity.idMeal,
                        name = mealEntity.strMeal,
                        category = mealEntity.strCategory,
                        ingredient1 = mealEntity.strIngredient1,
                        measure1 = mealEntity.strMeasure1,
                        ingredient2 = mealEntity.strIngredient2,
                        measure2 = mealEntity.strMeasure2,
                        ingredient3 = mealEntity.strIngredient3,
                        measure3 = mealEntity.strMeasure3,
                        ingredient4 = mealEntity.strIngredient4,
                        measure4 = mealEntity.strMeasure4,
                        ingredient5 = mealEntity.strIngredient5,
                        measure5 = mealEntity.strMeasure5,
                        ingredient6 = mealEntity.strIngredient6,
                        measure6 = mealEntity.strMeasure6,
                        ingredient7 = mealEntity.strIngredient7,
                        measure7 = mealEntity.strMeasure7,
                        ingredient8 = mealEntity.strIngredient8,
                        measure8 = mealEntity.strMeasure8,
                        ingredient9 = mealEntity.strIngredient9,
                        measure9 = mealEntity.strMeasure9,
                        ingredient10 = mealEntity.strIngredient10,
                        measure10 = mealEntity.strMeasure10,
                        ingredient11 = mealEntity.strIngredient11,
                        measure11 = mealEntity.strMeasure11,
                        ingredient12 = mealEntity.strIngredient12,
                        measure12 = mealEntity.strMeasure12,
                        ingredient13 = mealEntity.strIngredient13,
                        measure13 = mealEntity.strMeasure13,
                        ingredient14 = mealEntity.strIngredient14,
                        measure14 = mealEntity.strMeasure14,
                        ingredient15 = mealEntity.strIngredient15,
                        measure15 = mealEntity.strMeasure15,
                        ingredient16 = mealEntity.strIngredient16,
                        measure16 = mealEntity.strMeasure16,
                        ingredient17 = mealEntity.strIngredient17,
                        measure17 = mealEntity.strMeasure17,
                        ingredient18 = mealEntity.strIngredient18,
                        measure18 = mealEntity.strMeasure18,
                        ingredient19 = mealEntity.strIngredient19,
                        measure19 = mealEntity.strMeasure19,
                        ingredient20 = mealEntity.strIngredient20,
                        measure20 = mealEntity.strMeasure20,
                        area = mealEntity.strArea,
                        drinkAlternate = mealEntity.strDrinkAlternate,
                        instructions = mealEntity.strArea,
                        tags = mealEntity.strArea,
                        youtube = mealEntity.strArea,
                        source = mealEntity.strArea,
                        imageSource = mealEntity.strMealThumb,
                        creativeCommonsConfirmed = mealEntity.strArea,
                        dateModified = mealEntity.strArea,
                        saved = false,
                        cookingDate = "",
                        mealType = "",
                        strMealThumb = mealEntity.strMealThumb
                    )
                }
            }
    }

    override fun getAllByIngredient(name: String): Observable<List<Meal>> {
        return remoteDataSource
            .getAllByCategory(name)
            .map { mealEntities ->
                mealEntities.meals!!.map { mealEntity ->
                    Meal(
                        id = mealEntity.idMeal,
                        name = mealEntity.strMeal,
                        category = mealEntity.strCategory,
                        ingredient1 = mealEntity.strIngredient1,
                        measure1 = mealEntity.strMeasure1,
                        ingredient2 = mealEntity.strIngredient2,
                        measure2 = mealEntity.strMeasure2,
                        ingredient3 = mealEntity.strIngredient3,
                        measure3 = mealEntity.strMeasure3,
                        ingredient4 = mealEntity.strIngredient4,
                        measure4 = mealEntity.strMeasure4,
                        ingredient5 = mealEntity.strIngredient5,
                        measure5 = mealEntity.strMeasure5,
                        ingredient6 = mealEntity.strIngredient6,
                        measure6 = mealEntity.strMeasure6,
                        ingredient7 = mealEntity.strIngredient7,
                        measure7 = mealEntity.strMeasure7,
                        ingredient8 = mealEntity.strIngredient8,
                        measure8 = mealEntity.strMeasure8,
                        ingredient9 = mealEntity.strIngredient9,
                        measure9 = mealEntity.strMeasure9,
                        ingredient10 = mealEntity.strIngredient10,
                        measure10 = mealEntity.strMeasure10,
                        ingredient11 = mealEntity.strIngredient11,
                        measure11 = mealEntity.strMeasure11,
                        ingredient12 = mealEntity.strIngredient12,
                        measure12 = mealEntity.strMeasure12,
                        ingredient13 = mealEntity.strIngredient13,
                        measure13 = mealEntity.strMeasure13,
                        ingredient14 = mealEntity.strIngredient14,
                        measure14 = mealEntity.strMeasure14,
                        ingredient15 = mealEntity.strIngredient15,
                        measure15 = mealEntity.strMeasure15,
                        ingredient16 = mealEntity.strIngredient16,
                        measure16 = mealEntity.strMeasure16,
                        ingredient17 = mealEntity.strIngredient17,
                        measure17 = mealEntity.strMeasure17,
                        ingredient18 = mealEntity.strIngredient18,
                        measure18 = mealEntity.strMeasure18,
                        ingredient19 = mealEntity.strIngredient19,
                        measure19 = mealEntity.strMeasure19,
                        ingredient20 = mealEntity.strIngredient20,
                        measure20 = mealEntity.strMeasure20,
                        area = mealEntity.strArea,
                        drinkAlternate = mealEntity.strDrinkAlternate,
                        instructions = mealEntity.strArea,
                        tags = mealEntity.strArea,
                        youtube = mealEntity.strArea,
                        source = mealEntity.strArea,
                        imageSource = mealEntity.strMealThumb,
                        creativeCommonsConfirmed = mealEntity.strArea,
                        dateModified = mealEntity.strArea,
                        saved = false,
                        cookingDate = "",
                        mealType = "",
                        strMealThumb = mealEntity.strMealThumb
                    )
                }
            }
    }

    override fun getAllByArea(name: String): Observable<List<Meal>> {
        return remoteDataSource
            .getAllByCategory(name)
            .map { mealEntities ->
                mealEntities.meals!!.map { mealEntity ->
                    Meal(
                        id = mealEntity.idMeal,
                        name = mealEntity.strMeal,
                        category = mealEntity.strCategory,
                        ingredient1 = mealEntity.strIngredient1,
                        measure1 = mealEntity.strMeasure1,
                        ingredient2 = mealEntity.strIngredient2,
                        measure2 = mealEntity.strMeasure2,
                        ingredient3 = mealEntity.strIngredient3,
                        measure3 = mealEntity.strMeasure3,
                        ingredient4 = mealEntity.strIngredient4,
                        measure4 = mealEntity.strMeasure4,
                        ingredient5 = mealEntity.strIngredient5,
                        measure5 = mealEntity.strMeasure5,
                        ingredient6 = mealEntity.strIngredient6,
                        measure6 = mealEntity.strMeasure6,
                        ingredient7 = mealEntity.strIngredient7,
                        measure7 = mealEntity.strMeasure7,
                        ingredient8 = mealEntity.strIngredient8,
                        measure8 = mealEntity.strMeasure8,
                        ingredient9 = mealEntity.strIngredient9,
                        measure9 = mealEntity.strMeasure9,
                        ingredient10 = mealEntity.strIngredient10,
                        measure10 = mealEntity.strMeasure10,
                        ingredient11 = mealEntity.strIngredient11,
                        measure11 = mealEntity.strMeasure11,
                        ingredient12 = mealEntity.strIngredient12,
                        measure12 = mealEntity.strMeasure12,
                        ingredient13 = mealEntity.strIngredient13,
                        measure13 = mealEntity.strMeasure13,
                        ingredient14 = mealEntity.strIngredient14,
                        measure14 = mealEntity.strMeasure14,
                        ingredient15 = mealEntity.strIngredient15,
                        measure15 = mealEntity.strMeasure15,
                        ingredient16 = mealEntity.strIngredient16,
                        measure16 = mealEntity.strMeasure16,
                        ingredient17 = mealEntity.strIngredient17,
                        measure17 = mealEntity.strMeasure17,
                        ingredient18 = mealEntity.strIngredient18,
                        measure18 = mealEntity.strMeasure18,
                        ingredient19 = mealEntity.strIngredient19,
                        measure19 = mealEntity.strMeasure19,
                        ingredient20 = mealEntity.strIngredient20,
                        measure20 = mealEntity.strMeasure20,
                        area = mealEntity.strArea,
                        drinkAlternate = mealEntity.strDrinkAlternate,
                        instructions = mealEntity.strArea,
                        tags = mealEntity.strArea,
                        youtube = mealEntity.strArea,
                        source = mealEntity.strArea,
                        imageSource = mealEntity.strMealThumb,
                        creativeCommonsConfirmed = mealEntity.strArea,
                        dateModified = mealEntity.strArea,
                        saved = false,
                        cookingDate = "",
                        mealType = "",
                        strMealThumb = mealEntity.strMealThumb
                    )
                }
            }
    }

    override fun insert(meal: Meal): Completable {
        val mealEntity = MealEntity(
            idMeal = meal.id,
            strMeal = meal.name,
            strCategory = meal.category,
            strArea = meal.area,
            strInstructions = meal.instructions,
            strMealThumb = meal.imageSource,
            strTags = meal.tags,
            strYoutube = meal.youtube,
            strIngredient1 = meal.ingredient1,
            strIngredient2 = meal.ingredient2,
            strIngredient3 = meal.ingredient3,
            strIngredient4 = meal.ingredient4,
            strIngredient5 = meal.ingredient5,
            strIngredient6 = meal.ingredient6,
            strIngredient7 = meal.ingredient7,
            strIngredient8 = meal.ingredient8,
            strIngredient9 = meal.ingredient9,
            strIngredient10 = meal.ingredient10,
            strIngredient11 = meal.ingredient11,
            strIngredient12 = meal.ingredient12,
            strIngredient13 = meal.ingredient13,
            strIngredient14 = meal.ingredient14,
            strIngredient15 = meal.ingredient15,
            strIngredient16 = meal.ingredient16,
            strIngredient17 = meal.ingredient17,
            strIngredient18 = meal.ingredient18,
            strIngredient19 = meal.ingredient19,
            strIngredient20 = meal.ingredient20,
            strMeasure1 = meal.measure1,
            strMeasure2 = meal.measure2,
            strMeasure3 = meal.measure3,
            strMeasure4 = meal.measure4,
            strMeasure5 = meal.measure5,
            strMeasure6 = meal.measure6,
            strMeasure7 = meal.measure7,
            strMeasure8 = meal.measure8,
            strMeasure9 = meal.measure9,
            strMeasure10 = meal.measure10,
            strMeasure11 = meal.measure11,
            strMeasure12 = meal.measure12,
            strMeasure13 = meal.measure13,
            strMeasure14 = meal.measure14,
            strMeasure15 = meal.measure15,
            strMeasure16 = meal.measure16,
            strMeasure17 = meal.measure17,
            strMeasure18 = meal.measure18,
            strMeasure19 = meal.measure19,
            strMeasure20 = meal.measure20,
            strSource = meal.source,
            strImageSource = meal.imageSource,
            strCreativeCommonsConfirmed = meal.creativeCommonsConfirmed,
            dateModified = meal.dateModified,
            strDrinkAlternate = meal.drinkAlternate,
        )
        return localDataSource.insert(mealEntity)
    }





    //Categories


}