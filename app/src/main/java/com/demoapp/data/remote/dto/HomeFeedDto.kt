package com.demoapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class HomeFeedDto(
    @SerializedName("status") val status: Int?,
    @SerializedName("message") val message: String?,
    @SerializedName("result") val result: List<ModuleDto>?
)

data class ModuleDto(
    @SerializedName("module_id") val moduleId: String?,
    @SerializedName("module_name") val moduleName: String?,
    @SerializedName("module_type") val moduleType: String?,
    @SerializedName("entities") val entities: List<EntityDto>?
)

data class EntityDto(
    @SerializedName("type") val type: String?,
    @SerializedName("value") val value: EntityValueDto?
)

data class EntityValueDto(
    @SerializedName("premier_info") val premierInfo: PremierInfoDto?,
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("show_title") val showTitle: String?
)

data class PremierInfoDto(
    @SerializedName("id") val id: String?,
    @SerializedName("premier_image_url") val premierImageUrl: String?,
    @SerializedName("action_text") val actionText: String?
)
