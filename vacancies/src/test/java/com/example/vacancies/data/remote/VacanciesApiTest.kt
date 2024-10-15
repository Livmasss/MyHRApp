package com.example.vacancies.data.remote

import com.example.core.data.remote.RetrofitConfig
import com.example.vacancies.data.remote.models.VacanciesResponseModel
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.Test

class VacanciesApiTest {
    companion object {
        private val api = RetrofitConfig.createApi(VacanciesApi::class.java)
        private var responseBody: VacanciesResponseModel? = null
    }

    @Test
    fun `Get main screen test body not null`() = runTest {
        takeBody()
    }

    @Test
    fun `Get main screen offers not empty`() = runTest {
        val checkedBody = takeBody()

        assertNotNull { checkedBody.recommendations }
        assertFalse{ checkedBody.recommendations!!.isEmpty() }
    }

    @Test
    fun `Get main screen vacancies not empty`() = runTest {
        val checkedBody = takeBody()

        assertNotNull { checkedBody.vacancies }
        assertFalse { checkedBody.vacancies!!.isEmpty() }
    }

    private suspend fun takeBody(): VacanciesResponseModel {
        if (responseBody != null)
            return responseBody!!

        val response = api.getMainScreenInfo()
        assertTrue(response.isSuccessful)

        responseBody = response.body()
        assertNotNull { responseBody }
        return responseBody!!
    }
}