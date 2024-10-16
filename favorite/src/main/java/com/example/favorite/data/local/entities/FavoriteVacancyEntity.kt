package com.example.favorite.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.favorite.data.local.converters.DateConverter
import java.util.Date
import java.util.UUID

@Entity("favorite_vacancies")
@TypeConverters(DateConverter::class)
data class FavoriteVacancyEntity(
    @PrimaryKey
    val id: UUID,
    @ColumnInfo("interested_people_count")
    val interestedPeopleCount: Int,
    val title: String,
    val city: String,
    val company: String,
    @ColumnInfo("experience_text")
    val experienceText: String,
    @ColumnInfo("publish_date")
    val publishDate: Date
)
