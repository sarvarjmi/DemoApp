package com.demoapp.data.mapper

import com.demoapp.data.local.entity.HomeFeedEntity
import com.demoapp.data.remote.dto.EntityDto
import com.demoapp.data.remote.dto.ModuleDto
import com.demoapp.domain.model.HomeModule
import com.demoapp.domain.model.PremierItem
import com.demoapp.domain.model.ShowItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun ModuleDto.toEntity(rank: Int, gson: Gson): HomeFeedEntity {
    return HomeFeedEntity(
        moduleId = moduleId ?: "unknown_$rank",
        moduleName = moduleName,
        moduleType = moduleType,
        rank = rank,
        entitiesJson = gson.toJson(entities)
    )
}

fun HomeFeedEntity.toDomainModel(gson: Gson): HomeModule {
    val type = object : TypeToken<List<EntityDto>>() {}.type
    val entities: List<EntityDto> = try {
        gson.fromJson(entitiesJson, type) ?: emptyList()
    } catch (e: Exception) {
        emptyList()
    }

    return when (moduleId) {
        "premier_module_id" -> {
            HomeModule.PremierModule(
                moduleId = moduleId,
                rank = rank,
                moduleName = moduleName,
                items = entities.mapNotNull { it.value?.premierInfo }.map {
                    PremierItem(
                        id = it.id ?: "",
                        imageUrl = it.premierImageUrl ?: "",
                        actionText = it.actionText ?: "Play"
                    )
                }
            )
        }
        "discovery_trailer_module" -> {
            HomeModule.ShowModule(
                moduleId = moduleId,
                rank = rank,
                moduleName = moduleName,
                items = entities.mapNotNull { entity ->
                    entity.value?.let { value ->
                        ShowItem(
                            type = entity.type ?: "show",
                            imageUrl = value.imageUrl ?: "",
                            showTitle = value.showTitle ?: ""
                        )
                    }
                },
                layoutType = HomeModule.LayoutType.BIG
            )
        }
        else -> {
            HomeModule.ShowModule(
                moduleId = moduleId,
                rank = rank,
                moduleName = moduleName,
                items = entities.mapNotNull { entity ->
                    entity.value?.let { value ->
                        ShowItem(
                            type = entity.type ?: "show",
                            imageUrl = value.imageUrl ?: "",
                            showTitle = value.showTitle ?: ""
                        )
                    }
                },
                layoutType = HomeModule.LayoutType.SMALL
            )
        }
    }
}
