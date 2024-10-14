package com.example.vacancies.data.remote

import com.example.vacancies.data.remote.models.MainScreenResponseModel
import retrofit2.Response
import retrofit2.http.GET

internal interface VacanciesApi {
    @GET("u/0/uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download/")
    suspend fun getMainScreenInfo(): Response<MainScreenResponseModel>
}