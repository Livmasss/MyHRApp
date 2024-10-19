package com.example.vacancies.presentation.models

import com.example.coreui.models.VacancyModel

data class OtherVacanciesScreenModel (
    override val vacancies: List<VacancyModel>
): BaseVacanciesScreenModel