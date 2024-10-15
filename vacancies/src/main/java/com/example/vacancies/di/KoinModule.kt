package com.example.vacancies.di

import com.example.core.data.remote.RetrofitConfig
import com.example.vacancies.data.remote.VacanciesApi
import com.example.vacancies.data.remote.sources.VacanciesRemoteDataSource
import com.example.vacancies.data.repositories.impl.VacanciesRepositoryImpl
import com.example.vacancies.domain.repositories.VacanciesRepository
import com.example.vacancies.domain.useCases.GetVacanciesScreenUseCase
import com.example.vacancies.presentation.screens.main.MainVacanciesViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val vacanciesKoinModule = module {
    single<VacanciesApi> {
        RetrofitConfig.createApi(VacanciesApi::class.java)
    }
    singleOf(::VacanciesRemoteDataSource)

    single<VacanciesRepository> {
        VacanciesRepositoryImpl(get())
    }
    singleOf(::GetVacanciesScreenUseCase)
    viewModelOf(::MainVacanciesViewModel)
}