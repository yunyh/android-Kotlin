package com.example.younghyupyun.radiobuttonswiching.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plants")
data class PlantsModel(
    @PrimaryKey
    val plantId: String,
    val name: String,
    val description: String,
    val growZoneNumber: Int,
    val wateringInterval: Int,
    val imageUrl: String
)