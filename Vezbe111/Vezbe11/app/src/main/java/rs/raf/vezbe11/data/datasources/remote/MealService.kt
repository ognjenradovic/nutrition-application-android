package rs.raf.vezbe11.data.datasources.remote


import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import rs.raf.vezbe11.data.models.AllMealsResponse

interface MealService {

    @GET("search.php")
    fun getAll(@Query("s") searchQuery: String): Observable<AllMealsResponse>

    @GET("filter.php")
    fun getAllByCategory(@Query("c") searchQuery: String): Observable<AllMealsResponse>

    @GET("filter.php")
    fun getAllByArea(@Query("a") searchQuery: String): Observable<AllMealsResponse>

    @GET("filter.php")
    fun getAllByIngredient(@Query("i") searchQuery: String): Observable<AllMealsResponse>


    @GET("lookup.php")
    fun getMealById(@Query("i") id: String): Observable<AllMealsResponse>

}
