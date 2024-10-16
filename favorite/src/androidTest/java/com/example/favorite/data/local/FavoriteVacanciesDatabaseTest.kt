package com.example.favorite.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.favorite.data.local.entities.FavoriteVacancyEntity
import kotlinx.coroutines.test.runTest
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException
import java.util.UUID


@RunWith(AndroidJUnit4::class)
class FavoriteVacanciesDatabaseTest {
    private lateinit var favoriteDao: FavoriteDAO
    private lateinit var db: FavoriteVacanciesDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, FavoriteVacanciesDatabase::class.java).build()
        favoriteDao = db.getDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() = runTest {
        favoriteDao.clearFavorites()
        val vacancies = listOf(
            FavoriteVacancyEntity(
                id = UUID.randomUUID(),
                interestedPeopleCount = 10,
                title = "Title",
                city = "City",
                company = "Company",
                experienceText = "Experience"
            )
        )

        favoriteDao.saveFavorites(vacancies)
        val vacanciesFromDb = favoriteDao.getFavorites()
        assertEquals(vacanciesFromDb, vacancies)
    }
}
