package rs.raf.vezbe11.modules

import CategoryRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.vezbe11.data.datasources.local.MealDataBase

import rs.raf.vezbe11.data.datasources.remote.CategoryService
import rs.raf.vezbe11.data.datasources.remote.MealService

import rs.raf.vezbe11.data.repositories.*
import rs.raf.vezbe11.presentation.viewmodel.MainViewModel

val mealsModule = module {


    single<MealRepository> { MealRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }
    single<CategoryRepository> { CategoryRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }
    single { get<MealDataBase>().getMealDao() }
    single { get<MealDataBase>().getCategoryDao() }

    single<MealService> { create(retrofit = get()) }
    single<CategoryService> { create(retrofit = get()) }

}

