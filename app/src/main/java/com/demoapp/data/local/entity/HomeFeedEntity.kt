package com.demoapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "home_feed")
data class HomeFeedEntity(
    @PrimaryKey val moduleId: String,
    val moduleName: String?,
    val moduleType: String?,
    val rank: Int,
    val entitiesJson: String // Storing as JSON for simplicity in polymorphic variants
)
