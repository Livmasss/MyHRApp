package com.example.vacancies.presentation.models

import com.example.coreui.models.VacancyModel

interface BaseVacanciesScreenModel {
    val vacancies: List<VacancyModel>
}