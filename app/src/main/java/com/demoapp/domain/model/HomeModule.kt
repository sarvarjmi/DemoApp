package com.demoapp.domain.model

sealed class HomeModule {
    abstract val moduleId: String
    abstract val rank: Int
    abstract val moduleName: String?

    data class PremierModule(
        override val moduleId: String,
        override val rank: Int,
        override val moduleName: String?,
        val items: List<PremierItem>
    ) : HomeModule()

    data class ShowModule(
        override val moduleId: String,
        override val rank: Int,
        override val moduleName: String?,
        val items: List<ShowItem>,
        val layoutType: LayoutType
    ) : HomeModule()

    enum class LayoutType {
        SMALL, BIG
    }
}

data class PremierItem(
    val id: String,
    val imageUrl: String,
    val actionText: String
)

data class ShowItem(
    val type: String,
    val imageUrl: String,
    val showTitle: String
)
