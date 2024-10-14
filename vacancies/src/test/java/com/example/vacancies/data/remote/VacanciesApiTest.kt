package com.example.vacancies.data.remote

import com.example.core.data.RetrofitConfig
import com.example.vacancies.data.remote.models.MainScreenResponseModel
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Test

class VacanciesApiTest {
    companion object {
        private val api = RetrofitConfig.createApi(VacanciesApi::class.java)
        private var responseBody: MainScreenResponseModel? = null
    }

    @Test
    fun `Get main screen test null body`() = runTest {
        takeBody()
    }

    @Test
    fun `Get main screen null offers`() = runTest {
        val checkedBody = takeBody()

        assertNotNull(checkedBody.offers)
    }

    @Test
    fun `Get main screen null vacancies`() = runTest {
        val checkedBody = takeBody()

        assertNotNull(checkedBody.vacancies)
    }

    private suspend fun takeBody(): MainScreenResponseModel {
        if (responseBody != null)
            return responseBody!!

        val response = api.getMainScreenInfo()
        assertTrue(response.isSuccessful)

        responseBody = response.body()
        assertNotNull { responseBody }
        return responseBody!!
    }
}