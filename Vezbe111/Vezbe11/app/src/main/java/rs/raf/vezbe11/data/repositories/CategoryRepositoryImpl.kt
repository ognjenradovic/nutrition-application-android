import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.vezbe11.data.datasources.local.CategoryDao
import rs.raf.vezbe11.data.datasources.remote.CategoryService
import rs.raf.vezbe11.data.models.*
import rs.raf.vezbe11.data.repositories.CategoryRepository
import timber.log.Timber

class CategoryRepositoryImpl(
    private val localDataSource: CategoryDao,
    private val remoteDataSource: CategoryService
) : CategoryRepository {

    override fun fetchAll(): Observable<Resource<Unit>> {
        return remoteDataSource
            .getAll()
            .map {
              //  Timber.e("Upis u bazu")
                val categories = it.categories // Assuming the object field is called "meals"
                val entities = categories.map {
                    CategoryEntity(
                        it.idCategory,
                        it.strCategory,
                        it.strCategoryThumb,
                        it.strCategoryDescription
                    )
                }
                localDataSource.deleteAndInsertAllCategories(entities)
            }
            .map {
                Resource.Success(Unit)
            }
    }

    override fun getAll(): Observable<CategoryWrapper> {
        return localDataSource
            .getAllCategories()
            .map { categories ->
                CategoryWrapper(
                    categories.map { category ->
                        Category(
                            category.idCategory,
                            category.strCategory,
                            category.strCategoryThumb,
                            category.strCategoryDescription
                        )
                    }
                )
            }
    }

    override fun getAllByName(name: String): Observable<List<Category>> {
        return localDataSource
            .getAllByName(name)
            .map {
                it.map {
                    Category(it.idCategory, it.strCategory,it.strCategoryThumb,it.strCategoryDescription)
                }
            }
    }
}
