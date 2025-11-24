package com.tongminhnhut.android_compose.compose_app.presentation.component

data class ComponentModel(
    val title: String,
    val componentTypeEnum: ComponentTypeEnum,
)

enum class ComponentTypeEnum(val displayName: String){
    TEXT_INPUT("Text Input"),
    BUTTON("Button"),
    DROP_DOWN("Drop down box"),
    IMAGE_VIEW("Image View"),
    LIST_VIEW("List View")
}
