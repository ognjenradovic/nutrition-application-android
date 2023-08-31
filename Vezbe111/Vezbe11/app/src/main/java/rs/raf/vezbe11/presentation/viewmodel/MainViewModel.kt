package rs.raf.vezbe11.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.vezbe11.data.models.*
import rs.raf.vezbe11.data.repositories.CategoryRepository
import rs.raf.vezbe11.data.repositories.MealRepository
import rs.raf.vezbe11.presentation.contract.MainContract
import rs.raf.vezbe11.presentation.view.states.*
import timber.log.Timber
import java.util.concurrent.TimeUnit

class MainViewModel(private val mealRepository: MealRepository, private val categoryRepository: CategoryRepository
) : ViewModel(), MainContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val mealsState: MutableLiveData<MealsState> = MutableLiveData()
    override val mealDetailsState: MutableLiveData<MealDetailsState> = MutableLiveData()
    override val filterMealsState: MutableLiveData<FilterMealsState> = MutableLiveData()
    override val savedMealsState: MutableLiveData<SavedMealsState> = MutableLiveData()
    override val categoriesState: MutableLiveData<CategoriesState> = MutableLiveData()
    override val addDoneMeals: MutableLiveData<AddMealState> = MutableLiveData()


    private val publishSubject: PublishSubject<String> = PublishSubject.create()
    private val mealPublishSubject: PublishSubject<String> = PublishSubject.create()
    private val categoryPublishSubject: PublishSubject<String> = PublishSubject.create()


    init {



        val mealSubscription = mealPublishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                mealRepository
                    .getAllByName(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
//                        Timber.e("Error in meal publish subject")
//                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    mealsState.value = MealsState.Success(MealsWrapper(meals = it))
                },
                {
                    mealsState.value = MealsState.Error("Error happened while fetching meal data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(mealSubscription)

        val categorySubscription = categoryPublishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                categoryRepository
                    .getAllByName(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                     //   Timber.e("Error in category publish subject")
                        Timber.e(it)
                    }
            }
            .subscribe(
                { categories ->
                    categoriesState.value = CategoriesState.Success(CategoryWrapper(categories = categories))
                },
                { error ->
                    categoriesState.value = CategoriesState.Error("Error happened while fetching category data from db")
                    
                }
            )
        subscriptions.add(categorySubscription)



    }






    override fun fetchAllMeals() {
        val subscription = mealRepository
            .fetchAll()
            .startWith(Resource.Loading())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    if (response is Resource.Success<Unit>) {
                        mealsState.value = MealsState.DataFetched
                    } else {
                       // mealsState.value = MealsState.Error("Unexpected response or error occurred")
                    }
                },
                { error ->
                   // mealsState.value = MealsState.Error("Error happened while fetching data from the server")
                    
                }
            )

        subscriptions.add(subscription)

    }


    override fun fetchAllCategories() {
        val subscription = categoryRepository
            .fetchAll()
            .startWith(Resource.Loading())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    if (response is Resource.Success<Unit>) {
                        categoriesState.value = CategoriesState.DataFetched
                    } else {
                        //categoriesState.value = CategoriesState.Error("Unexpected response or error occurred")
                    }
                },
                { error ->
                    categoriesState.value = CategoriesState.Error("Error happened while fetching data from the server")
                    
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllCategories() {
        val subscription = categoryRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    categoriesState.value = CategoriesState.Success(it)
                },
                {
                    categoriesState.value = CategoriesState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }


    override fun getAllMeals() {
        val subscription = mealRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    mealsState.value = MealsState.Success(it)
                },
                {
                    mealsState.value = MealsState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllMealsForCategory(category: Category) {
        val subscription = mealRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { meals ->
                    val filteredMeals = meals.meals.filter { it.category == category.name }
                    mealsState.value = MealsState.Success(MealsWrapper(filteredMeals))
                },
                {
                    mealsState.value = MealsState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }
    override fun getAllMealsForCategoryAndName(category: Category,name:String) {
        val subscription = mealRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { meals ->
                    val filteredMeals = meals.meals.filter { it.category == category.name && it.name.toLowerCase().contains(name.toLowerCase()) }
                    mealsState.value = MealsState.Success(MealsWrapper(filteredMeals))
                },
                {
                    mealsState.value = MealsState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getMealsByName(name: String,page: Int) {
        val subscription = mealRepository.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { meals ->
                    val filteredMeals=meals.meals.filter { meal ->
                        meal.name?.toLowerCase()?.contains(name.toLowerCase()) ?: false;
                    }
                    val startIndex = (page - 1) * 10
                    val endIndex = minOf(startIndex + 10, filteredMeals.size)
                    val paginatedMeals = filteredMeals.subList(startIndex, endIndex)
                    filterMealsState.value = FilterMealsState.Success(MealsWrapper(meals = paginatedMeals))
                },
                { error ->
                    filterMealsState.value = FilterMealsState.Error("Error occurred while fetching meals by parameter")
                    
                }
            )
        subscriptions.add(subscription)
    }

    override fun fetchMealDetails(id: String) {
        val subscription = mealRepository.getMealById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    // Assuming response contains MealsWrapper object
                    mealDetailsState.value = MealDetailsState.Success(MealsWrapper(meals = response.meals))
                },
                { error ->
                    mealDetailsState.value = MealDetailsState.Error("Error happened while fetching meal details")
                    Timber.e(error)
                }
            )
        subscriptions.add(subscription)
    }
//    override fun getMealDetails(id: String) {
//        mealRepository.getMealDetails(id)
//    }

    override fun getMealsByCategory(category: String,page: Int) {
        val subscription = mealRepository.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { meals ->
                    val filteredMeals=meals.meals.filter { meal ->
                            meal.category?.toLowerCase()?.contains(category.toLowerCase()) ?: false;
                    }
                    val startIndex = (page - 1) * 10
                    val endIndex = minOf(startIndex + 10, filteredMeals.size)
                    val paginatedMeals = filteredMeals.subList(startIndex, endIndex)
                    mealsState.value = MealsState.Success(MealsWrapper(meals = paginatedMeals))
                  filterMealsState.value = FilterMealsState.Success(MealsWrapper(meals = paginatedMeals))
                },
                { error ->

                    filterMealsState.value = FilterMealsState.Error("Error occurred while fetching meals by parameter")
                    
                }
            )
        subscriptions.add(subscription)
    }

    override fun getMealsByCategoryAndName(category: String,name:String, page: Int) {
        val subscription = mealRepository.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { meals ->
                    val filteredMeals=meals.meals.filter { meal ->
                        meal.category?.toLowerCase()?.contains(category.toLowerCase()) ?: false && (
                                meal.name.contains(name, ignoreCase = true)  ||  meal.ingredient1.equals(name, ignoreCase = true) ||
                                        meal.ingredient2.equals(name, ignoreCase = true) ||
                                        meal.ingredient3.equals(name, ignoreCase = true) ||
                                        meal.ingredient4.equals(name, ignoreCase = true) ||
                                        meal.ingredient5.equals(name, ignoreCase = true) ||
                                        meal.ingredient6.equals(name, ignoreCase = true) ||
                                        meal.ingredient7.equals(name, ignoreCase = true) ||
                                        meal.ingredient8.equals(name, ignoreCase = true) ||
                                        meal.ingredient9.equals(name, ignoreCase = true) ||
                                        meal.ingredient10.equals(name, ignoreCase = true) ||
                                        meal.ingredient11.equals(name, ignoreCase = true) ||
                                        meal.ingredient12.equals(name, ignoreCase = true) ||
                                        meal.ingredient13.equals(name, ignoreCase = true) ||
                                        meal.ingredient14.equals(name, ignoreCase = true) ||
                                        meal.ingredient15.equals(name, ignoreCase = true) ||
                                        meal.ingredient16.equals(name, ignoreCase = true) ||
                                        meal.ingredient17.equals(name, ignoreCase = true) ||
                                        meal.ingredient18.equals(name, ignoreCase = true) ||
                                        meal.ingredient19.equals(name, ignoreCase = true) ||
                                        meal.ingredient20.equals(name, ignoreCase = true))
                    }
                    val startIndex = (page - 1) * 10
                    val endIndex = minOf(startIndex + 10, filteredMeals.size)
                    val paginatedMeals = filteredMeals.subList(startIndex, endIndex)
                    mealsState.value = MealsState.Success(MealsWrapper(meals = paginatedMeals))
                },
                { error ->
                    mealsState.value = MealsState.Error("Error occurred while fetching meals by parameter")
                    
                }
            )
        subscriptions.add(subscription)
    }

    override fun getMealsByArea(area: String,page: Int) {
        val subscription = mealRepository.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { meals ->
                    val filteredMeals=meals.meals.filter { meal ->
                        meal.area?.toLowerCase()?.contains(area.toLowerCase()) ?: false;
                    }
                    val startIndex = (page - 1) * 10
                    val endIndex = minOf(startIndex + 10, filteredMeals.size)
                    val paginatedMeals = filteredMeals.subList(startIndex, endIndex)
                    filterMealsState.value = FilterMealsState.Success(MealsWrapper(meals = paginatedMeals))
                },
                { error ->
                    filterMealsState.value = FilterMealsState.Error("Error occurred while fetching meals by parameter")
                    
                }
            )
        subscriptions.add(subscription)
    }

    override fun getMealsByIngredient(value: String,page: Int) {
        val subscription = mealRepository.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { meals ->
                    val filteredMeals=meals.meals.filter { meal ->
                        meal.ingredient1.equals(value, ignoreCase = true) ||
                                meal.ingredient2.equals(value, ignoreCase = true) ||
                                meal.ingredient3.equals(value, ignoreCase = true) ||
                                meal.ingredient4.equals(value, ignoreCase = true) ||
                                meal.ingredient5.equals(value, ignoreCase = true) ||
                                meal.ingredient6.equals(value, ignoreCase = true) ||
                                meal.ingredient7.equals(value, ignoreCase = true) ||
                                meal.ingredient8.equals(value, ignoreCase = true) ||
                                meal.ingredient9.equals(value, ignoreCase = true) ||
                                meal.ingredient10.equals(value, ignoreCase = true) ||
                                meal.ingredient11.equals(value, ignoreCase = true) ||
                                meal.ingredient12.equals(value, ignoreCase = true) ||
                                meal.ingredient13.equals(value, ignoreCase = true) ||
                                meal.ingredient14.equals(value, ignoreCase = true) ||
                                meal.ingredient15.equals(value, ignoreCase = true) ||
                                meal.ingredient16.equals(value, ignoreCase = true) ||
                                meal.ingredient17.equals(value, ignoreCase = true) ||
                                meal.ingredient18.equals(value, ignoreCase = true) ||
                                meal.ingredient19.equals(value, ignoreCase = true) ||
                                meal.ingredient20.equals(value, ignoreCase = true)
                    }
                    val startIndex = (page - 1) * 10
                    val endIndex = minOf(startIndex + 10, filteredMeals.size)
                    val paginatedMeals = filteredMeals.subList(startIndex, endIndex)
                    filterMealsState.value = FilterMealsState.Success(MealsWrapper(meals = paginatedMeals))
                },
                { error ->
                    filterMealsState.value = FilterMealsState.Error("Error occurred while fetching meals by parameter")
                    
                }
            )
        subscriptions.add(subscription)
    }


    override fun fetchAllMealsByName(name: String,page:Int) {
        val subscription = mealRepository
            .fetchAllByName(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    //addDoneMeals.value = AddMealState.Success
                    getMealsByName(name,page)
                },
                {
                    //addDoneMeals.value = AddMealState.Error("Error happened while adding meal")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun fetchAllMealsByCategory(name: String,page:Int) {
        val subscription = mealRepository
            .fetchAllByCategory(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    //addDoneMeals.value = AddMealState.Success
                   getMealsByCategory(name,page)
                },
                {
                    //addDoneMeals.value = AddMealState.Error("Error happened while adding meal")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }
    override fun fetchAllMealsByIngredient(name: String, page:Int) {
        val subscription = mealRepository
            .fetchAllByIngredient(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    //addDoneMeals.value = AddMealState.Success
                    getMealsByIngredient(name,page)
                },
                {
                    //addDoneMeals.value = AddMealState.Error("Error happened while adding meal")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }
    override fun fetchAllMealsByArea(name: String,page: Int) {
        val subscription = mealRepository
            .fetchAllByArea(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    //addDoneMeals.value = AddMealState.Success
                    getMealsByArea(name,page)
                },
                {
                    //addDoneMeals.value = AddMealState.Error("Error happened while adding meal")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getMealsByParameter(value: String, parameter: String, page: Int) {
        savedMealsState.value = SavedMealsState.Loading
        if (parameter.equals("Name", ignoreCase = true)) {
            fetchAllMealsByName(value,page)
        }
        else if(parameter.equals("Category", ignoreCase = true)){
            fetchAllMealsByCategory(value,page)
        }
        else if(parameter.equals("Ingredient", ignoreCase = true)){
            fetchAllMealsByIngredient(value,page)
        }
        else if(parameter.equals("Area", ignoreCase = true)){
            fetchAllMealsByArea(value,page)
        }
        else if(parameter.equals("Saved Meals", ignoreCase = true)){

            val subscription = mealRepository.getSavedMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { meals ->
                        val filteredMeals = meals.filter { meal ->
                            meal.name.contains(value,ignoreCase = true)
                            }
                        val startIndex = (page - 1) * 20
                        val endIndex = minOf(startIndex + 20, filteredMeals.size)
                        val paginatedMeals = filteredMeals.subList(startIndex, endIndex)

                        savedMealsState.value = SavedMealsState.Success(MealsWrapper(meals = paginatedMeals))
                    },
                    { error ->
                        savedMealsState.value = SavedMealsState.Error("Error occurred while fetching meals by parameter")

                    }
                )
            subscriptions.add(subscription)

        }
        else{

            val subscription = mealRepository.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { meals ->
                        val filteredMeals = when (parameter) {
                            "Tag" -> meals.meals.filter { meal ->
                                meal.tags?.toLowerCase()?.contains(value.toLowerCase()) ?: false;
                            }
                            else -> meals.meals.filter { meal ->

                                meal.ingredient1.equals(value, ignoreCase = true) ||
                                        meal.ingredient2.equals(value, ignoreCase = true) ||
                                        meal.ingredient3.equals(value, ignoreCase = true) ||
                                        meal.ingredient4.equals(value, ignoreCase = true) ||
                                        meal.ingredient5.equals(value, ignoreCase = true) ||
                                        meal.ingredient6.equals(value, ignoreCase = true) ||
                                        meal.ingredient7.equals(value, ignoreCase = true) ||
                                        meal.ingredient8.equals(value, ignoreCase = true) ||
                                        meal.ingredient9.equals(value, ignoreCase = true) ||
                                        meal.ingredient10.equals(value, ignoreCase = true) ||
                                        meal.ingredient11.equals(value, ignoreCase = true) ||
                                        meal.ingredient12.equals(value, ignoreCase = true) ||
                                        meal.ingredient13.equals(value, ignoreCase = true) ||
                                        meal.ingredient14.equals(value, ignoreCase = true) ||
                                        meal.ingredient15.equals(value, ignoreCase = true) ||
                                        meal.ingredient16.equals(value, ignoreCase = true) ||
                                        meal.ingredient17.equals(value, ignoreCase = true) ||
                                        meal.ingredient18.equals(value, ignoreCase = true) ||
                                        meal.ingredient19.equals(value, ignoreCase = true) ||
                                        meal.ingredient20.equals(value, ignoreCase = true)

                            }
                        }
                        val startIndex = (page - 1) * 20
                        val endIndex = minOf(startIndex + 20, filteredMeals.size)
                        val paginatedMeals = filteredMeals.subList(startIndex, endIndex)

                        mealsState.value = MealsState.Success(MealsWrapper(meals = paginatedMeals))
                    },
                    { error ->
                        mealsState.value = MealsState.Error("Error occurred while fetching meals by parameter")
                        
                    }
                )
            subscriptions.add(subscription)

        }



    }


    override fun getCategoriesByName(name: String) {
        categoryPublishSubject.onNext(name)
    }

    override fun addMeal(meal: Meal) {
        val subscription = mealRepository
            .insert(meal)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    addDoneMeals.value = AddMealState.Success
                },
                {
                    addDoneMeals.value = AddMealState.Error("Error happened while adding meal")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getSavedMeals() {
        val subscription = mealRepository
            .getSavedMeals()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { savedMeals ->
                    savedMealsState.value = SavedMealsState.Success(MealsWrapper(savedMeals))
                },
                { error ->
                    savedMealsState.value = SavedMealsState.Error("Error occurred while fetching saved meals")
                    
                }
            )
        subscriptions.add(subscription)
    }

    override fun setMealAsSaved(id: String, newImageSource: String, cookingDate: String, mealType: String) {
        val subscription = mealRepository
            .setMealAsSaved(id, newImageSource, cookingDate, mealType)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    mealsState.value = MealsState.Success(MealsWrapper(emptyList()))
                },
                { error ->
                    mealsState.value = MealsState.Error("Error occurred while updating saved meal")
                    
                }
            )
        subscriptions.add(subscription)
    }

    override fun deleteSaveMeal(id: String) {
        val subscription = mealRepository
            .deleteSaveMeal(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    mealsState.value = MealsState.Success(MealsWrapper(emptyList()))
                },
                { error ->
                    mealsState.value = MealsState.Error("Error occurred while updating saved meal")
                    
                }
            )
        subscriptions.add(subscription)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}